package com.cas.components.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:46 2020-07-09
 * @version: V1.0
 * @review:
 */
@Component
public class XlJdbcTemplate {

    @Autowired
    private XlTranscationManage xlTranscationManage;

    @Transactional
    public void execute(String sql) throws SQLException {
        Connection con = xlTranscationManage.getCon();
        Statement statement = con.createStatement();
        con.setAutoCommit(false);
        statement.execute(sql);
    }


}
