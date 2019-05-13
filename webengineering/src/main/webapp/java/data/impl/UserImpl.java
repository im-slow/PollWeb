/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.impl;

import java.data.model.User;
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

    public UserImpl() {        
        key = 0;
        name = "";
        email = "";
        password = "";
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
}
