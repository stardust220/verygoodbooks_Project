/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import vgb.domain.VGBException;

/**
 *
 * @author Administrator
 */
public class RDBConnection {
    private static final String DATA_SOURCE;  // = "jdbc/vgb"
    private static final String DRIVER; // = "com.mysql.jdbc.Driver";
    private static final String URL; // = "jdbc:mysql://localhost:3306/vgb?zeroDateTimeBehavior=convertToNull";
    private static final String USER_ID; // = "root", 
    private static final String PASSWORD; // = "1234";

    static {
        String data_source = null;
        String driver = null, url = null, userId = null, password = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("vgb.model.JDBC");
            data_source = bundle.getString("jdbc.data.source");
            driver = bundle.getString("jdbc.driver");
            url = bundle.getString("jdbc.url");
            userId = bundle.getString("jdbc.userid");
            password = bundle.getString("jdbc.password");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("無法讀取JDBC.properties檔案");
        }

        if (data_source==null) data_source = "jdbc/vgb";
        
        if (driver==null) driver = "com.mysql.jdbc.Driver";
        if (url==null) url = "jdbc:mysql://localhost:3306/vgb?zeroDateTimeBehavior=convertToNull";
        if (userId==null) userId = "root";
        if (password==null) password = "1234";
        
        
        DATA_SOURCE = data_source;
        
        DRIVER = driver;
        URL = url;
        USER_ID = userId;
        PASSWORD = password ;
    }

    static Connection getConnection() throws VGBException {
        DataSource ds = null;
        Connection connection = null;
        
        try {
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI Context could not be found.");
            }
            
            ds = (DataSource) ctx.lookup("java:comp/env/" + DATA_SOURCE);
            connection = ds.getConnection();
            System.out.println("已取得Connection Pool中的連線: " + connection);
            return connection;            
        }catch(Exception e){
            System.out.println("無法連結Connection Pool:" + e + ",改用DriverManager取得連線!");
            try {
                //1. 載入MySQL JDBC Driver
                Class.forName(DRIVER);
                try {
                    //2. 建立連線(Connection)
                    connection = DriverManager.getConnection(URL, USER_ID, PASSWORD);
                    //3. 回傳連線(Connection)
                    return connection;
                } catch (SQLException ex) {
                    Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "無法建立連線", ex);
                    throw new VGBException("無法建立連線", ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "無法載入" + DRIVER, ex);
                throw new VGBException("無法載入" + DRIVER, ex);
            }
        }
    }

    public static void main(String[] args) {
        try (Connection connection = RDBConnection.getConnection();) {
            System.out.println(connection.getCatalog());
        } catch (Exception ex) {
            Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
