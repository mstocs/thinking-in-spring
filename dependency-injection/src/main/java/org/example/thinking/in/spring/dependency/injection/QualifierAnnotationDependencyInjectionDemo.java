package org.example.thinking.in.spring.dependency.injection;

import org.example.thinking.in.spring.dependency.injection.annotation.UserGroup;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * Qualifier依赖注入
 */
@Configuration
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierdUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;

    @Bean
    @Qualifier
    public User user1() {
        return createUser(11l);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(22l);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(33l);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(44l);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.namedUser = " + demo.namedUser);

        //Qualifier进行集合分组，分为标注和未标注的
        System.out.println("demo.allUsers = " + demo.allUsers);
        System.out.println("demo.qualifieredUser = " + demo.qualifierdUsers);

        //@UserGroup
        System.out.println("demo.groupUsers = " + demo.groupUsers);

        applicationContext.close();
    }
}
