package poolweb.data.dao;

import poolweb.data.model.Instance;
import poolweb.data.model.Poll;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;

public interface InstanceDAO {

    List<Instance> getAllInstance() throws DataException;

    Instance getInstanceByID(int ID) throws DataException;

    List<Instance> getInstanceByUser(User user) throws DataException;

    List<Instance> getInstanceByPoll(Poll poll) throws DataException;

    Instance getInstanceByOKey(User user, Poll poll) throws DataException;

    Instance createInstance(ResultSet rs) throws DataException;

    Instance createInstance();

    void storeInstance(Instance instance) throws DataException;

}
