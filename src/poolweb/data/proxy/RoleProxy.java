package poolweb.data.proxy;

import poolweb.data.impl.RoleImpl;
import poolweb.framework.data.DataLayer;

public class RoleProxy extends RoleImpl {

    protected boolean dirty;

    protected DataLayer datalayer;

    public RoleProxy(DataLayer d) {
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
    public void setName(String name) {
        super.setName(name);
        this.dirty = true;
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
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
