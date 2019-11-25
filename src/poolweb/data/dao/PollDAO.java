package poolweb.data.dao;

import poolweb.data.model.Poll;
import poolweb.data.proxy.PollProxy;
import poolweb.framework.data.DataException;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.List;

public interface PollDAO {

    public List<Poll> getAllPoll() throws DataException;

    public List<Poll> getAllPoll(int userID) throws DataException;

    public Poll getPollByID(int ID) throws DataException;

    public Poll getPollByUserID(int userID) throws DataException;

    public Poll createPoll(ResultSet rs) throws DataException;

    public Poll createPoll();

}
