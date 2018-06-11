/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

    // Kết nối vào SQLServer.
    // (Sử dụng thư viện điều khiển SQLJDBC)
    public static Connection getConnection()
            throws SQLException, ClassNotFoundException {
        String hostName = "sql12.freemysqlhosting.net";
        String sqlInstanceName = "DESKTOP-1TK54IB\\SQLEXPRESS";
        String database = "sql12242253";
        String userName = "sql12242253";
        String password = "McKznSF2Lf";
        String portNumber = "3306";

        return getConnection(hostName, sqlInstanceName,
                database, userName, password, portNumber);
    }

    // Trường hợp sử dụng SQLServer.
    // Và thư viện SQLJDBC.
    public static Connection getConnection(String hostName,
            String sqlInstanceName, String database, String userName,
            String password, String portNumber) throws ClassNotFoundException, SQLException {
        try {
            // Connection class
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String connectionURL = "jdbc:mysql://" + hostName + ":" + portNumber
                    + "?user=" + userName + "&password=" + password;

            Connection conn = DriverManager.getConnection(connectionURL);
            return conn;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
