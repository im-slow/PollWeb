/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.model;

/**
 *
 * @author andy4
 */
public interface User {
    
    int getKey();

    String getName();
    
    String getEmail();
    
    String getPassword();
    
    void setKey(int key);

    void setName(String name);
    
    void setEmail(String email);
    
    void setPassword(String password);
    
}
