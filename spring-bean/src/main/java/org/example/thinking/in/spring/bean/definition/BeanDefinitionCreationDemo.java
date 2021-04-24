package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        // build by BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        beanDefinitionBuilder.addPropertyValue("id", 1);
        beanDefinitionBuilder.addPropertyValue("name", "java");

        //get beanDefinition instance
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // build by derived
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        //通过mutablePropertyValues批量操作
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", 1).add("name", "java");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
