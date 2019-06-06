package com.shoufeng.serializable;

import com.shoufeng.clone.CloneUtil;

import java.io.*;
import java.util.Objects;

public class Person implements Serializable {

    private static final long serialVersionUID = 443489773843924018L;

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) throws Exception {
        Person person1 = new Person("zhangsan", 11);
        Person person2 = CloneUtil.clone(person1);
        System.out.println(person1.equals(person2));
    }
}

