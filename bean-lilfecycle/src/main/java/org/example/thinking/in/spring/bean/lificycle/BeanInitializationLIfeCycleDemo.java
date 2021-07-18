package org.example.thinking.in.spring.bean.lificycle;

import org.example.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Constant
 * @Date 2021/7/11 23:00
 * @Description 实例化生命周期示例
 **/
public class BeanInitializationLIfeCycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //解决@PostConstruct注解未生效
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        String location2= "META-INF/bean-constructor-dependency-injection.xml";

        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(location, location2);
        System.out.println("已加载BeanDefinition数量" + beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        //构造器注入是按照类型注入的方式，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        //添加BeanPostProcessor实现
        String location = "META-INF/dependency-lookup-context.xml";
        String location2= "META-INF/bean-constructor-dependency-injection.xml";
        String[] locations = new String[2];
        locations[0]=location;
        locations[1]=location2;
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = applicationContext.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        //构造器注入是按照类型注入的方式，resolveDependency
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();


    }
}

