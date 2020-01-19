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

    public int getStatePoll();

    public void setStatePoll(int statePoll);

    public User getUser();

    public void setUser(User user);

    public String getURL();

    public void setURL(String url);

}
