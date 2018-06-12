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

public class ModelConfig {

    public String className;
    public String tablename;
    public Dictionary<String,ModelProperty> properties = new Hashtable();

    public ModelConfig(String className, String tableName, Dictionary<String, ModelProperty> props) {
        this.className = className;
        this.tablename = tableName;
        this.properties = props;
    }
}
