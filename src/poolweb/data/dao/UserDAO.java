package poolweb.data.dao;

import freemarker.template.DefaultArrayAdapter;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;

public interface UserDAO {

    List<User> getUsers() throws DataException;

    User createUser() throws DataException;

    User createUser(ResultSet res) throws DataException;

    void storeUser(User user) throws DataException;

    User getUser(int ID) throws DataException;

    User getUser(String email, String password) throws DataException;

    int getCount() throws DataException;

}
