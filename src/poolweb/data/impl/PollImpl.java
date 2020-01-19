package poolweb.data.impl;

import poolweb.data.model.Poll;
import poolweb.data.model.User;

public class PollImpl implements Poll {

    private int ID;
    private String title;
    private String opentext;
    private String closetext;
    private boolean openstatus;
    private int statePoll;
    private String URL;
    private User user;

    public PollImpl() {
       ID = 0;
       statePoll = 0;
       openstatus = false;
       URL = "";
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
    public int getStatePoll() {
        return statePoll;
    }

    @Override
    public void setStatePoll(int statePoll) {
            this.statePoll = statePoll;
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
        this.URL = url;
    }
}
