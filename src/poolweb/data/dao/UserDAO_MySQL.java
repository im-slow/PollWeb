package poolweb.data.dao;

import poolweb.data.model.Instance;
import poolweb.data.model.User;
import poolweb.data.proxy.InstanceProxy;
import poolweb.data.proxy.UserProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL extends DAO implements UserDAO {

    private final String SELECT_ALL_USER = "SELECT id FROM utente";
    private final String SELECT_USER_BY_ID = "SELECT * FROM utente WHERE id = ?";
    private final String SELECT_USER_BY_NAME_AND_PASSWORD ="SELECT * FROM utente where email = ? AND pwd = ?";
    private final String COUNT_ALL_USER = "SELECT COUNT(id) FROM utente";
    private final String INSERT_USER = "INSERT INTO utente (nome, email, pwd) " +
            "VALUES (?, ?, ?)";
    private final String UPDATE_USER = "UPDATE utente SET nome=?, email=?, pwd=?" +
            "WHERE ID=?";

    private PreparedStatement allUser;
    private PreparedStatement userByID;
    private PreparedStatement userByLogin;
    private PreparedStatement countUser;
    private PreparedStatement insertUser;
    private PreparedStatement updateUser;

    public UserDAO_MySQL(DataLayer d) { super(d);}

    @Override
    public void init() throws DataException {
        try {
            super.init();
            allUser = connection.prepareStatement(SELECT_ALL_USER);
            userByID = connection.prepareStatement(SELECT_USER_BY_ID);
            userByLogin = connection.prepareStatement(SELECT_USER_BY_NAME_AND_PASSWORD);
            countUser = connection.prepareStatement(COUNT_ALL_USER);
            insertUser = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            updateUser = connection.prepareStatement(UPDATE_USER);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            allUser.close();
            userByID.close();
            userByLogin.close();
            countUser.close();
            insertUser.close();
            updateUser.close();
        } catch (SQLException ex){
            //
        }
        super.destroy();
    }

    @Override
    public UserProxy createUser() {
        return new UserProxy(getDataLayer());
    }

    @Override
    public UserProxy createUser(ResultSet rs) {
        UserProxy a = createUser();
        try {
            a.setID(rs.getInt("ID"));
            a.setName(rs.getString("nome"));
            a.setEmail(rs.getString("email"));
            a.setPassword(rs.getString("pwd"));
            a.setRoleKey(3);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public void storeUser(User user) throws DataException {
        int id = user.getID();
        try {
            if (user.getID() > 0) {
                if (user instanceof UserProxy && !((UserProxy) user).isDirty()) {
                    return;
                }
                updateUser.setString(1, user.getName());
                updateUser.setString(2, user.getEmail());
                updateUser.setString(3, user.getPassword());
                updateUser.executeUpdate();
            } else {
                insertUser.setString(1, user.getName());
                insertUser.setString(2, user.getEmail());
                insertUser.setString(3, user.getPassword());
                if (insertUser.executeUpdate() == 1) {
                    try (ResultSet rs = insertUser.getGeneratedKeys()) {
                        if (rs.next()) {
                           id = rs.getInt(1);
                        }
                    }
                   user.setID(id);
                }
            }
            if (user instanceof UserProxy){
                ((UserProxy) user).setDirty(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataException("Unable to insert or update User", ex);
        }
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
    public User getUser(String mail, String password) throws DataException {
        try {
            userByLogin.setString(1, mail);
            userByLogin.setString(2, password);
            try (ResultSet rs = userByLogin.executeQuery()) {
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
    public int getCount() throws DataException {
        int count = 0;
        try {
            try (ResultSet rs = countUser.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("COUNT(id)");
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to count all User", ex);
        }
        return count;
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

