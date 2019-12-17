package poolweb.data.impl;

import poolweb.data.model.Role;

public class RoleImpl implements Role {

    private int ID;
    private String name;
    private String description;

    public RoleImpl() {
        ID = 0;
        name = "";
        description = "";
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
