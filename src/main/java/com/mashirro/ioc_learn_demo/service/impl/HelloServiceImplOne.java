package com.mashirro.ioc_learn_demo.service.impl;

import com.mashirro.ioc_learn_demo.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service("HelloService1")
public class HelloServiceImplOne implements HelloService{

    @PostConstruct
    public void init(){
        System.out.println("HelloServiceImplOne init()...");
    }

    @Override
    public String sayHello() {
        return "HelloServiceImplOne...";
    }
}
