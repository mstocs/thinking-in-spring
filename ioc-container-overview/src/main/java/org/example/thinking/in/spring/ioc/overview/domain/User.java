package org.example.thinking.in.spring.ioc.overview.domain;

import org.example.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

public class User {
    private Long id;

    private String name;

    private City city;

    private Resource configFileLocation;

    public static User createUser() {
        User user = new User();
        user.setName("static-method-user");
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

}
