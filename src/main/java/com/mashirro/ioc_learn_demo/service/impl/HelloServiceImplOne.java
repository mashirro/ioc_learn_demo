package com.mashirro.ioc_learn_demo.service.impl;

import com.mashirro.ioc_learn_demo.service.HelloService;
import org.springframework.stereotype.Service;


@Service("HelloService1")
public class HelloServiceImplOne  implements HelloService{
    @Override
    public String sayHello() {
        return "HelloServiceImplOne...";
    }
}
