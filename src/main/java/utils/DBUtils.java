package utils;

import constant.DBConstant;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by xiaomengning on 2017/10/5.
 */
public class DBUtils {

    private static String driver;
    private static String url;
    private static String usr;
    private static String password;

    /**
     * load property file ; initialize fields
     */
    static {
        Properties props = new Properties();
        InputStream is = DBUtils.class.getClassLoader().getResourceAsStream(DBConstant.PROPERTY_NAME);
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = props.getProperty("jdbc.driverClassName");
        url = props.getProperty("jdbc.url");
        usr = props.getProperty("jdbc.usr");
        password = props.getProperty("jdbc.password");

    }

    /**
     * register the driver
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * open database connection
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, usr, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * close database connection
     * @param rs
     * @param st
     * @param conn
     */
    public static void closeAll(ResultSet rs,Statement st,Connection conn){
         close(rs);
         close(st);
         close(conn);
    }

    private static void close(Statement st){
        if(null != st){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(Connection conn){
        if(null != conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void close(ResultSet res){
        if(null != res){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
