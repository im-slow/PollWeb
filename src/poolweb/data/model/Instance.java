package poolweb.data.model;


import java.util.Date;

public interface Instance {

    public int getID();

    public void setID(int id);

    public boolean getUserStatus();

    public void setUserStatus(boolean userStatus);

    public Date getSubmission();

    public void setSubmission(Date submission);

    public Poll getPoll();

    public void setPoll(Poll poll);

    public User getUser();

    public void setUser(User user);
}
