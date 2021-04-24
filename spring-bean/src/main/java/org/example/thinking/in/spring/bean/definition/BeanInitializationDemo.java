package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

// bean的初始化演示
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {

        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration class
        applicationContext.register(BeanInitializationDemo.class);

        //启动spring应用上下文
        applicationContext.refresh();

        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        System.out.println(userFactory);

        System.out.println("Spring 上下文准备关闭");
        //关闭
        applicationContext.close();
        System.out.println("Spring 上下午已关闭");

    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
