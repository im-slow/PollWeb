package poolweb.data.model;

public interface User {

    int getID();

    void setID(int ID);

    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String mail);

    String getPassword();

    void setPassword(String password);

    Role getRole();

    void setRole(Role role);
}
