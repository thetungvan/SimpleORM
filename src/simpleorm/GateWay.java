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
            String sql = "Select * from " + tableName;
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
        if (columns.length == values.length) {
            try {
                String sql = "INSERT INTO " + tableName + "(";
                for (String single_com : columns) {
                    sql += single_com;
                    sql += ",";
                }
                sql = sql.substring(0, sql.length() - 1);
                sql += ") VALUES (";

                for (Object single_val : values) {
                    sql += '?';
                    sql += ",";
                }

                sql = sql.substring(0, sql.length() - 1);
                sql += ")";

                // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
                Connection conn = ConnectorFactory.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                for (int i = 0; i < columns.length; i++) {
                    String fieldType = ModelMapper.modelConfigs.get(tableName).properties.get(columns[i]).type;
                    pstm.setObject(i + 1, values[i]);

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
    public static void update(String tableName, String[] columns, Object[] values, String[] columncondition, Object[] condition) throws ClassNotFoundException {
        if (columns.length == values.length && columncondition.length == condition.length) {
            try {
                String sql = "UPDATE  " + tableName + " SET ";
                for (int i = 0; i < columns.length; i++) {
                    sql += columns[i] + " = ?" + ", ";
                }

                sql = sql.substring(0, sql.length() - 2);
                sql += " WHERE ";
                for (int i = 0; i < columncondition.length; i++) {
                    sql += columncondition[i];
                    sql += " = ? AND ";
                }
                sql = sql.substring(0, sql.length() - 4);
                Connection conn = ConnectorFactory.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                int count = 0;
                for (int i = 0; i < columns.length; i++) {
                    pstm.setObject(i + 1, values[i]);
                    count++;
                }
                for (int i = 0; i < columncondition.length; i++) {
                    pstm.setObject(i + count + 1, condition[i]);
                }
                pstm.executeUpdate();
                System.out.println("Update completed");
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //delete
    public static void delete(String tableName, String[] columns, Object[] values) throws ClassNotFoundException {
        if (columns.length == values.length) {
            try {
                String sql = "DELETE FROM  " + tableName + " WHERE ";
                for (int i = 0; i < columns.length; i++) {
                    sql += columns[i];
                    sql += " = ? AND ";
                }
                sql = sql.substring(0, sql.length() - 4);
                Connection conn = ConnectorFactory.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);
                for (int i = 0; i < columns.length; i++) {
                    pstm.setObject(i + 1, values[i]);
                }
                pstm.executeUpdate();
                System.out.println("Delete completed");
                conn.close();
                // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
            } catch (SQLException ex) {
                Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void DeleteAll(String tableName) throws ClassNotFoundException {
        try {
            String sql = "DELETE FROM  " + tableName;
            Connection conn = ConnectorFactory.getConnection();
            Statement statement = conn.createStatement();
            int rowCount = statement.executeUpdate(sql);
            System.out.println("Row Count affected = " + rowCount);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GateWay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
