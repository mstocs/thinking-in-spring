package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Author Constant
 * @Date 2021/7/25 09:27
 * @Description "user" 元素的BeanDefinitionParser实现
 **/
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id", element, builder);
        setPropertyValue("name", element, builder);
        setPropertyValue("city", element, builder);
    }

    private void setPropertyValue(String propertyValueName, Element element, BeanDefinitionBuilder builder) {
        String propertyValue = element.getAttribute(propertyValueName);
        if(StringUtils.hasText(propertyValue)) {
            builder.addPropertyValue(propertyValueName, propertyValue);
        }
    }
}
