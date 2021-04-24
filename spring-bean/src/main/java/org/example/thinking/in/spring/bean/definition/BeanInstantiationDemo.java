package org.example.thinking.in.spring.bean.definition;

import com.sun.xml.internal.txw2.output.DumpSerializer;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {
    public static void main(String[] args) {
        //config xml and start spring context
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstantceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println(user);
        System.out.println(userByInstantceMethod);
        System.out.println(userByFactoryBean);
        System.out.println(user==userByInstantceMethod);
        System.out.println(userByFactoryBean==userByInstantceMethod);
    }
}
