/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.data.dao;

import java.data.model.User;
import java.framework.data.DataException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author andy4
 */
public interface UserDAO {
    
    User createUser();

    User createUser(ResultSet rs) throws DataException;

    User getUser(int user_key) throws DataException;

    List<User> getUsers() throws DataException;
}
