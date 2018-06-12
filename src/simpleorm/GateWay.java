/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm;

import config.ModelMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpleorm.Connector.ConnectionUtils;
import simpleorm.Connector.ConnectorFactory;

public abstract class GateWay {

    //find allby table's name
    public static ResultSet findAll(String tableName) throws ClassNotFoundException {
        try {
            System.out.println("Name:" + tableName);
            String sql = "Select * from " + tableName ;

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            Connection conn = ConnectorFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //find by attribute(s)
    public static ResultSet findByAttribute(String tableName, String ColumnName, String Condition) throws ClassNotFoundException {
        try {
            String fieldType = ModelMapper.modelConfigs.get("student").properties.get(ColumnName).type;
            int inttype = Integer.parseInt(Condition);
            String sql = "Select * from " + tableName + " where " + ColumnName + " = ?";
            System.out.println(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            Connection conn = ConnectionUtils.getMyConnection();
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, inttype);
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    //insert
    public static void insert(String tableName, String[] columns, String[] values) throws ClassNotFoundException {
        try {
            if (columns.length == values.length)
            {
                String sql = "INSERT INTO " + tableName + " (";
                for (String single_com : columns){
                    sql += single_com;
                    sql += ",";
                }
                sql.substring(0, sql.length() - 1);
                sql += ") VALUES (";

                for (String single_val : values){
                    sql += single_val;
                    sql += ",";
                }

                sql.substring(0, sql.length() - 1);
                sql += ")";

                // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
                Connection conn = ConnectionUtils.getMyConnection();
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //update
    public static void update(String tableName, String[] columns, String[] values, String condition) throws ClassNotFoundException {
        try {
            if (columns.length == values.length)
            {
                String sql = "UPDATE  " + tableName + " SET ";
                for (int i = 0; i < columns.length; i++){
                    sql+= columns[i] + " = " + values[i] + ", ";
                }

                sql.substring(0, sql.length() - 1);
                sql += " WHERE " + condition;

                // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
                Connection conn = ConnectionUtils.getMyConnection();
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //delete
    public static void delete(String tableName, String columns, String values) throws ClassNotFoundException {
        try {
            String sql = "DELETE FROM  " + tableName + " WHERE " + columns 
                    + " = " + values;

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            Connection conn = ConnectionUtils.getMyConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
