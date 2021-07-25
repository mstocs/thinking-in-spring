package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @Author Constant
 * @Date 2021/7/24 17:47
 * @Description 基于Java注解的Spring IoC 容器元信息配置示例
 **/

//将当前类作为Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class AnnotatedSpringIoCContainerMetaConfigutationDemo {
    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册当前类作为configuration class
        context.register(AnnotatedSpringIoCContainerMetaConfigutationDemo.class);
        context.refresh();
        Map<String, User> userMap = context.getBeansOfType(User.class);
        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.printf("user name: %s, user: %s \n", entry.getKey(), entry.getValue());
        }
        context.close();
    }
}
