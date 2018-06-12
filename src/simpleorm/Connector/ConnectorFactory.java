/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm.Connector;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Tung
 */
public class ConnectorFactory {

    private static Dictionary<String, String> datasourceConfig = new Hashtable();

    public static void loadDatasourceConfig(String pathToFile) throws FileNotFoundException {
        datasourceConfig = new Hashtable();
        File file = new File(ConnectorFactory.class.getResource(pathToFile).getFile());
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");
        String fileContent;
        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            fileContent = fileContents.toString();
        } finally {
            scanner.close();
        }

        JSONObject config = new JSONObject(fileContent);
        datasourceConfig.put("connector", config.getString("connector"));
        datasourceConfig.put("hostName", config.getString("hostName"));
        datasourceConfig.put("database", config.getString("database"));
        datasourceConfig.put("userName", config.getString("userName"));
        datasourceConfig.put("password", config.getString("password"));
        datasourceConfig.put("portNumber", config.getString("portNumber"));
        datasourceConfig.put("instanceName", config.getString("instanceName"));
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String connector = datasourceConfig.get("connector");
        System.out.println(connector);
        switch (connector) {
            case "mysql":
                return new MySQLConnector().getConnection(datasourceConfig);
            case "postgresql":
                return new PostgresqlConnector().getConnection(datasourceConfig);
            default:
                return null;
        }
    }
}
