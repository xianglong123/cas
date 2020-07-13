package com.cas.components.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:49 2020-07-09
 * @version: V1.0
 * @review:
 */
@Component
public class XlTranscationManage {

    private static ThreadLocal<Connection> con = new ThreadLocal<>();

    @Autowired
    private DataSource dataSource;

    public Connection getCon () throws SQLException {
        if (con.get() == null) {
            con.set(dataSource.getConnection());
        }
        return con.get();
    }

}
