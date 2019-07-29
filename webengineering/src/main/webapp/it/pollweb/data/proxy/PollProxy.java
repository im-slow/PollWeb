/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.proxy;

import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.dao.QuestionDAO;
import it.pollweb.data.dao.UserDAO;
import it.pollweb.data.impl.PollImpl;
import it.pollweb.data.model.Question;
import it.pollweb.data.model.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andy4
 */
public class PollProxy extends PollImpl {
    protected boolean dirty;
    protected DataLayer dataLayer;
    protected int user_key;
    
    public PollProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
        this.user_key = 0;
    }
    
    @Override
    public void setKey(int key) {
        super.setKey(key);
        this.dirty = true;
    }

    @Override
    public User getResponsible() {
        //notare come l'autore in relazione venga caricato solo su richiesta
        //note how the related author is loaded only after it is requested
        if (super.getResponsible() == null && user_key > 0) {
            try {
                super.setResponsible(((UserDAO) dataLayer.getDAO(User.class)).getUser(user_key));
            } catch (DataException ex) {
                Logger.getLogger(UserProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //attenzione: l'autore caricato viene lagato all'oggetto in modo da non
        //dover venir ricaricato alle richieste successive, tuttavia, questo
        //puo' rende i dati potenzialmente disallineati: se l'autore viene modificato
        //nel DB, qui rimarrà la sua "vecchia" versione
        //warning: the loaded author is embedded in this object so that further
        //requests do not require its reloading. However, this may cause data
        //problems since if the author is updated in the DB, here its "old"
        //version will be still attached
        return super.getResponsible();
    }

    @Override
    public List<Question> getQuestions() {
        if(super.getQuestions() == null) {
            try {
                super.setQuestions(((QuestionDAO) dataLayer.getDAO(Question.class)).getQuestionsByPoll(this.getKey()));
            } catch (DataException ex) {
                Logger.getLogger(QuestionProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getQuestions();
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

    public void setResponsibleKey(int author_key) {
        this.user_key = user_key;
        //resettiamo la cache dell'autore
        //reset author cache
        super.setResponsible(null);
    }
    
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
