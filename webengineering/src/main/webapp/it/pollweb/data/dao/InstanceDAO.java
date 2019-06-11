package it.pollweb.data.dao;

import framework.data.DataException;
import it.pollweb.data.model.Instance;

import java.sql.ResultSet;

public interface InstanceDAO {

    Instance createInstance();

    Instance createInstance(ResultSet rs) throws DataException;

    Instance getInstance(int instance_key) throws DataException;

    void storeInstance(Instance instance) throws DataException;

    void deleteInstance(Instance instance) throws DataException;

}
