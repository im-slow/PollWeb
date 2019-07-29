/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import it.pollweb.data.model.Question;
import framework.data.DataException;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public interface QuestionDAO {
    
    Question createQuestion();

    Question createQuestion(ResultSet rs) throws DataException;

    Question getQuestionByID(int question_key) throws DataException;

    List<Question> getQuestionsByPoll(int poll_key) throws DataException;

    List<Question> getQuestions() throws DataException;

    void storeQuestion(Question question) throws DataException;
    
}
