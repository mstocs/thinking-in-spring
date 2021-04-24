package org.example.thinking.in.spring.dependency.lookup;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.annotation.PreDestroy;
import java.util.Iterator;

/**
 * 通过ObjectProvider进行依赖查找
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(ObjectProviderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找
        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStream(applicationContext);

        applicationContext.close();
    }

    private static void lookupByStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(() -> User.createUser());
        System.out.println("当前User对象： "+user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    @Bean
    public String helloWorld() {
        return "Hello, World";
    }

    @Bean
    @Primary
    public String message() {
        return "Message";
    }

}
