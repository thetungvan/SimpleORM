/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import org.json.*;

public class ModelMapper {

    public static Dictionary<String, ModelConfig> modelConfigs = new Hashtable();

    public static Dictionary<String, ModelConfig> get() {
        return modelConfigs;
    }

    public static void load(String pathToFile) throws FileNotFoundException {
        modelConfigs = new Hashtable();
        // Read config file
        File file = new File(ModelMapper.class.getResource(pathToFile).getFile());
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

        JSONArray modelConfigData = new JSONArray(fileContent);
        for (int i = 0; i < modelConfigData.length(); i++) {
            List<ModelProperty> properties = new ArrayList<ModelProperty>();
            JSONArray modelProperties = modelConfigData.getJSONObject(i).getJSONArray("properties");
            for (int j = 0; j < modelProperties.length(); j++) {
                String name = modelProperties.getJSONObject(j).getString("name");
                String type = modelProperties.getJSONObject(j).getString("type");
                Boolean primary = modelProperties.getJSONObject(j).getBoolean("primary");
                properties.add(new ModelProperty(name, type, primary));
            }

            String className = modelConfigData.getJSONObject(i).getString("className");
            String tableName = modelConfigData.getJSONObject(i).getString("tableName");
            modelConfigs.put(className, new ModelConfig(className, tableName, properties));
        }

    }
}
