package poolweb.data.dao;

import poolweb.data.model.Role;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;

public interface RoleDAO {

    List<Role> getAllRole() throws DataException;

    Role getRoleByID(int ID) throws DataException;

    Role getRoleByUser(int ID) throws DataException;

    Role createRole(ResultSet rs) throws DataException;

    Role createRole();

    void storeUserRole(User user) throws DataException;

}
