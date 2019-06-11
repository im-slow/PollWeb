/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.model;

import java.util.List;

/**
 *
 * @author andy4
 */
public interface Poll {

    int getKey();

    String getTitle();
    
    String getOpenText();
    
    String getCloseText();
    
    List<Question> getQuestions();
    
    User getResponsible();
    
    boolean getOpenPoll();
    
    boolean getStatePoll();
    
    String getURLPoll();
    
    void setKey(int key);

    void setTitle(String title);
    
    void setOpenText(String openText);
    
    void setCloseText(String closeText);
    
    void setQuestions(List<Question> questions);
    
    void setResponsible(User responsible);
    
    void setOpenPoll(boolean openPoll);
    
    void setStatePoll(boolean statePoll);
    
    void setURLPoll(String url);
    
}

