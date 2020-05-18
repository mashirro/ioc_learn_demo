package com.mashirro.ioc_learn_demo.service.impl;

import com.mashirro.ioc_learn_demo.service.HelloService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


@Service("HelloService2")
public class HelloServiceImplTwo implements HelloService, InitializingBean{
    @Override
    public String sayHello() {
        return "HelloServiceImplTwo";
    }


    /**
     * 实现InitializingBean接口,初始化回调
     * 允许bean(即HelloServiceImplTwo bean)在容器设置了bean的所有必要属性之后执行初始化工作。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("HelloServiceImplTwo init()...");
    }
}
