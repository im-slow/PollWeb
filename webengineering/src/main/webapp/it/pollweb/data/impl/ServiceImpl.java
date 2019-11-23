package it.pollweb.data.impl;

import it.pollweb.data.model.Group;
import it.pollweb.data.model.Service;

import java.util.List;

public class ServiceImpl implements Service {

    private String name;
    private String description;
    private List<Group> groups;

    public ServiceImpl(){
        name = "";
        description = "";
        groups = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}