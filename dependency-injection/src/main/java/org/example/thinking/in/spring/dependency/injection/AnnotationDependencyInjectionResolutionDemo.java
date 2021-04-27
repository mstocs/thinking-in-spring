package org.example.thinking.in.spring.dependency.injection;

import org.example.thinking.in.spring.dependency.injection.annotation.InjectedUser;
import org.example.thinking.in.spring.dependency.injection.annotation.MyAutowired;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动的依赖注入过程
 */

public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    // DependencyDescriptor ->
    // required true
    // 实时注入 +
    // 通过类型（User.class)依赖查找（处理）
    // 字段名称（"user")
    // 是否首要（"primary = true)
    @Autowired
    private User user;

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> optionalUser;

    @Inject
    private User injectUser;

    @InjectedUser
    private User myInjectUser;

    @Bean(name = "myBeanPostProcessor")
    public AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        //替换原有注解处理，使用新注解
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.injectedUser = " + demo.injectUser);

        System.out.println("demo.users = " + demo.users);
        System.out.println("demo.optionalUser = " + demo.optionalUser);

        System.out.println("demo.lazyUser = " + demo.lazyUser);

        System.out.println("demo.myInjectedUser = " + demo.injectUser);

        applicationContext.close();
    }
}
