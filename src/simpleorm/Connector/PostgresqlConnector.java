/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NhoxToong
 */
public class PostgresqlConnector extends Connector {

    @Override
    public Connection getConnection(String hostName,
            String instanceName, String database, String userName,
            String password, String portNumber) throws ClassNotFoundException, SQLException {
        try {
            // Connection class
            Class.forName("org.postgresql.Driver").newInstance();

            String connectionURL = "jdbc:postgresql://" + hostName + ":" + portNumber + "/" + database;

            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            return conn;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
