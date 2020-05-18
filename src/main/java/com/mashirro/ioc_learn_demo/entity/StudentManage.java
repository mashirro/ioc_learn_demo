package com.mashirro.ioc_learn_demo.entity;

import com.mashirro.ioc_learn_demo.ApplicationContextUtil;

public class StudentManage {

//    private Student student;
//
//    public StudentManage(Student student) {
//        this.student = student;
//    }
//
//    public Student getStudent() {
//        return student;
//    }


    public Student getStudent() {
        return ApplicationContextUtil.getBean(Student.class);
    }

}
