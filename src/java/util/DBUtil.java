/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Astersa
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBUtil {
    private static BasicDataSource dataSource;
    
    static {
        try {
            Properties props = new Properties();
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(is);
            
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(props.getProperty("db.driver"));
            dataSource.setUrl(props.getProperty("db.url"));
            dataSource.setUsername(props.getProperty("db.username"));
            dataSource.setPassword(props.getProperty("db.password"));
            
            // Set connection pool properties
            dataSource.setInitialSize(Integer.parseInt(props.getProperty("db.pool.initialSize")));
            dataSource.setMaxTotal(Integer.parseInt(props.getProperty("db.pool.maxActive")));
            dataSource.setMaxIdle(Integer.parseInt(props.getProperty("db.pool.maxIdle")));
            dataSource.setMinIdle(Integer.parseInt(props.getProperty("db.pool.minIdle")));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
