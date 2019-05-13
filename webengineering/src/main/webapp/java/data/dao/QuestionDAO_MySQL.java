/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.dao;

import java.data.model.Question;
import java.framework.data.DAO;
import java.framework.data.DataException;
import java.framework.data.DataLayer;
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
