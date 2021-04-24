package org.example.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    //1基于post constructor注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: 初始化中");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法：初始化中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializaingBean afterPropertiesSet方法：初始化中");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: 销毁中");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy方法： 销毁中");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法：销毁中");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前DefaultFactory正在被回收");
    }
}
