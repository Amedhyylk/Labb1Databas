/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.Serializable;

public class Author implements Serializable{
    
    private String name;
    
    
    /**
     * here is were you store data for author
     * @param name 
     */
    public Author(String name)
    {
        this.name=name;
        
    }
    /**
     * this is a accessmethod that returns a private member veriable
     * @return 
     */

    public String getName() {
        return name;
    }
    
    

    @Override
    public String toString() {
        return name;
    }
    
}