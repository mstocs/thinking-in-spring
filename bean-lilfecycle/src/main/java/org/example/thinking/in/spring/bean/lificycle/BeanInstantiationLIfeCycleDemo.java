package org.example.thinking.in.spring.bean.lificycle;

import org.example.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * @Author Constant
 * @Date 2021/7/11 23:00
 * @Description 实例化生命周期示例
 **/
public class BeanInstantiationLIfeCycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        String location2= "META-INF/bean-constructor-dependency-injection.xml";

        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(location, location2);
        System.out.println("已加载BeanDefinition数量" + beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        //构造器注入是按照类型注入的方式，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                //把配置完成的superUser bean覆盖
                return new SuperUser();
            }
            return null;
        }
    }
}
