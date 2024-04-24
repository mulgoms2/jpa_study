package com.study.jpa.domain;

public class Student extends People {

    String name;
    int age;


    public Student(int height, int weight, String name, int age) {
        super(height, weight);
        this.name = name;
        this.age = age;
    }

}
