/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.impl;

import it.pollweb.data.model.Group;
import it.pollweb.data.model.Instance;
import it.pollweb.data.model.User;

import java.util.List;

/**
 *
 * @author andy4
 */
public class UserImpl implements User {
    private int key;
    private String name;
    private String email;
    private String password;
    private List<Instance> instances;
    private List<Group> groups;

    public UserImpl() {        
        key = 0;
        name = "";
        email = "";
        password = "";
        instances = null;
        groups = null;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<Instance> getInstances(){
        return instances;
    }

    @Override
    public List<Group> getGroups() { return groups; }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEmail(String email) {
        /*
        if(!(users.contains(email))){
            this.email = email;
        } else {
            System.out.println("this email already exists!");
        }
        */
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setInstances(List<Instance> instances) { this.instances = instances; }

    @Override
    public void setGroups(List<Group> groups) { this.groups = groups; }

}
