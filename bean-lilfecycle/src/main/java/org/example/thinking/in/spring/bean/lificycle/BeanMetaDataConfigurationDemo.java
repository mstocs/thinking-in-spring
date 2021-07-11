package org.example.thinking.in.spring.bean.lificycle;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Author Constant
 * @Date 2021/7/9 22:01
 * @Description 使用Properties文件加载资源
 **/
public class BeanMetaDataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //实例化基于Properites资源的BeanDefinationReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        String location = "META-INF/user.properties";
        Resource resource = new ClassPathResource(location);
        //指定字符编码为utf-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("已加载BeanDefination数量:" + beanNumbers);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
