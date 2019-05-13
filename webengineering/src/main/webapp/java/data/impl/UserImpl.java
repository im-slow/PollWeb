/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.impl;

import it.univaq.iw.pollweb.data.model.User;
import java.util.List;

/**
 *
 * @author andy4
 */
public class UserImpl implements User {
    private int key;
    private String name;
    private String surname;
    private String email;
    private String role;

    public UserImpl() {        
        key = 0;
        name = "";
        surname = "";
        email = "";
        role = "User";
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
    public String getSurname() {
        return surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getRole() {
        return role;
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
    public void setSurname(String surname) {
        this.surname = surname;
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
    }

    @Override
    public void setRole(String role) {
        /*
        if(AuthUser=="Admin"){ 
           this.role = role;
        } else {
           System.out.println("Warning! You don't have access")
        }
        */
        this.role = role;
    }
}
