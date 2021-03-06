package poolweb.data.dao;

import poolweb.data.model.Poll;
import poolweb.data.proxy.PollProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PollDAO_MySQL extends DAO implements PollDAO {

    private final String SELECT_ALL_POLLID = "SELECT ID as IDPOLL FROM poll WHERE statePoll = 1 AND openPoll=1";
    private final String SELECT_POPOULAR_POLLID = "SELECT ID as IDPOLL FROM poll WHERE statePoll=1 AND openPoll=1 LIMIT 3";
    private final String SELECT_POLL_BY_ID = "SELECT * FROM poll WHERE ID = ?";
    private final String SELECT_POLL_BY_USER_ID = "SELECT * FROM poll WHERE IDuser = ?";
    private final String INSERT_POLL = "INSERT INTO poll (title, openText, closeText, openPoll, statePoll, URLPoll, IDuser)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_POLL = "UPDATE poll SET title=?, openText=?, closeText=?, openPoll=?, statePoll=?, URLPoll=?)" +
            "WHERE ID=?";
    private final String PUBLISH_POLL = "UPDATE poll SET statePoll=? WHERE ID=?";
    private final String COUNT_POLL = "SELECT COUNT(id) FROM poll";

    private PreparedStatement allPoll;
    private PreparedStatement popoularPoll;
    private PreparedStatement pollByID;
    private PreparedStatement pollByUserID;
    private PreparedStatement insertPoll;
    private PreparedStatement updatePoll;
    private PreparedStatement publishPoll;
    private PreparedStatement countPoll;

    public PollDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            popoularPoll = connection.prepareStatement(SELECT_POPOULAR_POLLID);
            allPoll = connection.prepareStatement(SELECT_ALL_POLLID);
            pollByID = connection.prepareStatement(SELECT_POLL_BY_ID);
            pollByUserID = connection.prepareStatement(SELECT_POLL_BY_USER_ID);
            insertPoll = connection.prepareStatement(INSERT_POLL, Statement.RETURN_GENERATED_KEYS);
            updatePoll = connection.prepareStatement(UPDATE_POLL);
            publishPoll = connection.prepareStatement(PUBLISH_POLL);
            countPoll = connection.prepareStatement(COUNT_POLL);
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
    public List<Poll> getPopoularPoll() throws DataException {
        List<Poll> result = new ArrayList<>();
        try {
            try (ResultSet rs = popoularPoll.executeQuery()) {
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
    public int getCount() throws DataException {
        int count = 0;
        try {
            try (ResultSet rs = countPoll.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("COUNT(id)");
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to count all Poll", ex);
        }
        return count;
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
    public void storePoll(Poll poll) throws DataException {
        int id = poll.getID();
        try {
            if (poll.getID() > 0) {
                if (poll instanceof PollProxy && ((PollProxy) poll).isDirty()) {
                    return;
                }
                updatePoll.setString(1, poll.getTitle());
                updatePoll.setString(2, poll.getOpentext());
                updatePoll.setString(3, poll.getClosetext());
                updatePoll.setBoolean(4, poll.getOpenstatus());
                updatePoll.setInt(5, poll.getStatePoll());
                updatePoll.setString(6, poll.getURL());
                updatePoll.setInt(7, poll.getUser().getID());
                updatePoll.executeUpdate();
            } else {
                insertPoll.setString(1, poll.getTitle());
                insertPoll.setString(2, poll.getOpentext());
                insertPoll.setString(3, poll.getClosetext());
                insertPoll.setBoolean(4, poll.getOpenstatus());
                insertPoll.setInt(5, poll.getStatePoll());
                insertPoll.setString(6, poll.getURL());
                insertPoll.setInt(7, poll.getUser().getID());
                if (insertPoll.executeUpdate() == 1) {
                    try (ResultSet rs = insertPoll.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                    poll.setID(id);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataException("Unable to insert or update Poll", ex);
        }
    }

    @Override
    public void closeStatus(int state, int ID) throws DataException {
        try {
            publishPoll.setInt(1, state);
            publishPoll.setInt(2, ID);
            publishPoll.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
           throw new DataException("Unable to publish poll", e);
        }
    }

    @Override
    public PollProxy createPoll(ResultSet rs) throws DataException {
        try {
            PollProxy a = createPoll();
            a.setID(rs.getInt("id"));
            a.setTitle(rs.getString("title"));
            a.setOpentext(rs.getString("openText"));
            a.setClosetext(rs.getString("closeText"));
            a.setStatePoll(rs.getInt("statePoll"));
            a.setOpenStatus(rs.getBoolean("openPoll"));
            a.setURL(rs.getString("URLPoll"));
            a.setAuthorKey(rs.getInt("IDuser"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Poll object from ResultSet", ex);
        }
    }
}
