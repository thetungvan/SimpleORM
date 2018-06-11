/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm;

import config.ModelMapper;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
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
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        try {
            ConnectorFactory.loadDatasourceConfig("/simpleorm/Connector/datasource.json");
            ModelMapper.load("/config/ModelConfig.json");
            Connection conn = ConnectorFactory.getConnection();
            System.out.println(ModelMapper.get());

        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
