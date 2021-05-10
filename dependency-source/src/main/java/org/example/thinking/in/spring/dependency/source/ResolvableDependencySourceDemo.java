package org.example.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolveDependency作为依赖来源
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ResolvableDependencySourceDemo.class);

        /**
         * 回调方法
         * @see AbstractApplicationContext#invokeBeanFactoryPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
         */
        applicationContext.addBeanFactoryPostProcessor(
                beanFactory -> {
                    beanFactory.registerResolvableDependency(String.class, "Hello world");
                }
        );

        applicationContext.refresh();

        applicationContext.close();
    }
}
