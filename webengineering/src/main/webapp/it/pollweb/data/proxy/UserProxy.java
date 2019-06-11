/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.proxy;

import it.pollweb.data.framework.data.DataLayer;
import it.pollweb.data.impl.UserImpl;

/**
 *
 * @author andy4
 */
public class UserProxy extends UserImpl{
    
    protected boolean dirty;
    protected DataLayer dataLayer;
    
    public UserProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
    }
    
    @Override
    public void setKey(int key) {
        super.setKey(key);
        this.dirty = true;
    }
    
    @Override
    public void setName(String name){
        super.setName(name);
        this.dirty = true;
    }
    
    @Override
    public void setEmail(String email){
        super.setEmail(email);
        this.dirty = true;
    }
    
    @Override
    public void setPassword(String password){
        super.setPassword(password);
        this.dirty = true;
    }
    
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
    
}
