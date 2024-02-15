package com.learningspringcore;

public class Employee implements Person{
    @Override
    public void learn() {
        System.out.println("Employee is learning.");
    }
}
