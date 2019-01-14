package com.hykj.liuzhi.androidcomponents.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;

    private String name;
    private String sex;
    private String password;
    private int age;
    @Generated(hash = 1715762827)
    public User(Long id, String name, String sex, String password, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
