package com.mashirro.ioc_learn_demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


/**
 * 自定义BeanFactoryPostProcessor实现类
 * (1)Spring IoC容器允许BeanFactoryPostProcessor读取配置元数据，并可能在容器实例化BeanFactoryPostProcessor实例以外的任何bean之前对其进行更改
 * (2)不要在BeanFactoryPostProcessor进行可能触发bean实例化的操作。会导致bean实例化过早，从而违反了标准容器的生命周期。这可能会导致负面影响，例如绕过bean后处理。
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered{

    /**
     * 本方法在Bean对象实例化之前执行，
     * 通过beanFactory可以获取bean的定义信息，
     * 并可以修改bean的定义信息。这点是和BeanPostProcessor最大区别
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println(">> BeanFactoryPostProcessor 开始执行了");
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if("helloServiceTest".equals(beanDefinitionName)){
                BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
                System.out.println("beanDefinition-->"+ beanDefinition);
            }

        }
        System.out.println(">> BeanFactoryPostProcessor 执行结束了");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
