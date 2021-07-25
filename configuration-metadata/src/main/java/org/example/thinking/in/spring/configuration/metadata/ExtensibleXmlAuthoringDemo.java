package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author Constant
 * @Date 2021/7/25 16:24
 * @Description Spring xml元素拓展示例
 **/
public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args) {
        //创建ioc底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("META-INF/users-context.xml");

        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
