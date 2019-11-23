package poolweb.data.model;

public interface Poll {

    public int getID();

    public void setID(int id);

    public String getTitle();

    public void setTitle(String title);

    public String getOpentext();

    public void setOpentext(String openText);

    public String getClosetext();

    public void setClosetext(String closeText);

    public boolean getOpenstatus();

    public void setOpenStatus(boolean status);

    public boolean getPollstatus();

    public void setPollstatus(boolean statusPool);

    public User getUser();

    public void setUser(User user);

}
