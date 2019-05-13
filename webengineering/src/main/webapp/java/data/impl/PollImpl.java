/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.impl;

import java.data.model.Poll;
import java.data.model.Question;
import java.data.model.User;
import java.util.List;

/**
 *
 * @author andy4
 */
public class PollImpl implements Poll {

    private int key;
    private String title;
    private String openText;
    private String closeText;
    private List<Question> questions;
    private User responsible;
    private boolean open;
    private boolean state;
    private String url;

    public PollImpl() {        
        key = 0;
        title = "";
        openText = "";
        closeText = "";
        questions = null;
        responsible = null;
        open = true;
        state = true;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getOpenText() {
        return openText;
    }
    
    @Override
    public String getCloseText() {
        return closeText;
    }
    
    @Override
    public List<Question> getQuestions() {
        return questions;
    } 
    
    @Override
    public User getResponsible() {
        return responsible;
    }
    
    @Override
    public boolean getOpenPoll() {
        return open;
    }
    
    @Override
    public boolean getStatePoll() {
        return state;
    }

    @Override
    public String getURLPoll() {
        return url;
    }
    
    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public void setOpenText(String openText) {
        this.openText = openText;
    }
    
    @Override
    public void setCloseText(String closeText) {
        this.closeText = closeText;
    }
    
    @Override
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    } 
    
    @Override
    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }
    
    @Override
    public void setOpenPoll(boolean open) {
        this.open = open;
    }

    @Override
    public void setStatePoll(boolean state) {
        this.state = state;
    }

    @Override
    public void setURLPoll(String url) {
        this.url = url;
    }
    
}

