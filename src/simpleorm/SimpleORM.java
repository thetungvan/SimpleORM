/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm;

import config.ModelMapper;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpleorm.Connector.ConnectionUtils;
import simpleorm.Connector.ConnectorFactory;

/**
 *
 * @author Tung
 */
public class SimpleORM {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
        try {
            ConnectorFactory.loadDatasourceConfig("/simpleorm/Connector/datasource.json");
            ModelMapper.load("/config/ModelConfig.json");
            String fieldType = ModelMapper.modelConfigs.get("student").properties.get("ID").type;
            System.out.println(fieldType);
            ResultSet rs = GateWay.findByAttribute("student", "ID", "1412628");
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
                int MAHS = rs.getInt(1);
                String ten = rs.getString(2);
                System.out.println("--------------------");
                System.out.println("EmpId:" + MAHS);
                System.out.println("EmpNo:" + ten);
            }
            System.out.println("-----------------DATAMAPPER TEST--------------------");
            System.out.println("----------findAll---");
            DataMapper<student> newDM = new DataMapper<student>(student.class);
            List<student> resList = new ArrayList<>();
            try {
                resList = newDM.findAll();
            } catch (Exception ex) {
                Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("size : " + resList.size());
            for (int i = 0; i < resList.size() ; i++)
            {
                System.out.println(resList.get(i).getID() + " : " + resList.get(i).getName());
            }
            System.out.println("---------findByAttribute----");
            try {
                resList = newDM.findByAttribute("ID", "1412563");
                for (int i = 0; i < resList.size() ; i++)
                {
                    System.out.println(resList.get(i).getID() + " : " + resList.get(i).getName());
                }
            } catch (Exception ex) {
                Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
