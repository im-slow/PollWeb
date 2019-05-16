/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.impl;

import it.pollweb.data.model.Answer;

/**
 *
 * @author andy4
 */
public class AnswerImpl implements Answer {

    private int key;
    private String answer; 
        
    public AnswerImpl() {        
        key = 0;
        answer = "";
    }
    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
