/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.dao;

import java.data.model.User;
import java.data.proxy.UserProxy;
import java.framework.data.DAO;
import java.framework.data.DataException;
import java.framework.data.DataLayer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andy4
 */
public class UserDAO_MySQL extends DAO implements UserDAO{
    
    private PreparedStatement sUsers, sUserByID, sUserByEmail;
    private PreparedStatement iUser, uUser, dUser;
    private PreparedStatement uUserRole;

    public UserDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            sUserByID = connection.prepareStatement("SELECT * FROM user WHERE ID=?");
            sUserByEmail = connection.prepareStatement("SELECT * FROM user WHERE ID=?");
            sUsers = connection.prepareStatement("SELECT ID FROM user");
       
            iUser = connection.prepareStatement("INSERT INTO user (nome,email,pwd) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uUser = connection.prepareStatement("UPDATE user SET nome=?,email=?,pwd=? WHERE ID=?");
            dUser = connection.prepareStatement("DELETE FROM user WHERE ID=?");

            uUserRole = connection.prepareStatement("UPDATE appartiene SET IDuser=?,IDgruppo=? WHERE IDuser=?"); // ????
        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
         }
    }
    
    @Override
    public void destroy() throws DataException {
        try {
            sUserByID.close();
            sUserByEmail.close();
            sUsers.close();
            iUser.close();
            uUser.close();
            dUser.close();
            uUserRole.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }
    
    @Override
    public UserProxy createUser() {
        return new UserProxy(getDataLayer());
    }
    
    @Override
    public UserProxy createUser(ResultSet rs) throws DataException {
        try {
            UserProxy a = createUser();
            a.setKey(rs.getInt("ID"));
            a.setName(rs.getString("name"));
            a.setEmail(rs.getString("email"));
            a.setPassword(rs.getString("password"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create author object form ResultSet", ex);
        }
    }

    @Override
    public User getUser(int user_key) throws DataException {
        try {
            sUserByID.setInt(1, user_key);
            try (ResultSet rs = sUserByID.executeQuery()) {
                if (rs.next()) {
                    return createUser(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load user by ID", ex);
        }
        return null;    
    }

    @Override
    public List<User> getUsers() throws DataException {
        List<User> result = new ArrayList();
        
        try (ResultSet rs = sUsers.executeQuery()) {
            while (rs.next()) {
                result.add(getUser(rs.getInt("ID")));
            }
        } catch (SQLException ex){
            throw new DataException("Unable to load users", ex);    
        }
        return null;
    }    
}
