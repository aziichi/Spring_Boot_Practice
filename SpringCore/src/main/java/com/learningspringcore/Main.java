package com.learningspringcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        School school = context.getBean(School.class);
        school.studentGoingToSchool();
        Student student = context.getBean(Student.class);
        Student student1 = context.getBean(Student.class);
        student1.setName("Ahmad");
        student.learn();
        student1.learn();

    }
}