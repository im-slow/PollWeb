/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.proxy;

import java.data.impl.PollImpl;
import java.data.model.Answer;
import java.data.model.Question;
import java.data.model.User;
import java.framework.data.DataLayer;
import java.util.List;

/**
 *
 * @author andy4
 */
public class PollProxy extends PollImpl{
    protected boolean dirty;
    protected DataLayer dataLayer;
    
    public PollProxy(DataLayer d) {
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
    public void setTitle(String title){
        super.setTitle(title);
        this.dirty = true;
    }
    
    @Override
    public void setOpenText(String openText){
        super.setOpenText(openText);
        this.dirty = true;
    }
    
    @Override
    public void setCloseText(String closeText){
        super.setCloseText(closeText);
        this.dirty = true;
    }
    
    @Override
    public void setQuestions(List<Question> questions){
        super.setQuestions(questions);
        this.dirty = true;
    }
    
    @Override
    public void setResponsible(User responsible){
        super.setResponsible(responsible);
        this.dirty = true;
    }
    
    @Override
    public void setOpenPoll(boolean openPoll){
        super.setOpenPoll(openPoll);
        this.dirty = true;
    }
    
    @Override
    public void setStatePoll(boolean statePoll){
        super.setStatePoll(statePoll);
        this.dirty = true;
    }
    
    @Override
    public void setURLPoll(String url){
        super.setURLPoll(url);
        this.dirty = true;
    }
    
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
