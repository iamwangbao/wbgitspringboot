package com.wangbao.springbootmongodb.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 使用@Document注解指定集合名称
 */
@Document(collection="col")
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
 /**
     * 使用@Id注解指定MongoDB中的 _id 主键
     */
    @Id
    private int id;
    private String userName;
    private String passWord;
    private Integer age;
    private Date createTime;
    //...get/set
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                '}';
    }
}