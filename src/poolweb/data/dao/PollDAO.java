package poolweb.data.dao;

import poolweb.data.model.Poll;
import poolweb.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;

public interface PollDAO {

    public List<Poll> getAllPoll() throws DataException;

    public List<Poll> getPopoularPoll() throws DataException;

    public Poll getPollByID(int ID) throws DataException;

    public List<Poll> getPollByUserID(int userID) throws DataException;

    public Poll createPoll(ResultSet rs) throws DataException;

    public Poll createPoll();

    public void storePoll(Poll p) throws DataException;

    public void closeStatus(int state, int ID) throws DataException;

    int getCount() throws DataException;

}
