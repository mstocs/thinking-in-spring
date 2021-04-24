package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");

        User constantUser = beanFactory.getBean("constant-user", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(constantUser == user);
    }
}
