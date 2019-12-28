package poolweb.data.impl;

import poolweb.data.model.Role;
import poolweb.data.model.User;

public class UserImpl implements User {

    private int ID;
    private String name;
    private String email;
    private String password;
    private Role role;

    public UserImpl() {
        name = "";
        email = "";
        password = "";
    }

    @Override
    public int getID() { return ID; }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String mail) {
        this.email = mail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }
}
