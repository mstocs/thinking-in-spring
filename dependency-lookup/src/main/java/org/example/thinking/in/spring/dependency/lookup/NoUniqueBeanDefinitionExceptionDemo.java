package org.example.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        //启动应用上下文
        applicationContext.refresh();

        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring 上下文存在%s个 %s 类型的bean，具体原因：%s%n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }

        applicationContext.close();
    }

    @Bean
    @Primary
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }
}
