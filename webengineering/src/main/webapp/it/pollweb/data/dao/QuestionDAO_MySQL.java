/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import it.pollweb.data.model.Question;
import it.pollweb.data.framework.data.DAO;
import it.pollweb.data.framework.data.DataException;
import it.pollweb.data.framework.data.DataLayer;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public class QuestionDAO_MySQL extends DAO implements QuestionDAO{

    public QuestionDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public Question createQuestion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question createQuestion(ResultSet rs) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question getQuestion(int question_key) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Question> getQuestion() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
