package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Author Constant
 * @Date 2021/7/24 10:48
 * @Description PropertiesBeanDefinitionReader示例
 **/
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建面向properties资源的BeanDefinition示例
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //Properties资源加载默认通过ISO-8859-1，实际存储是UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("META-INF/user-bean-definitions.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int loadBeanDefinitions = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的BeanDefinition数量:" + loadBeanDefinitions);
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
