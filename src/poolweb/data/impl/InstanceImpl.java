package poolweb.data.impl;

import poolweb.data.model.Instance;
import poolweb.data.model.Poll;
import poolweb.data.model.User;

import java.util.Date;

public class InstanceImpl implements Instance {

    private int ID;
    private boolean userStatus;
    private Date submission;
    private Poll poll;
    private User user;

    public InstanceImpl() {
        ID = 0;
        userStatus = false;
        submission = null;
        poll = null;
        user= null;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int id) {
        this.ID = id;
    }

    @Override
    public boolean getUserStatus() {
        return userStatus;
    }

    @Override
    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public Date getSubmission() {
        return submission;
    }

    @Override
    public void setSubmission(Date submission) {
        this.submission = submission;
    }

    @Override
    public Poll getPoll() {
        return poll;
    }

    @Override
    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
