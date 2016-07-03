/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad3;

/**
 *
 * @author A20111
 */
public class Contact {
    private String name, number;
    
    public Contact(String newName, String newNumber) {
        name = newName;
        number = newNumber;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public void setNumber(String newNumber) {
        number = newNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public String getNumber() {
        return number;
    }
}
