package poolweb.data.dao;

import poolweb.data.model.User;
import poolweb.data.proxy.UserProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL extends DAO implements UserDAO {

    private final String SELECT_ALL_USER = "SELECT id FROM utente";
    private final String SELECT_USER_BY_ID = "SELECT * FROM utente WHERE id = ?";

    private PreparedStatement allUser;
    private PreparedStatement userByID;

    public UserDAO_MySQL(DataLayer d) { super(d);}

    @Override
    public void init() throws DataException {
        try {
            super.init();
            allUser = connection.prepareStatement(SELECT_ALL_USER);
            userByID = connection.prepareStatement(SELECT_USER_BY_ID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            allUser.close();
        } catch (SQLException ex){
            //
        }
        super.destroy();
    }

    @Override
    public UserProxy createUser(ResultSet rs) {
        UserProxy a = new UserProxy(getDataLayer());
        try {
            a.setName(rs.getString("nome"));
            a.setEmail(rs.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public User getUser(int ID) throws DataException {
        try {
            userByID.setInt(1, ID);
            try (ResultSet rs = userByID.executeQuery()) {
                if (rs.next()) {
                    return createUser(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load User by ID", ex);
        }
        return null;
    }

    @Override
    public List<User> getUsers() throws DataException {
        List<User> listausers = new ArrayList<>();
        try {
            try (ResultSet rs = allUser.executeQuery()) {
                while(rs.next()) {
                    listausers.add(getUser(rs.getInt("id")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load all User", ex);
        }
        return listausers;
    }

}

