package org.example.thinking.in.spring.bean.lificycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @Author Constant
 * @Date 2021/7/19 23:53
 * @Description 自定义Bean销毁生命周期接口
 **/
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("this is user holder v9");
            System.out.println("postProcessBeforeDestruction() = " + userHolder.getDescription());
        }
    }
}
