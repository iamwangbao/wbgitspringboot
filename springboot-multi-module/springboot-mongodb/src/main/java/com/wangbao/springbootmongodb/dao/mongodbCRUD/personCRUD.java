package com.wangbao.springbootmongodb.dao.mongodbCRUD;

import com.wangbao.springbootmongodb.pojo.Person;

public interface personCRUD {
    String insert(Person person);

    String query(int personid);

    long updateFirst(Person person);
    String findAndRemove(int personid);

}
