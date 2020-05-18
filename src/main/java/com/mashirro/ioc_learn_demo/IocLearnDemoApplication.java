package com.mashirro.ioc_learn_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:custom.properties")
public class IocLearnDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocLearnDemoApplication.class, args);
		//测试代码
		HelloServiceTest helloServiceTest = ApplicationContextUtil.getBean(HelloServiceTest.class);
		helloServiceTest.sayHello();
		System.out.println(helloServiceTest.getTeacherName());
	}

}
