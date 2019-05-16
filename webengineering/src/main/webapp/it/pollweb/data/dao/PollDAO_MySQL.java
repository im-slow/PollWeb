/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import it.pollweb.data.model.Poll;
import it.pollweb.data.framework.data.DAO;
import it.pollweb.data.framework.data.DataException;
import it.pollweb.data.framework.data.DataLayer;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public class PollDAO_MySQL extends DAO implements PollDAO{

    public PollDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public Poll createPoll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poll createPoll(ResultSet rs) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Poll getPoll(int poll_key) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Poll> getPoll() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storePoll(Poll poll) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
