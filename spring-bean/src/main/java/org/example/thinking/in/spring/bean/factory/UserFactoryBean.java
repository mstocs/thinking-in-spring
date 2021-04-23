package org.example.thinking.in.spring.bean.factory;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * User对象Bean的实现
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
