package it.pollweb.data.dao;

import it.pollweb.data.framework.data.DAO;
import it.pollweb.data.framework.data.DataException;
import it.pollweb.data.framework.data.DataLayer;
import it.pollweb.data.model.Instance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InstanceDAO_MySQL extends DAO implements InstanceDAO {

    private PreparedStatement sInstance, sInstanceByID, sInstanceByUser;
    private PreparedStatement iInstance, uInstance, dInstance;

    public InstanceDAO_MySQL(DataLayer d) {
        super(d);
    }


    @Override
    public Instance createInstance() {
        return null;
    }

    @Override
    public Instance createInstance(ResultSet rs) throws DataException {
        return null;
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
