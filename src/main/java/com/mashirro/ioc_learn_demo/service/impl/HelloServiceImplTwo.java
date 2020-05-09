package com.mashirro.ioc_learn_demo.service.impl;

import com.mashirro.ioc_learn_demo.service.HelloService;
import org.springframework.stereotype.Service;


@Service("HelloService2")
public class HelloServiceImplTwo implements HelloService{
    @Override
    public String sayHello() {
        return "HelloServiceImplTwo";
    }
}
