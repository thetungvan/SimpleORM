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
            //String condition = "Id= ahihi";
            //GateWay.update("student", columns, values, condition);
            //GateWay.insert("student",columns,values);
            //ResultSet rs = GateWay.findByAttribute("student", "ID", 1412410);
            DataMapper<student> newDM = new DataMapper<student>(student.class);
            System.out.println("-----------------DATAMAPPER TEST--------------------");
            List<student> resList = new ArrayList<>();
            
            System.out.println("----insert------------");
            String[] columns = {"ID","name"};
            Object[] values = {343434,"SmileyFace"};
            newDM.insert(columns, values);
            
            try {
                resList = newDM.findAll();
                System.out.println("----findAll------------");
                for (int i = 0; i < resList.size() ; i++)
                {
                    System.out.println(resList.get(i).getID() + " : " + resList.get(i).getName());
                }
            } catch (Exception ex) {
                Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                resList = newDM.findByAttribute("ID", "1412563");
                System.out.println("------findByAttribute-----------------");
                for (int i = 0; i < resList.size() ; i++)
                {
                    System.out.println(resList.get(i).getID() + " : " +  resList.get(i).getName());
                }
            } catch (Exception ex) {
                Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
