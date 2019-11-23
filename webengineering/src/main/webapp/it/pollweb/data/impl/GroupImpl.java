package it.pollweb.data.impl;

import it.pollweb.data.model.Group;
import it.pollweb.data.model.Service;

import java.util.List;

public class GroupImpl implements Group {

    private String name;
    private String description;
    private List<Service> services;

    public GroupImpl(){
        name = "";
        description = "";
        services = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}