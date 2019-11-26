package poolweb.data.dao;

import poolweb.data.model.Poll;
import poolweb.data.proxy.PollProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PollDAO_MySQL extends DAO implements PollDAO {

    private final String SELECT_ALL_POLLID = "SELECT ID as IDPOLL FROM poll LIMIT 3";
    private final String SELECT_POLL_BY_ID = "SELECT * FROM poll WHERE ID = ?";
    private final String SELECT_POLL_BY_USER_ID = "SELECT * FROM poll WHERE IDuser = ? LIMIT 5";

    private PreparedStatement allPoll;
    private PreparedStatement pollByID;
    private PreparedStatement pollByUserID;

    public PollDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            allPoll = connection.prepareStatement(SELECT_ALL_POLLID);
            pollByID = connection.prepareStatement(SELECT_POLL_BY_ID);
            pollByUserID = connection.prepareStatement(SELECT_POLL_BY_USER_ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Poll> getAllPoll() throws DataException {
       List<Poll> result = new ArrayList<>();
       try {
           try (ResultSet rs = allPoll.executeQuery()) {
               while (rs.next()) {
                   result.add(getPollByID(rs.getInt("IDPOLL")));
               }
           }
       } catch (SQLException ex) {
           //
       }
       return result;
    }

    @Override
    public Poll getPollByID(int ID) throws DataException {
        try {
            pollByID.setInt(1, ID);
            try (ResultSet rs = pollByID.executeQuery()) {
                if (rs.next()) {
                    return createPoll(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by ID", ex);
        }
        return null;
    }

    @Override
    public List<Poll> getPollByUserID(int userID) throws DataException {
        List<Poll> result = new ArrayList<>();
        try {
            pollByUserID.setInt(1, userID);
            try (ResultSet rs = pollByUserID.executeQuery()) {
                while (rs.next()) {
                    result.add(createPoll(rs));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by UserID", ex);
        }
        return result;
    }

    @Override
    public PollProxy createPoll() {
        return new PollProxy(getDataLayer());
    }

    @Override
    public PollProxy createPoll(ResultSet rs) throws DataException {
        try {
            PollProxy a = createPoll();
            a.setID(rs.getInt("id"));
            a.setTitle(rs.getString("title"));
            a.setOpentext(rs.getString("openText"));
            a.setPollstatus(rs.getBoolean("statePoll"));
            a.setAuthorKey(rs.getInt("IDuser"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Poll object from ResultSet", ex);
        }
    }
}
