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
    
    String getText();
    
    String getNote();
    
    QuestionType getQuestionType();
    
    boolean getMandatory();
    
    void setKey(int key);
    
    void setCode(String code);
    
    void setText(String text);
    
    void setNote(String note);
    
    void setQuestionType(QuestionType type);
    
    void setMandatory(boolean mandatory);
    
}
