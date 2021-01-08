package com.cas.components.rwdb;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:52 2020-07-07
 * @version: V1.0
 * @review:
 */
@Slf4j
@Component
@Intercepts(
        {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement)objects[0];

        DynamicDataSourceGlobal dynamicDataSourceGlobal = null;

        // 读方法
        if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
            dynamicDataSourceGlobal = DynamicDataSourceGlobal.READ;
        } else {
            // 写数据源
            dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
        }

        System.out.println("-------------------------------");
        System.out.println("方法[{" + ms.getId() + "}] 使用了[{" + dynamicDataSourceGlobal.name() + "}] 数据源，类型[{" + ms.getSqlCommandType() + "}]");

        // 设置数据源
        DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
