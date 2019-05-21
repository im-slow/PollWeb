package it.pollweb.data.proxy;

import it.pollweb.data.framework.data.DataLayer;
import it.pollweb.data.impl.InstanceImpl;

import java.util.Date;

public class InstanceProxy extends InstanceImpl {

    protected boolean dirty;
    protected DataLayer dataLayer;

    public InstanceProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
    }

    @Override
    public void setKey(int key) {
        super.setKey(key);
        this.dirty = true;
    }

    @Override
    public void setUserStatus(boolean userStatus) {
        super.setUserStatus(userStatus);
        this.dirty = true;
    }

    @Override
    public void setSubmission(Date submission) {
        super.setSubmission(submission);
        this.dirty = true;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }

}
