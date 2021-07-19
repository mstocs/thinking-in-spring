package org.example.thinking.in.spring.bean.lificycle;

import org.example.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * @Author Constant
 * @Date 2021/7/19 23:56
 * @Description bean生命周期演示
 **/
public class BeanLifeCycleDemo {
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        //添加销毁前回调接口
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

        //解决@PostConstruct注解未生效
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        String location2= "META-INF/bean-constructor-dependency-injection.xml";

        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(location, location2);
        System.out.println("已加载BeanDefinition数量" + beanNumbers);

        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        //构造器注入是按照类型注入的方式，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        //显式调用preInstantiateSingletions()
        //SmartInitializingSingleton一般在applicationConstet调用
        beanFactory.preInstantiateSingletons();

        System.out.println(userHolder);

        //执行bean销毁
        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println(userHolder);

        //销毁BeanFactory中的单例bean
        beanFactory.destroySingletons();
        System.gc();
        Thread.sleep(1000L);
        System.gc();
    }
}
