/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpleorm.Connector.ConnectionUtils;

public abstract class GateWay {

    public ResultSet findAll(String name, Object value) throws ClassNotFoundException {
        try {
            String className = this.getClass().getSimpleName();
            System.out.println("Name:" + className);
            String sql = "Select * from student";

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            Connection conn = ConnectionUtils.getMyConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            //conn.close();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
