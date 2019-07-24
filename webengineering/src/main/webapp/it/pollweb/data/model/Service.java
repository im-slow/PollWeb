package it.pollweb.data.model;

import java.util.List;

public interface Service{

    String getName();

    String getDescription();

    List<Group> getGroups();

    void setName(String name);

    void setDescription(String description);

    void setGroups(List<Group> groups);

}