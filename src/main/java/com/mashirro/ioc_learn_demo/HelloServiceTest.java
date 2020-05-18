package com.mashirro.ioc_learn_demo;


import com.mashirro.ioc_learn_demo.annotation.RountingInjected;
import com.mashirro.ioc_learn_demo.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceTest {

    /**
     * 使用自定义注解结合自定义BeanPostProcessor实现类---->模拟实现@Autowired注解的功能
     * 关于BeanPostProcessor讲解请参看博客Spring5.2.5 ioc容器官方文档学习
     */
    @RountingInjected(beanName = "HelloService2")
    //第二种
    //@Autowired
    //@Qualifier("HelloService2")
    //第三种
    //@Resource(name = "HelloService2")
    private HelloService helloService;


    @Value("${teacher.name}")
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void sayHello(){
        System.out.println(helloService.sayHello());
    }

    /**
     * 测试用main入口(此测试入口移到spring boot启动类)
     * @param args
     */
//    public static void main(String[] args) {
//        //使用AnnotationConfigApplicationContext实例化Spring容器
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.mashirro.ioc_learn_demo");
//        HelloServiceTest helloServiceTest = ac.getBean(HelloServiceTest.class);
//        helloServiceTest.sayHello();
//        System.out.println(helloServiceTest.teacherName);
//    }
}
