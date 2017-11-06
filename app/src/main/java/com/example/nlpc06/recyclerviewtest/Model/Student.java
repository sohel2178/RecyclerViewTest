package com.example.nlpc06.recyclerviewtest.Model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by NL PC 06 on 10/26/2017.
 */

public class Student extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String name;
    private int age;
    private int genderType;

    public Student() {
    }

    public Student(String name, int age, int genderType) {
        this.name = name;
        this.age = age;
        this.genderType = genderType;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.genderType=1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGenderType() {
        return genderType;
    }

    public void setGenderType(int genderType) {
        this.genderType = genderType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
