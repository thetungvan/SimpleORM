/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author Tung
 */
public class ModelProperty {

    public String name;
    public String type;
    public Boolean primary;

    public ModelProperty(String name, String type, Boolean primary) {
        this.name = name;
        this.type = type;
        this.primary = primary;
    }
}
