/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import it.pollweb.data.model.Answer;
import it.pollweb.data.model.Poll;
import framework.data.DataException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public interface AnswerDAO {
    
    Answer createAnswer();

    Answer createAnswer(ResultSet rs) throws DataException;

    Answer getAnswerByID(int answer_key) throws DataException;

    List<Answer> getAnswers() throws DataException;
    
    List<Answer> getAnswersByQuestion(int question_key) throws DataException;

    void storeAnswer(Answer answer) throws DataException;
    
}
