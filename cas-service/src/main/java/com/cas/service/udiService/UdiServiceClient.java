package com.cas.service.inqueryService;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class InqueryServiceClient implements InqueryService{
    private static final Logger LOGGER = LoggerFactory.getLogger(InqueryServiceClient.class);

    private static final String PARAM_PAGE_NUM = "pageNum";
    private static final String PARAM_PAGE_SIZE = "pageSize";

    private static Map<String, String> sqlMap = new HashMap<>();

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<LinkedHashMap<String, Object>> inquery(String statementId, String sqlText, Map<String, String> paramsMap) {
        SqlSession sqlSession = null;
        try {
            // 获取 SqlSession
            sqlSession = sqlSessionFactory.openSession();
            Configuration configuration = sqlSession.getConfiguration();

            // 判断map缓存中是否存在此sql
            if (!sqlText.equals(sqlMap.get(statementId))) {
                if (configuration.hasStatement(statementId)) {
                    configuration.getMappedStatements().remove(configuration.getMappedStatement(statementId));
                }
                sqlMap.put(statementId, sqlText);
            }

            // 加载 sql(当configuration中不存在 或者 标记需要reload时)  并发时 多个请求同时加载相同的sqlId 导致第二个加载失败报错
            if (!configuration.hasStatement(statementId)) {
                // 获取 LanguageDriver
                LanguageDriver languageDriver = configuration.getLanguageRegistry().getDefaultDriver();
                // 构造sqlsource
                SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlText, Map.class);
                // 创建一个查询statement
                newSelectMappedStatement(statementId, configuration, sqlSource, Map.class);
            }
            // 执行sql
            if (paramsMap.containsKey(PARAM_PAGE_NUM) && paramsMap.containsKey(PARAM_PAGE_SIZE)) { // 针对需要分页的情况
                int pageNum = Integer.parseInt(paramsMap.get(PARAM_PAGE_NUM)); // 页码
                int pageSize = Integer.parseInt(paramsMap.get(PARAM_PAGE_SIZE)); // 行数
                return sqlSession.selectList(statementId, paramsMap, new RowBounds(pageNum, pageSize));
            } else {
                return sqlSession.selectList(statementId, paramsMap, new RowBounds(0, 2000));
            }
        } finally {
            // 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }


    /**
     * 创建一个查询statement
     *
     * @param statementId
     * @param configuration
     * @param sqlSource     执行的sqlSource
     * @param resultType    返回的结果类型
     */
    private void newSelectMappedStatement(String statementId, Configuration configuration, SqlSource sqlSource,
                                          final Class<?> resultType) {
        List<ResultMap> resultMaps = new ArrayList<>();
        resultMaps.add(new ResultMap.Builder(configuration, "defaultResultMap", resultType,
                new ArrayList<ResultMapping>(0)).build());
        MappedStatement ms = new MappedStatement.Builder(configuration, statementId, sqlSource, SqlCommandType.SELECT)
                .resultMaps(resultMaps).build();
        try {
            // 缓存到mybatis
            configuration.addMappedStatement(ms);
        } catch (java.lang.IllegalArgumentException e) {
            // 捕捉到加载异常 跳过加载 继续接下来的流程 此异常为并发时 多请求同时加载相同的SQLID导致 第二个加载时 会报错 所以可以忽略
            LOGGER.error("IllegalArgumentException", e);
            return;
        }
    }
}
