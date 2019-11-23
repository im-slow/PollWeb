package poolweb.data.proxy;

import poolweb.data.impl.UserImpl;
import poolweb.framework.data.DataLayer;

public class UserProxy extends UserImpl {

    protected boolean dirty;

    protected DataLayer datalayer;

    public UserProxy(DataLayer d) {
        super();
        this.datalayer = d;
        this.dirty = false;
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

    //Proxy methods
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
