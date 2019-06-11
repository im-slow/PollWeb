/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;
import it.pollweb.data.model.Answer;
import it.pollweb.data.model.Poll;
import framework.data.DAO;
import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.proxy.AnswerProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public class AnswerDAO_MySQL extends DAO implements AnswerDAO{

    private PreparedStatement sInstance, sInstanceByID, sInstanceByUser;
    private PreparedStatement iInstance, uInstance, dInstance;

    public AnswerDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public AnswerProxy createAnswer() {
        return new AnswerProxy(getDataLayer());
    }

    @Override
    public Answer createAnswer(ResultSet rs) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Answer getAnswer(int answer_key) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Answer> getAnswers() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Answer> getAnswers(Poll poll) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeAnswer(Answer answer) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAnswer(Answer answer) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
