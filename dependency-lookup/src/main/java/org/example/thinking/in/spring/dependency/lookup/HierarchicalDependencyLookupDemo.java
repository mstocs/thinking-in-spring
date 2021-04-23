package org.example.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        //获取HierarchicicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        System.out.println("当前beanFactory的parent："+beanFactory.getParentBeanFactory());

        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前beanFactory的parent："+beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "superUser");

        displayContainsBean(beanFactory, "user");
        displayContainsBean(beanFactory,"superUser");
        //启动应用上下文
        applicationContext.refresh();

        applicationContext.close();
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if(containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory【%s]是否包含bean[%s] : %s\n", beanFactory, beanName, containsBean(beanFactory, beanName) );
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory【%s]是否包含bean[%s] : %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName) );
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //xml configure file path
        String path = "META-INF/dependency-lookup-context.xml";
        int beanCount = reader.loadBeanDefinitions(path);
        return beanFactory;
    }
}
