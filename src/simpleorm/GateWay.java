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
    public static ResultSet findByAttribute(String tableName, String ColumnName, Object Condition) throws ClassNotFoundException {
        try {
            String sql = "Select * from " + tableName + " where " + ColumnName + " = ?";
            System.out.println(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            Connection conn = ConnectorFactory.getConnection();
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, Condition);
            
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    //insert
    public static void insert(String tableName, String[] columns, Object[] values) throws ClassNotFoundException {
        if (columns.length == values.length)
        {
            try {
                String sql = "INSERT INTO " + tableName + "(";
                for (String single_com : columns){
                    sql += single_com;
                    sql += ",";
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += ") VALUES (";
                
                for (Object single_val : values){
                    sql += '?';
                    sql += ",";
                }
                
                sql = sql.substring(0, sql.length() - 1);
                sql += ")";
                
                
                System.out.println(sql);
                // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
                Connection conn = ConnectorFactory.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                for(int i =0;i<columns.length;i++){
                    String fieldType=ModelMapper.modelConfigs.get(tableName).properties.get(columns[i]).type;
                    /*if(fieldType.equals("int")){
                        int inttype = Integer.parseInt(values[i]);
                        pstm.setInt(i+1, inttype);
                        System.out.println(inttype);
                    }
                    else if(fieldType.equals("String")){
                        pstm.setString(i+1, values[i]);
                        System.out.println(values[i]);
                    }*/
                    pstm.setObject(i+1, values[i]);
                    
                    
                }
                pstm.executeUpdate();
                System.out.println("Insert completed");
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    //update
    public static void update(String tableName, String[] columns, Object[] values, Object condition) throws ClassNotFoundException {
        if (columns.length == values.length)
        {
            String sql = "UPDATE  " + tableName + " SET ";
            for (int i = 0; i < columns.length; i++){
                sql+= columns[i] + " = " + values[i] + ", ";
            }
            
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE " + condition;
            System.out.println(sql);
            // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
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
