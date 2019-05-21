package it.pollweb.data.dao;

import it.pollweb.data.framework.data.DataException;
import it.pollweb.data.model.Answer;
import it.pollweb.data.model.Instance;
import it.pollweb.data.model.Poll;

import java.sql.ResultSet;
import java.util.List;

public interface InstanceDAO {

    Instance createInstance();

    Instance createInstance(ResultSet rs) throws DataException;

    Instance getInstance(int instance_key) throws DataException;

    void storeInstance(Instance instance) throws DataException;

    void deleteInstance(Instance instance) throws DataException;

}
