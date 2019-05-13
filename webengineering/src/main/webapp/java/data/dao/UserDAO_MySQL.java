/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.dao;

import java.data.model.User;
import java.framework.data.DAO;
import java.framework.data.DataException;
import java.framework.data.DataLayer;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public class UserDAO_MySQL extends DAO implements UserDAO{

    public UserDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public User createUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User createUser(ResultSet rs) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(int user_key) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUser() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
