package poolweb.data.proxy;

import poolweb.data.dao.UserDAO;
import poolweb.data.impl.PollImpl;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PollProxy extends PollImpl {

    protected boolean dirty;
    protected int user_key = 0;

    protected DataLayer dataLayer;

    public PollProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
        this.user_key = 0;
    }

    @Override
    public User getUser() {
        if (super.getUser() == null && user_key > 0) {
            try {
                super.setUser(((UserDAO) dataLayer.getDAO(User.class)).getUser(user_key));
            } catch (DataException ex) {
                Logger.getLogger(UserProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getUser();
    }

    @Override
    public void setID(int ID) {
        super.setID(ID);
        this.dirty = false;
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        this.dirty = true;
    }

    @Override
    public void setOpentext(String opentext) {
        super.setOpentext(opentext);
        this.dirty = true;
    }

    @Override
    public void setClosetext(String closeText) {
        super.setClosetext(closeText);
        this.dirty = true;
    }

    @Override
    public void setOpenStatus(boolean status) {
        super.setOpenStatus(status);
        this.dirty = true;
    }

    @Override
    public void setStatePoll(int statePoll) {
        super.setStatePoll(statePoll);
        this.dirty = true;
    }

   @Override
   public void setURL(String url) {
        super.setURL(url);
        this.dirty = true;
   }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setAuthorKey(int author_key) {
        this.user_key = author_key;
        //resettiamo la cache dell'autore
        //reset author cache
        super.setUser(null);
    }

}
