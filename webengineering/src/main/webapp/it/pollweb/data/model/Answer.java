/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.model;

/**
 *
 * @author andy4
 */
public interface Answer {
    
    int getKey();
    
    String getAnswer();
    
    void setKey(int key);

    void setAnswer(String answer);
    
}
