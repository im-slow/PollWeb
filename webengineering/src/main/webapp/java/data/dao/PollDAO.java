/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD

package it.univaq.iw.pollweb.data.dao;
=======
package java.data.dao;
>>>>>>> 0881fc88e099d7724a7d4273a4c43a6bac869b49

import java.data.model.Poll;
import java.framework.data.DataException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */

public interface PollDAO {
    
    Poll createPoll();

    Poll createPoll(ResultSet rs) throws DataException;

    Poll getPoll(int poll_key) throws DataException;

    List<Poll> getPoll() throws DataException;

    void storePoll(Poll poll) throws DataException;
    
}
