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
		 * 如果在Configuration类中通过new StudentManage(new Student())处理依赖关系,会发现sm1和sm2中的student是同一个对象
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
