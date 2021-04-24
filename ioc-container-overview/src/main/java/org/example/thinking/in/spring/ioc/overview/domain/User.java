package org.example.thinking.in.spring.ioc.overview.domain;

import org.example.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.List;

public class User {
    private Long id;

    private String name;

    private City city;

    private Resource configFileLocation;

    private List<City> lifeCities;

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

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

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                ", lifeCities=" + lifeCities +
                '}';
    }
}
