package com.mashirro.ioc_learn_demo.config;


import com.mashirro.ioc_learn_demo.entity.Student;
import com.mashirro.ioc_learn_demo.entity.StudentManage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfiguration {

    /**
     * 声明prototype bean
     * @return
     */
    @Bean
    @Scope("prototype")
    public Student student(){
        return new Student();
    }

    /**
     * 声明单例Bean
     * @return
     */
    @Bean
    public StudentManage studentManage(){
        //return new StudentManage(new Student());
        return new StudentManage();
    }
}
