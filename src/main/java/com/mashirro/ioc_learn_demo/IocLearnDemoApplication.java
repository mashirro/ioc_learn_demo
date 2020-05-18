package com.mashirro.ioc_learn_demo;

import com.mashirro.ioc_learn_demo.entity.Student;
import com.mashirro.ioc_learn_demo.entity.StudentManage;
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

		/**
		 * student为单例bean,StudentManage为多例bean
		 * 如果使用@Autowired为StudentManage bean注入student bean,会发现两个sm对象是同一个对象
		 */
		Student student = ApplicationContextUtil.getBean(Student.class);
		StudentManage sm1 = ApplicationContextUtil.getBean(StudentManage.class);
		System.out.println(sm1);
		System.out.println(sm1.getStudent());
		StudentManage sm2 = ApplicationContextUtil.getBean(StudentManage.class);
		System.out.println(sm2);
		System.out.println(sm2.getStudent());
	}

}
