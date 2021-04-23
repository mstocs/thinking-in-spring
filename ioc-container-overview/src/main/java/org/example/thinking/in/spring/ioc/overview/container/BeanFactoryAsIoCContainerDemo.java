package org.example.thinking.in.spring.ioc.overview.container;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        // build BeanFactory Container
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //xml configure file path
        String path = "META-INF/dependency-lookup-context.xml";
        int beanCount = reader.loadBeanDefinitions(path);
        System.out.println("beans count: " + beanCount);

        lookupByCollectionType(beanFactory);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User类型的数据： " + users);
        }
    }
}
