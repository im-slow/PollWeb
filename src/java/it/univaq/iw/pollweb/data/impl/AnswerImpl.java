/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.iw.pollweb.data.impl;

import it.univaq.iw.pollweb.data.model.Answer;

/**
 *
 * @author andy4
 */
public class AnswerImpl implements Answer {

    private int key;
    private String result;
    private String min;
    private String max;  
        
    public AnswerImpl() {        
        key = 0;
        result = "";
        min = "";
        max = "";
    }
    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getMin() {
        return min;
    }

    @Override
    public String getMax() {
        return max;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void setMin(String min) {
        this.min = min;
    }

    @Override
    public void setMax(String max) {
        this.max = max;
    }
    
}
