/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.iw.pollweb.data.dao;

import it.univaq.iw.pollweb.data.model.Answer;
import it.univaq.iw.pollweb.data.model.Poll;
import it.univaq.iw.pollweb.framework.data.DataException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public interface AnswerDAO {
    
    Answer createAnswer();

    Answer createAnswer(ResultSet rs) throws DataException;

    Answer getAnswer(int answer_key) throws DataException;

    List<Answer> getAnswers() throws DataException;
    
    List<Answer> getAnswers(Poll poll) throws DataException;

    void storeAnswer(Answer answer) throws DataException;
    
    void deleteAnswer(Answer answer) throws DataException;
    
}
