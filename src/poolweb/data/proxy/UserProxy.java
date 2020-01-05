package poolweb.data.proxy;

import poolweb.data.dao.RoleDAO;
import poolweb.data.dao.UserDAO;
import poolweb.data.impl.UserImpl;
import poolweb.data.model.Role;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserProxy extends UserImpl {

    protected boolean dirty;

    protected DataLayer datalayer;
    protected int role_key;

    public UserProxy(DataLayer d) {
        super();
        this.role_key = 0;
        this.datalayer = d;
        this.dirty = false;
    }

    @Override
    public Role getRole() {
        if (super.getRole() == null && role_key > 0) {
            try {
                super.setRole(((RoleDAO) datalayer.getDAO(Role.class)).getRoleByUser(super.getID()));
            } catch (DataException ex) {
                Logger.getLogger(UserProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getRole();
    }

    @Override
    public void setID(int ID) {
        super.setID(ID);
        this.dirty = true;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        this.dirty = true;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.dirty = true;
    }

    public void setRoleKey(int role_key) {
        this.role_key = role_key;
        //resettiamo la cache dell'autore
        //reset author cache
        super.setRole(null);
    }

    //Proxy methods
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
