/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.iw.pollweb.data.model;

/**
 *
 * @author andy4
 */
public interface Answer {
    
    int getKey();
    
    String getResult();
    
    String getMin();
    
    String getMax();
    
    void setKey(int key);

    void setResult(String result);
    
    void setMin(String min);
    
    void setMax(String max);
}
