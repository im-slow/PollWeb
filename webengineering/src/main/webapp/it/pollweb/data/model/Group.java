package it.pollweb.data.model;

import java.util.List;

public interface Group{

    String getName();

    String getDescription();

    List<Service> getServices();

    void setName(String name);

    void setDescription(String description);

    void setServices(List<Service> services);

}