/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.proxy;

import framework.data.DataLayer;
import it.pollweb.data.impl.QuestionImpl;
import it.pollweb.data.model.Answer;

/**
 *
 * @author andy4
 */
public class QuestionProxy extends QuestionImpl {
    protected boolean dirty;
    protected DataLayer dataLayer;
    
    public QuestionProxy(DataLayer d) {
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
    public void setCode(String code){
        super.setCode(code);
        this.dirty = true;
    }
    
    @Override
    public void setQuestionText(String text){
        super.setQuestionText(text);
        this.dirty = true;
    }
    
    @Override
    public void setNote(String note){
        super.setNote(note);
        this.dirty = true;
    }
    
    @Override
    public void setQuestionType(QuestionType type){
        super.setQuestionType(type);
        this.dirty = true;
    }
    
    @Override
    public void setMandatory(boolean mandatory){
        super.setMandatory(mandatory);
        this.dirty = true;
    }
    
    @Override
    public void setPositionNumber(int position){
        super.setPositionNumber(position);
        this.dirty = true;
    }
    
    @Override
    public void setMinimum(String min){
        super.setMinimum(min);
        this.dirty = true;
    }
    
    @Override
    public void setMaximum(String max){
        super.setMaximum(max);
        this.dirty = true;
    }
    
    @Override
    public void setAnswers(Answer answers){
        super.setAnswers(answers);
        this.dirty = true;
    }
    
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
