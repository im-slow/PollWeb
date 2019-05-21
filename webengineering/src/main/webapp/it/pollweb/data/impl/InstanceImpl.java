package it.pollweb.data.impl;

import it.pollweb.data.model.Instance;

import java.util.Date;

public class InstanceImpl implements Instance {

    private int key;
    boolean userStatus;
    Date submission;

    public InstanceImpl(){
        key = 0;
        userStatus = true;
        submission = null;
    }


    @Override
    public int getKey() {
        return key;
    }

    @Override
    public boolean getUserStatus() {
        return userStatus;
    }

    @Override
    public Date getSubmission() {
        return submission;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public void setUserStatus(boolean setUserStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public void setSubmission(Date submission) {
        this.submission = submission;
    }
}
