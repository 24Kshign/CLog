package com.jack.clog;

/**
 * Created by manji
 * Date：2018/10/23 上午9:40
 * Desc：
 */
public class TestBean {

    private int id;
    private int age;
    private String name;

    public TestBean(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}