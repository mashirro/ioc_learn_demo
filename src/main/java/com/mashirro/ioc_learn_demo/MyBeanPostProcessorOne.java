package com.mashirro.ioc_learn_demo;

import com.mashirro.ioc_learn_demo.annotation.RountingInjected;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;


/**
 * BeanPostProcessor接口定义回调方法，
 * 您可以实现这些方法来提供自己的（或重写容器的默认）实例化逻辑、依赖关系解析逻辑等。
 * 如果要在Spring容器完成实例化、配置和初始化 bean之后实现一些自定义逻辑，
 * 可以插入一个或多个自定义BeanPostProcessor实现。您可以配置多个BeanPostProcessor实例，
 * 并且可以通过设置order属性来控制这些BeanPostProcessor实例的执行顺序
 * （只有BeanPostProcessor实现有序接口Ordered时，才能设置此属性）
 */
@Component
public class MyBeanPostProcessorOne implements BeanPostProcessor, Ordered {

    private static final String DEFAULT_BEAN_NAME = "HelloService1";

    @Autowired
    private ApplicationContext applicationContext;

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("MyBeanPostProcessorOne---After: " + beanName);
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("MyBeanPostProcessorOne---Before: " +beanName);

        //--------在Spring容器完成实例化、配置和初始化 bean之后实现一些自定义逻辑start--------

        //获取Class对象(HelloServiceTest)
        Class<?> aClass = bean.getClass();
        //获得某个类(HelloServiceTest)的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            //HelloServiceTest中字段是否存在自定义注解,存在则返回true,否则false
            if (field.isAnnotationPresent(RountingInjected.class)) {
                if (!field.getType().isInterface()) {
                    //如果Class对象(HelloService)不表示一个接口类型,则抛出异常
                    throw new BeanCreationException("RoutingInjected field must be declared as an interface:" + field.getName() + " @Class " + aClass.getName());
                } else {
                    try {
                        //注入依赖bean(注入HelloService)
                        this.handleRoutingInjected(field, bean, field.getType());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //--------在Spring容器完成实例化、配置和初始化 bean之后实现一些自定义逻辑end--------
        return bean;
    }

    /**
     * @param field HelloServiceTest中存在自定义注解的字段
     * @param bean  HelloServiceTest bean
     * @param aClass    HelloService Class对象
     */
    private void handleRoutingInjected(Field field, Object bean, Class<?> aClass) throws IllegalAccessException {
        //获取HelloService类型的beans
        Map<String, ?> beansOfType = this.applicationContext.getBeansOfType(aClass);
        //如果字段是私有的,那么必须要对这个字段设置setAccessible(true)才可以正常使用,否则报错can not access a member of...
        field.setAccessible(true);
        ////存在一个或多个相同类型(HelloService)的bean
        if (beansOfType.size() >= 1) {
            //获取字段注解上的值
            String beanName = field.getAnnotation(RountingInjected.class).beanName().trim();
            if ("".equals(beanName)) {
                //未设置的话,设默认值
                beanName = DEFAULT_BEAN_NAME;
            } else {
                //判断是否存在此beanName的bean
                if (!beansOfType.keySet().contains(beanName)) {
                    throw new IllegalArgumentException("No beans with this beanName were found: " + aClass);
                }
            }
            for (String key : beansOfType.keySet()) {
                if (beanName.equals(key)) {
                    Object o = null;
                    //从容器中获取依赖项
                    o = beansOfType.get(key);
                    //依赖项注入
                    field.set(bean, o);
                }
            }
        } else {
            throw new IllegalArgumentException("No beans of this Class were found: " + aClass);
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
