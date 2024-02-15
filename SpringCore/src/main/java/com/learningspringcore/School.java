package com.learningspringcore;

public class School {
    private Student student;

    public School(String name){
        System.out.println("School "+name+" is starting.");
    }

    public void studentGoingToSchool(){
        System.out.println(this.student.getName()+" is going to school.");
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
