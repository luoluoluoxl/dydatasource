package com.test.dydatasource.dydatasource.util;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * 动态创建数据源
 */
@Component
public class DataSourceConn extends BasicDataSource {
    @Value("${project.driverClassName}")
    String driverClassName;
//    String driverClassName = "com.mysql.jdbc.Driver";

    @Value("${project.url}")
    String url;
//    String url = "jdbc:mysql://127.0.0.1:3306/datasource_info?useUnicode=true&amp;characterEncoding=UTF-8";

    @Value("${project.username}")
    String username;
//    String username = "root";

    @Value("${project.password}")
    String password;
//    String password = "root";

    public DataSource getDataSource() {
        DataSource ds = null;
        super.setDriverClassName(driverClassName);
        super.setUrl(url);
        super.setUsername(username);
        super.setPassword(password);
        try {
            ds = super.createDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public DataSource getDataSource(Map<String, Object> map) {
        DataSource ds = null;
        super.setDriverClassName((String) map.get("driver"));
        super.setUrl((String) map.get("url"));
        super.setUsername((String) map.get("username"));
        super.setPassword((String) map.get("password"));
        try {
            ds = super.createDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
}