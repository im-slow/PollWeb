package poolweb.data.dao;

import poolweb.data.model.Instance;
import poolweb.data.model.Poll;
import poolweb.data.model.User;
import poolweb.data.proxy.InstanceProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InstanceDAO_MySQL extends DAO implements InstanceDAO{

    private final String SELECT_ALL_INSTANCE = "SELECT * FROM instance WHERE ID=?";
    private final String SELECT_INSTANCE_BY_ID = "SELECT ID as IDINSTANCE FROM instance";
    private final String SELECT_INSTANCE_BY_USER = "SELECT instance.* from instance WHERE IDutente=?";
    private final String SELECT_INSTANCE_BY_POLL = "SELECT instance.* from instance WHERE IDpoll=?";
    private final String SELECT_INSTANCE_BY_OK = "SELECT instance.* from instance WHERE IDutente=? AND IDpoll=?";
    private final String INSERT_INSTANCE = "INSERT INTO instance (userStatus, submission, IDutente, IDpoll) " +
            "VALUES (?, ?, ?, ?)";
    private final String UPDATE_INSTANCE = "UPDATE instance SET userStatus=?, submission=?, IDutente=?, IDpoll=? " +
            "WHERE ID=?";

    private PreparedStatement allInstance;
    private PreparedStatement instanceByID;
    private PreparedStatement instanceByUser;
    private PreparedStatement instanceByPoll;
    private PreparedStatement instanceByOKey;
    private PreparedStatement insertInstance;
    private PreparedStatement updateInstance;

    public InstanceDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            allInstance = connection.prepareStatement(SELECT_ALL_INSTANCE);
            instanceByID = connection.prepareStatement(SELECT_INSTANCE_BY_ID);
            instanceByUser = connection.prepareStatement(SELECT_INSTANCE_BY_USER);
            instanceByPoll = connection.prepareStatement(SELECT_INSTANCE_BY_POLL);
            instanceByOKey = connection.prepareStatement(SELECT_INSTANCE_BY_OK);
            insertInstance = connection.prepareStatement(INSERT_INSTANCE, Statement.RETURN_GENERATED_KEYS);
            updateInstance = connection.prepareStatement(UPDATE_INSTANCE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            allInstance.close();
            instanceByID.close();
            instanceByUser.close();
            instanceByPoll.close();
            insertInstance.close();
            updateInstance.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public Instance getInstanceByID(int ID) throws DataException {
        try {
            instanceByID.setInt(1, ID);
            try (ResultSet rs = instanceByID.executeQuery()) {
                if (rs.next()) {
                    return createInstance(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Instance by ID", ex);
        }
        return null;
    }

    @Override
    public List<Instance> getInstancesByPoll(Poll poll) throws DataException {
        List<Instance> result = new ArrayList<>();
        try {
            instanceByPoll.setInt(1, poll.getID());
            try (ResultSet rs = instanceByPoll.executeQuery()) {
                while (rs.next()) {
                    result.add(createInstance(rs));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Instance by pollID", ex);
        }
        return result;
    }

    @Override
    public List<Instance> getInstancesByUser(User user) throws DataException {
        List<Instance> result = new ArrayList<>();
            try {
                instanceByUser.setInt(1, user.getID());
                try (ResultSet rs = instanceByUser.executeQuery()) {
                while (rs.next()) {
                    result.add(createInstance(rs));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Instance by userID", ex);
        }
        return result;
    }

    @Override
    public Instance getInstanceByOKey(User user, Poll poll) throws DataException {
        try {
            instanceByOKey.setInt(1, user.getID());
            instanceByOKey.setInt(2, poll.getID());
            try (ResultSet rs = instanceByOKey.executeQuery()) {
                if (rs.next()) {
                    return createInstance(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Instance by userID or pollID", ex);
        }
        return null;
    }

    @Override
    public Instance getInstanceByUser(User user) throws DataException {
        try {
            instanceByUser.setInt(1, user.getID());
            try (ResultSet rs = instanceByOKey.executeQuery()) {
                if (rs.next()) {
                    return createInstance(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Instance by userID", ex);
        }
        return null;
    }

    @Override
    public Instance getInstanceByPoll(Poll poll) throws DataException {
        try {
            instanceByOKey.setInt(2, poll.getID());
            try (ResultSet rs = instanceByOKey.executeQuery()) {
                if (rs.next()) {
                    return createInstance(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Instance by pollID", ex);
        }
        return null;
    }

    @Override
    public List<Instance> getAllInstance() throws DataException {
        List<Instance> result = new ArrayList<>();
        try {
            try (ResultSet rs = allInstance.executeQuery()) {
                while (rs.next()) {
                    result.add(getInstanceByID(rs.getInt("IDINSTANCE")));
                }
            }
        } catch (SQLException ex) {
            //
        }
        return result;
    }

    @Override
    public InstanceProxy createInstance() {
        return new InstanceProxy(getDataLayer());
    }

    @Override
    public InstanceProxy createInstance(ResultSet rs) throws DataException {
        try {
            InstanceProxy a = createInstance();
            a.setID(rs.getInt("id"));
            a.setUserStatus(rs.getBoolean("userStatus"));
            a.setSubmission(rs.getDate("submission"));
            a.setUserKey(rs.getInt("IDutente"));
            a.setPollKey(rs.getInt("IDpoll"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Instance object from ResultSet", ex);
        }
    }

    @Override
    public void storeInstance(Instance instance) throws DataException {
        int id = instance.getID();
        try {
            if (instance.getID() > 0) {
                if (instance instanceof InstanceProxy && !((InstanceProxy) instance).isDirty()) {
                    return;
                }
                updateInstance.setBoolean(1, instance.getUserStatus());
                updateInstance.setDate(2,  new java.sql.Date(instance.getSubmission().getTime()));
                updateInstance.setInt(3, instance.getUser().getID());
                updateInstance.setInt(4, instance.getPoll().getID());
                updateInstance.setInt(5, instance.getID());
                updateInstance.executeUpdate();
            } else {
                insertInstance.setBoolean(1, instance.getUserStatus());
                if (instance.getSubmission() != null) {
                    insertInstance.setDate(2,  new java.sql.Date(instance.getSubmission().getTime()));
                } else {
                    insertInstance.setNull(2, java.sql.Types.DATE);
                }
                insertInstance.setInt(3, instance.getUser().getID());
                insertInstance.setInt(4, instance.getPoll().getID());
                if (insertInstance.executeUpdate() == 1) {
                    try (ResultSet rs = insertInstance.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                    instance.setID(id);
                }
            }
            if (instance instanceof InstanceProxy){
                ((InstanceProxy) instance).setDirty(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataException("Unable to insert or update Instance", ex);
        }
    }
}
