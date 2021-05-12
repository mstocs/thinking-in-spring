package org.example.thinking.in.spring.bean.scope;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class BeanScopeDemo implements DisposableBean {
    @Bean
    //默认scope为singleton
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User protoTypeUser() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("protoTypeUser")
    private User protoTypeUser;

    @Autowired
    @Qualifier("protoTypeUser")
    private User protoTypeUser1;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Autowired
    Map<String, User> userMap;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称:%s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        applicationContext.refresh();

        scopedBeanByLookUp(applicationContext);

        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopedBeanByLookUp(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser = " + singletonUser);
            User protoTypeUser = applicationContext.getBean("protoTypeUser", User.class);
            System.out.println("protoTypeUser = " + protoTypeUser);
        }
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("singletonUser = " + beanScopeDemo.singletonUser);
        System.out.println("singletonUser1 = " + beanScopeDemo.singletonUser1);
        System.out.println("protoTypeUser = " + beanScopeDemo.protoTypeUser);
        System.out.println("protoTypeUser1 = " + beanScopeDemo.protoTypeUser1);
        System.out.println("userMap = " + beanScopeDemo.userMap);
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("当前beanScopeDemo bean正在销毁中...");
        this.protoTypeUser.destroy();
        this.protoTypeUser1.destroy();
        for(Map.Entry<String, User> entry: userMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(beanDefinition.isPrototype()) {
                User user = entry.getValue();
                user.destroy();
            }
        }
        System.out.println("当前beanScopeDemo bean销毁完毕...");

    }
}
