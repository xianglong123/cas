package com.cas.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Data
@ToString
public class User {

    private String uid;

    private String name;

    private Integer age;

    private Date birthday;

    public User() {}

    public User(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public User(String uid, String name, Integer age, Date birthday) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

}
