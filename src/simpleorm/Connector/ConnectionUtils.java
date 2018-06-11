/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm.Connector;

/**
 *
 * @author NhoxToong
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtils {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {
        return Connector.getConnection();
    }

    public static ResultSet QuerrySelect(String querry) throws SQLException {
        try {
            Connection conn = ConnectionUtils.getMyConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(querry);
            //conn.close();
            return rs;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
