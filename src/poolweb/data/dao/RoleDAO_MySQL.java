package poolweb.data.dao;

import org.omg.CORBA.DATA_CONVERSION;
import poolweb.data.model.Instance;
import poolweb.data.model.Poll;
import poolweb.data.model.Role;
import poolweb.data.model.User;
import poolweb.data.proxy.InstanceProxy;
import poolweb.data.proxy.PollProxy;
import poolweb.data.proxy.RoleProxy;
import poolweb.data.proxy.UserProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.lang.ref.PhantomReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO_MySQL extends DAO implements RoleDAO{

    private final String SELECT_ALL_ROLE = "SELECT ID as IDROLE FROM roles";
    private final String SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE ID=?";
    private final String SELECT_ROLE_BY_USER = "SELECT roles.* from roles INNER JOIN users_roles ON roles.ID=users_roles.IDroles AND users_roles.IDusers = ?";
    private final String UPDATE_USER_ROLE = "INSERT INTO users_roles (IDusers, IDroles) " +
            "VALUES (?, ?)";
    private final String INSERT_USER_ROLE = "UPDATE users_roles SET IDusers=?, IDroles=?" +
            "WHERE ID=?";

    private PreparedStatement allRole;
    private PreparedStatement roleByID;
    private PreparedStatement roleByUser;
    private PreparedStatement updateUserRole;
    private PreparedStatement insertUserRole;

    public RoleDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            allRole = connection.prepareStatement(SELECT_ALL_ROLE);
            roleByID = connection.prepareStatement(SELECT_ROLE_BY_ID);
            roleByUser = connection.prepareStatement(SELECT_ROLE_BY_USER);
            updateUserRole = connection.prepareStatement(UPDATE_USER_ROLE);
            insertUserRole = connection.prepareStatement(INSERT_USER_ROLE, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            allRole.close();
            roleByID.close();
            roleByUser.close();
            updateUserRole.close();
            insertUserRole.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public Role getRoleByID(int ID) throws DataException {
        try {
            roleByID.setInt(1, ID);
            try (ResultSet rs = roleByID.executeQuery()) {
                if (rs.next()) {
                    return createRole(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by ID", ex);
        }
        return null;
    }

    public Role getRoleByUser(User user) throws DataException {
        try {
            roleByUser.setInt(1, user.getID());
            try (ResultSet rs = roleByUser.executeQuery()) {
                if (rs.next()) {
                    return createRole(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load role by userID", ex);
        }
        return null;
    }

    @Override
    public List<Role> getAllRole() throws DataException {
        List<Role> result = new ArrayList<>();
        try {
            try (ResultSet rs = allRole.executeQuery()) {
                while (rs.next()) {
                    result.add(getRoleByID(rs.getInt("IDROLE")));
                }
            }
        } catch (SQLException ex) {
            //
        }
        return result;
    }

    @Override
    public RoleProxy createRole() {
        return new RoleProxy(getDataLayer());
    }

    @Override
    public RoleProxy createRole(ResultSet rs) throws DataException {
        try {
            RoleProxy a = createRole();
            a.setID(rs.getInt("id"));
            a.setName(rs.getString("nome"));
            a.setDescription(rs.getString("description"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Role object from ResultSet", ex);
        }
    }

    @Override
    public void storeUserRole(User user) throws DataException {
        int id = 0;
        try {
            if (user.getID() > 0) {
                if (user instanceof UserProxy && ((UserProxy) user).isDirty()) {
                    return;
                }
                updateUserRole.setInt(1, user.getID());
                updateUserRole.setInt(2, user.getRole().getID());
                updateUserRole.executeUpdate();
            } else {
                insertUserRole.setInt(1, user.getID());
                insertUserRole.setInt(2, user.getRole().getID());
                if (insertUserRole.executeUpdate() == 1) {
                    try (ResultSet rs = insertUserRole.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                    //set new user role
                }
            }
            if (user instanceof UserProxy){
                ((UserProxy) user).setDirty(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataException("Unable to insert or update Instance", ex);
        }
    }
}
