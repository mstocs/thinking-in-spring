package org.example.thinking.in.spring.dependency.lookup;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//类型安全依赖查找说明
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        //启动应用上下文
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);

        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);

        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", ()->objectProvider.stream().forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory applicationContext) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", ()->applicationContext.getBeansOfType(User.class));
    }

    //安全，为空不抛异常
    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", ()->objectProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", ()->objectFactory.getObject());
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("---------------");
        System.err.println("Source from: " + source);
        try{
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", ()->beanFactory.getBean(User.class));
    }
}
