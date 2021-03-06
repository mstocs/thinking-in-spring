package org.example.thinking.in.spring.bean.lificycle;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author Constant
 * @Date 2021/7/12 23:52
 * @Description TODO
 **/
public class UserHolder implements InitializingBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, SmartInitializingSingleton, DisposableBean {
    private final User user;

    private Integer number;

    private String description;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;

    private Environment environment;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    @PostConstruct
    public void initPostConstruct() {
        this.description = "this is user holder v4";
        System.out.println("initPostConstructor() = " + description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "this is user holder v5";
        System.out.println("afterPropertiesSet() = " + description);
    }

    public void init() {
        this.description = "this is user holder v6";
        System.out.println("init-method() = " + description);
    }

    @PreDestroy
    public void preDestroy() {
        this.description = "this is user holder v10";
        System.out.println("preDestroy() = " + description);
    }


    @Override
    public void destroy() throws Exception {
        this.description = "this is user holder v11";
        System.out.println("destroy() = " + description);
    }

    public void doDestroy() {
        this.description = "this is user holder v12";
        System.out.println("doDestroy() = " + description);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        //??????applicaitonContext???????????????doCreateBean????????????
        this.environment = environment;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.description = "this is user holder v8";
        System.out.println("afterSingletonsInstantiated = " + this.description);
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("userHolder?????????");
    }
}
