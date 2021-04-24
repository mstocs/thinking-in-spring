package org.example.thinking.in.spring.bean.factory;

import org.example.thinking.in.spring.ioc.overview.domain.User;

public interface UserFactory {
    default User createUser(){
        return User.createUser();
    };
}
