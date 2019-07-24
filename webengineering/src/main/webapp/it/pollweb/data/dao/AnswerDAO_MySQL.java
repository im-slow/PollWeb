/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import framework.data.DAO;
import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.model.Answer;
import it.pollweb.data.model.Poll;
import it.pollweb.data.proxy.AnswerProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andy4
 */
public class AnswerDAO_MySQL extends DAO implements AnswerDAO{

    private PreparedStatement sAnswer, sAnswerByID, sAnswerByQuestion, sAnswers;
    private PreparedStatement iAnswer, uAnswer, dAnswer;

    public AnswerDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            sAnswer = connection.prepareStatement("SELECT * FROM answer");
            sAnswerByID = connection.prepareStatement("SELECT * FROM answer WHERE ID=?");
            sAnswerByQuestion = connection.prepareStatement("SELECT * FROM answer JOIN question WHERE question.ID=answer.IDquestion"); //?
            sAnswers = connection.prepareStatement("SELECT ID FROM answer");

            iAnswer = connection.prepareStatement("INSERT INTO answer (answer,IDpoll) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            uAnswer = connection.prepareStatement("UPDATE answer SET answer=?,IDquestion=? WHERE ID=?");
            dAnswer = connection.prepareStatement("DELETE FROM answer WHERE ID=?");

        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sAnswer.close();
            sAnswerByID.close();
            sAnswerByQuestion.close();
            sAnswers.close();

            iAnswer.close();
            uAnswer.close();
            dAnswer.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public AnswerProxy createAnswer() {
        return new AnswerProxy(getDataLayer());
    }

    @Override
    public AnswerProxy createAnswer(ResultSet rs) throws DataException {
        try{
            AnswerProxy a = createAnswer();
            a.setKey(rs.getInt("ID"));
            a.setString(rs.getString("answer"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create poll object form ResultSet", ex);
            return null;
        }
    }

    @Override
    public Answer getAnswer(int answer_key) throws DataException {
        try {
            sAnswerByID.setInt(1, answer_key);
            try (ResultSet rs = sAnswerByID.executeQuery()) {
                if (rs.next()) {
                    return createAnswer(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by ID", ex);
        }
        return null;
    }

    @Override
    public List<Answer> getAnswers() throws DataException {
        List<Answer> result = new ArrayList<Answer>();
        try (ResultSet rs = sAnswers.executeQuery()) {
            while(rs.next()) {
                result.add(getPoll(rs.getInt("ID")));
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by email", ex);
            return null;
        }
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
