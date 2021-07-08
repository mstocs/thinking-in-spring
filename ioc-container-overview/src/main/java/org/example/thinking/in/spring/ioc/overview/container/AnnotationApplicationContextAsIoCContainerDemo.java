package org.example.thinking.in.spring.ioc.overview.container;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // build BeanFactory Container
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);

        //start application context
        applicationContext.refresh();

        lookupByCollectionType(applicationContext);

        // close application context
        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("spring");
        return user;
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User类型的数据： " + users);
        }
    }
}
