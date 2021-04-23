package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws Exception{
        //创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration class
        applicationContext.register(BeanInitializationDemo.class);

        //启动spring应用上下文
        applicationContext.refresh();
        //关闭
        applicationContext.close();
        System.out.println("Spring 上下文已关闭");
        TimeUnit.SECONDS.sleep(1l);
        System.gc();
        TimeUnit.SECONDS.sleep(5l);
    }
}
