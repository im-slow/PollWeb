/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.model;

import java.util.List;

/**
 *
 * @author andy4
 */
public interface User {
    
    int getKey();

    String getName();
    
    String getEmail();
    
    String getPassword();

    List<Instance> getInstances();

    List<Group> getGroups();
    
    void setKey(int key);

    void setName(String name);
    
    void setEmail(String email);
    
    void setPassword(String password);

    void setInstances(List<Instance> instances);

    void setGroups(List<Group> groups);
    
}
