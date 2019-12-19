package poolweb.data.impl;

import poolweb.data.model.Poll;
import poolweb.data.model.User;

public class PollImpl implements Poll {

    private int ID;
    private String title;
    private String opentext;
    private String closetext;
    private boolean openstatus;
    private boolean pollstatus;
    private String URL;
    private User user;

    public PollImpl() {
       ID = 0;
       pollstatus = false;
       openstatus = false;
    }


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int id) {
        ID = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getOpentext() {
        return opentext;
    }

    @Override
    public void setOpentext(String openText) {
        this.opentext = openText;
    }

    @Override
    public String getClosetext() {
        return closetext;
    }

    @Override
    public void setClosetext(String closeText) {
        this.closetext = closeText;
    }

    @Override
    public boolean getOpenstatus() {
       return openstatus;
    }

    @Override
    public void setOpenStatus(boolean status) {
        openstatus = status;
    }

    @Override
    public boolean getPollstatus() {
        return pollstatus;
    }

    @Override
    public void setPollstatus(boolean statusPool) {
        pollstatus = statusPool;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public void setURL(String url) {
        URL = url;
    }
}
