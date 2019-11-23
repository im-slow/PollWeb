/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.proxy;

import it.pollweb.data.impl.AnswerImpl;
import framework.data.DataLayer;

/**
 *
 * @author andy4
 */
public class AnswerProxy extends AnswerImpl{
    protected boolean dirty;
    protected int question_key;
    protected DataLayer dataLayer;
    
    public AnswerProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
        this.question_key = 0;
    }
    
    @Override
    public void setKey(int key) {
        super.setKey(key);
        this.dirty = true;
    }
    
    @Override
    public void setAnswer(String answer){
        super.setAnswer(answer);
        this.dirty = true;
    }

    public void setQuestionKey(int question_key){
        this.question_key = question_key;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
