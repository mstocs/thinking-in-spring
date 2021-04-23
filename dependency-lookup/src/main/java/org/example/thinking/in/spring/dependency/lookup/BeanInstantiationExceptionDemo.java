package org.example.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        //将当前类作为配置类
        applicationContext.registerBeanDefinition("ErrorBean", beanDefinitionBuilder.getBeanDefinition());
        //启动应用上下文
        applicationContext.refresh();


        applicationContext.close();
    }
}
