package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体bean注册演示
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();


        //注册外部单例对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);


        //启动spring应用上下文
        applicationContext.refresh();

        UserFactory userFactoryByLookUp = beanFactory.getBean("userFactory", UserFactory.class);

        System.out.println("userFactory == userFactoryByLookUp ? " + (userFactory == userFactoryByLookUp));
        //关闭
        applicationContext.close();
    }
}
