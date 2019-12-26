package poolweb.data.proxy;

import poolweb.data.dao.PollDAO;
import poolweb.data.dao.UserDAO;
import poolweb.data.impl.InstanceImpl;
import poolweb.data.model.Poll;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstanceProxy extends InstanceImpl {

    protected boolean dirty;
    protected int poll_key = 0;
    protected int user_key = 0;

    protected DataLayer dataLayer;

    public InstanceProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
        this.poll_key = 0;
        this.user_key = 0;
    }

    @Override
    public Poll getPoll() {
        if (super.getPoll() == null && poll_key > 0) {
            try {
                super.setPoll(((PollDAO) dataLayer.getDAO(Poll.class)).getPollByID(poll_key));
            } catch (DataException ex) {
                Logger.getLogger(UserProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getPoll();
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
        this.dirty = true;
    }

    @Override
    public void setUserStatus(boolean userStatus){
        super.setUserStatus(userStatus);
        this.dirty = true;
    }

    @Override
    public void setSubmission(Date submission){
        super.setSubmission(submission);
        this.dirty = true;
    }

    @Override
    public void setPoll(Poll poll){
        super.setPoll(poll);
        this.dirty = true;
    }

    @Override
    public void setUser(User user){
        super.setUser(user);
        this.dirty = true;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setPollKey(int poll_key) {
        this.poll_key = poll_key;
        //resettiamo la cache del sondaggio
        super.setPoll(null);
    }

    public void setUserKey(int user_key) {
        this.user_key = user_key;
        //resettiamo la cache dell'utente
        super.setPoll(null);
    }
}
