/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.dao;

import java.data.model.Question;
import java.framework.data.DataException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public interface QuestionDAO {
    
    Question createQuestion();

    Question createQuestion(ResultSet rs) throws DataException;

    Question getQuestion(int question_key) throws DataException;

    List<Question> getQuestion() throws DataException;
    
}
