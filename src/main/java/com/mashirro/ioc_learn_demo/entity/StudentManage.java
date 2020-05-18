package com.mashirro.ioc_learn_demo.entity;

import com.mashirro.ioc_learn_demo.ApplicationContextUtil;

public class StudentManage {

//    @Autowired
//    private Student student;
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }


    public Student getStudent(){
        return ApplicationContextUtil.getBean(Student.class);
    }
}
