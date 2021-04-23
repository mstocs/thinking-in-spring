package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Map;

@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        applicationContext.refresh();

        System.out.println("config类型的所有bean： " + applicationContext.getBeansOfType(Config.class));
        System.out.println("user类型的所有bean： " + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    @Component
    public static class Config {
        @Bean(name = {"user", "constant-user"})
        public User user() {
            User user = new User();
            user.setId(33L);
            user.setName("node");
            return user;
        }
    }

}
