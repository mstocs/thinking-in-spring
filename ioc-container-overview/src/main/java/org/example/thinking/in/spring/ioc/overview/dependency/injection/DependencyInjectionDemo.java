package org.example.thinking.in.spring.ioc.overview.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.annotation.Super;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.example.thinking.in.spring.ioc.overview.domain.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置xml配置文件
        //启动应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        // System.out.println(userRepository.getUsers());
        //依赖注入
        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory() == beanFactory);

        //依赖查找
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory objectFactory = userRepository.getObjectFactory();

        System.out.println(objectFactory.getObject());

        System.out.println(objectFactory.getObject()==beanFactory);

        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("Environment类型的bean: " + environment);
    }
}
