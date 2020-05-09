package com.mashirro.ioc_learn_demo.annotation;


import java.lang.annotation.*;

/**
 * 自定义注解: 注入依赖项使用
 */

@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RountingInjected {

    String beanName() default "";
}
