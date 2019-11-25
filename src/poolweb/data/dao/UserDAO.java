package poolweb.data.dao;

import poolweb.data.model.User;
import poolweb.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;

public interface UserDAO {

    List<User> getUsers() throws DataException;

    User createUser(ResultSet res) throws DataException;

    User getUser(int ID) throws DataException;

    User getUser(String name, String password) throws DataException;

}
