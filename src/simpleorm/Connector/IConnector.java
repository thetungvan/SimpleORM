/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Dictionary;

/**
 *
 * @author Tung
 */
public interface IConnector {

    public Connection getConnection(Dictionary<String, String> config) throws SQLException, ClassNotFoundException;

    public Connection getConnection(String hostName,
            String instanceName, String database, String userName,
            String password, String portNumber) throws ClassNotFoundException, SQLException;
}
