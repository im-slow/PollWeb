package it.pollweb.data.dao;

import framework.data.DAO;
import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.model.Instance;
import it.pollweb.data.proxy.InstanceProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InstanceDAO_MySQL extends DAO implements InstanceDAO {

    private PreparedStatement sInstance, sInstanceByID, sInstanceByUser, sInstances;
    private PreparedStatement iInstance, uInstance, dInstance;

    public InstanceDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            sInstance = connection.prepareStatement("SELECT * FROM intance");
            sInstanceByID = connection.prepareStatement("SELECT * FROM instance WHERE ID=?");
            sInstanceByUser = connection.prepareStatement("SELECT * FROM instance JOIN user WHERE user.nome=?, instance.userID=user.ID"); //?
            sInstances = connection.prepareStatement("SELECT ID FROM instance");

            iInstance = connection.prepareStatement("INSERT INTO instance (userStatus,submission,IDutente,IDpoll) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uInstance = connection.prepareStatement("UPDATE instance SET userStatus=?,submission=?,IDutente=?, IDpoll=? WHERE ID=?");
            dInstance = connection.prepareStatement("DELETE FROM instance WHERE ID=?");

        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sInstance.close();
            sInstanceByID.close();
            sInstanceByUser.close();

            iInstance.close();
            uInstance.close();
            dInstance.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public InstanceProxy createInstance() {
        return new InstanceProxy(getDataLayer());
    }

    @Override
    public InstanceProxy createInstance(ResultSet rs) throws DataException {
        try{
            InstanceProxy i = createInstance();
            i.setKey(rs.getInt("ID"));
            i.setAnswer(rs.getString("answer"));
        } catch (SQLException ex) {
            throw new DataException("Unable to create poll object form ResultSet", ex);
        }
    }

    @Override
    public Instance getInstance(int instance_key) throws DataException {
        return null;
    }

    @Override
    public void storeInstance(Instance instance) throws DataException {

    }

    @Override
    public void deleteInstance(Instance instance) throws DataException {

    }
}
