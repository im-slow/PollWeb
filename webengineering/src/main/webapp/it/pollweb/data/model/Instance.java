package it.pollweb.data.model;


import java.util.Date;

public interface Instance {

    int getKey();

    boolean getUserStatus();

    Date getSubmission();

    void setKey(int key);

    void setUserStatus(boolean setUserStatus);

    void setSubmission(Date submission);

}
