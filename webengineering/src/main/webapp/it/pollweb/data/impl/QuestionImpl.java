/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.impl;

import it.pollweb.data.model.Answer;
import it.pollweb.data.model.Question;

import java.util.List;

/**
 *
 * @author andy4
 */
public class QuestionImpl implements Question {

    private int key;
    private String code;
    private String text;
    private String note;
    private boolean mandatory;
    private QuestionType type;
    private int position;
    private String min;
    private String max;
    private String option;
    private List<Answer> answers;
    
    
    public QuestionImpl() {        
        key = 0;
        code = "";
        text = "";
        note = "";
        mandatory = false;
        type = null;
        position = 0;
        min = "";
        max = "";
        option = "";
        answers = null;
    }
    
    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getCode(){
        return code;
    }

    @Override
    public String getQuestionText() { return text; }
    
    @Override
    public String getNote(){
        return note;
    }
    
    @Override
    public QuestionType getQuestionType(){
        return type;
    }
    
    @Override
    public boolean getMandatory(){
        return mandatory;
    }
    
    @Override
    public int getPositionNumber() {
        return position;
    }

    @Override
    public String getMinimum() {
        return min;
    }

    @Override
    public String getMaximum() {
        return max;
    }

    @Override
    public String getOption() {
        return option;
    };

    @Override
    public List<Answer> getAnswers(){
        return answers;
    }
    
    @Override
    public void setKey(int key) {
        this.key = key;
    }
    
    @Override
    public void setCode(String code){
        this.code = code;
    }
            
    @Override
    public void setQuestionText(String text){
        this.text = text;
    }    
    
    @Override
    public void setNote(String note){
        this.note = note;
    }
    
    @Override
    public void setQuestionType(QuestionType type){
        this.type = type;
    }
    
    @Override
    public void setMandatory(boolean mandatory){
        this.mandatory = mandatory;
    }

    @Override
    public void setPositionNumber(int position) {
        this.min = min;
    }
    
    @Override
    public void setMinimum(String min) {
        this.min = min;
    }

    @Override
    public void setMaximum(String max) {
        this.max = max;
    }

    @Override
    public void setOption(String option){
        this.option = option;
    }
    
    @Override
    public void setAnswers(List<Answer> answers){
        this.answers = answers;
    }
    
}
