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

/**
 *
 * @author Tung
 */
public class SimpleORM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        try {

            Connection conn = ConnectionUtils.getMyConnection();
            ModelMapper.load("/config/ModelConfig.json");
            System.out.println(ModelMapper.get());

        } catch (FileNotFoundException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SimpleORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
