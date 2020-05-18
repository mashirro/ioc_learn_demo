package com.mashirro.ioc_learn_demo;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessorTwo implements BeanPostProcessor, Ordered {

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //后处理器在调用容器初始化方法（如initializengbean afterPropertiesSet()或任何声明的init方法）之后
        System.out.println("MyBeanPostProcessorTwo---After: " + beanName);
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //后处理器在调用容器初始化方法（如initializengbean afterPropertiesSet()或任何声明的init方法）之前
        System.out.println("MyBeanPostProcessorTwo---Before: " +beanName);
        return bean;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
