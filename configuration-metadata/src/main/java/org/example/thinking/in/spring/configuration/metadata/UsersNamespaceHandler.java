package org.example.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Author Constant
 * @Date 2021/7/25 09:25
 * @Description "users.xsd" 自定义NameSpaceHandlerSupport实现
 **/
public class UsersNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        //将"user"元素注册对应的BeanDefinitionParser实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
