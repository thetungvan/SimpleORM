/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleorm;

/**
 *
 * @author Tung
 */
public class student {

    int ID;
    String name;

    public student() {
        this.ID = 0;
        this.name = "";
    }

    public student(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
