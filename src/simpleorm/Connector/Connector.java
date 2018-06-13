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
public abstract class Connector {

    public final Connection getConnection(Dictionary<String, String> config) throws SQLException, ClassNotFoundException {
        String hostName = config.get("hostName");
        String instanceName = config.get("instanceName");
        String database = config.get("database");
        String userName = config.get("userName");
        String password = config.get("password");
        String portNumber = config.get("portNumber");

        return getConnection(hostName, instanceName,
                database, userName, password, portNumber);
    }

    public abstract Connection getConnection(String hostName,
            String instanceName, String database, String userName,
            String password, String portNumber) throws ClassNotFoundException, SQLException;
}
