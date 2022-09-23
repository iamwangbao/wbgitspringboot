package com.wangbao.springbootmongodb.dao.mongodbCRUD.mongodbCRUDimpl;

import com.mongodb.client.result.UpdateResult;
import com.wangbao.springbootmongodb.dao.mongodbCRUD.personCRUD;
import com.wangbao.springbootmongodb.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class personCRUDimpl implements personCRUD {

    public static personCRUDimpl personCRUDimpl;
    @PostConstruct
    public void init()
    {
        personCRUDimpl=this;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String insert(Person person) {
        personCRUDimpl.mongoTemplate.insert(person);
        return "插入成功";
    }

    @Override
    public String query(int personid) {

        Person person = new Person();
        person = (Person) personCRUDimpl.mongoTemplate.findById(personid,Person.class);
        return person.toString();


    }

    @Override
    public long updateFirst(Person person) {
        //更新条件
        Query query= new Query(Criteria.where("id").is(person.getId()));
        //更新值
        Update update= new Update().set("userName", person.getUserName()).set("passWord", person.getPassWord());
        //更新查询满足条件的文档数据（第一条）
        UpdateResult result =personCRUDimpl.mongoTemplate.updateFirst(query,update, Person.class);
        if(result!=null){
            System.out.println("更新条数：" + result.getMatchedCount());
        }
        return result.getMatchedCount();
    }

    @Override
    public String findAndRemove(int personid) {
        Query query = new Query(Criteria.where("id").is(personid));
        Person result = personCRUDimpl.mongoTemplate.findAndRemove(query, Person.class);
        System.out.println("删除的文档数据：" + result.toString());
        if(result.toString()!=null)
            return result.toString();
        else
            return "null";
    }
}
