/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.iw.pollweb.data.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author andy4
 */
public interface Question {
    
    public static enum QuestionType {
        SHORTTEXT,
        LONGTEXT,
        NUMBER,
        DATE,
        SINGLECHOISE,
        MULTIPLECHOISE;
    }
    
    int getKey();
    
    String getCode();
    
    String getQuestionText();
    
    String getNote();
    
    QuestionType getQuestionType();
    
    boolean getMandatory();
    
    int getPositionNumber();
    
    String getMinimum();
    
    String getMaximum();
    
    Answer getAnswers();
    
    void setKey(int key);
    
    void setCode(String code);
    
    void setQuestionText(String text);
    
    void setNote(String note);
    
    void setQuestionType(QuestionType type);
    
    void setMandatory(boolean mandatory);
    
    void setPositionNumber(int position);
    
    void setMinimum(String min);
    
    void setMaximum(String max);
    
    void setAnswers(Answer answers);
    
}
