package com.learningspringcore;

import org.springframework.beans.factory.BeanNameAware;

public class Student implements Person, BeanNameAware {

    private String name;
    @Override
    public void learn() {
        System.out.println("Student " + name + " is learning.");
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Set bean name method is called.");
    }
}
