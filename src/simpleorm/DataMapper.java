/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm;
import config.ModelMapper;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;  
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DataMapper<T> {
    private String className = "";
    private final Class<T> type;
    
    public DataMapper(Class<T> t){/*
        if (this.getClass().getSimpleName() != "DataMapper")
        {
            className = this.getClass().getSimpleName();
            className.substring(0, className.length() - 10);
            System.out.println(className);
            //Class has names like 'StudentDataMapper' will become 'Student'
        }*/
        this.type = t;
        className = getMyType().getSimpleName();
        System.out.println("Class name is : " + className);
    }
    
    public Class<T> getMyType(){
        return this.type;
    }
    
    public void setClassName(String input){
        this.className = input;
    }
    
    
    
    public List<T> findAll() throws Exception{
        
        try {
            List<T> res = new ArrayList<>();
            try (ResultSet resSet = GateWay.findAll(className)) {
                while(resSet.next()){/*
                        Field[] fields = this.type.getTypeParameters().getClass().getDeclaredFields();//this.type.getTypeName().getClass().getDeclaredFields();
                        for (int i = 0; i < fields.length; i++)
                        {
                        String name = fields[i].getName();//atribute name
                        String type = fields[i].getType().getSimpleName();//attribute type
                        System.out.println(name + " : " + type);
                        }*/
                    String strScr = getMyType().getName();
                    Object newObject = Class.forName(strScr).newInstance();
                    Field[] fields = newObject.getClass().getDeclaredFields();
                    
                    int size_of_props = ModelMapper.modelConfigs.get(className).properties.size();
                    if (size_of_props != fields.length){
                        break;
                    }//check if number of properties in json file == number of attributes declared in class
                    for (int i = 0; i < fields.length; i++)
                    {
                        String fieldName = fields[i].getName();
                        Field f = newObject.getClass().getDeclaredField(fieldName);
                        f.setAccessible(true);
                        f.set(newObject, resSet.getObject(fieldName));
                    }
                    res.add((T) newObject);
                }
                return res;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<T> findByAttribute(String collumnName,String condition) throws Exception{
        List<T> res = new ArrayList<>();
        try (ResultSet resSet = GateWay.findByAttribute(className, collumnName, condition)){
            while (resSet.next()){
                String strScr = getMyType().getName();
                Object newObject = Class.forName(strScr).newInstance();
                Field[] fields = newObject.getClass().getDeclaredFields();
                int size_of_props = ModelMapper.modelConfigs.get(className).properties.size();
                if (size_of_props != fields.length){
                    break;
                }
                for (int i = 0; i < fields.length; i++)
                {
                    String fieldName = fields[i].getName();
                    Field f = newObject.getClass().getDeclaredField(fieldName);
                    f.setAccessible(true);
                    f.set(newObject, resSet.getObject(fieldName));
                }
                res.add((T) newObject);
            }
            return res;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insert(T input){ try {
        //String[] columns, Object[] values){
        Field[] fields = input.getClass().getDeclaredFields();
        int size = fields.length;
        String[] columns = new String[size];
        Object[] values = new Object[size];
        
        for (int i = 0; i < fields.length; i++){
            try {
                columns[i] = fields[i].getName();
                String fieldName = fields[i].getName();
                Field f = input.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                try {
                    values[i] = f.get(input);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        GateWay.insert(className, columns, values);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(String[] columns, Object[] values, String[] columncondition, Object[] condition){
        try {
            GateWay.update(className, columns, values, columncondition, condition);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete( String[] columns, Object[] values){
        try {
            GateWay.delete(className, columns, values);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAll(){
        try {
            GateWay.DeleteAll(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
