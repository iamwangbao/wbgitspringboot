package com.wangbao.springbootmongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wangbao.springbootmongodb.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootMongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    void contextLoads() {
    }

    /**
     * 插入文档
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        mongoTemplate.insert(person);
    }


    /**
     * 自定义集合，插入文档
     * @throws Exception
     */
    @Test
    public void insertCustomCollection() throws Exception {
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        mongoTemplate.insert(person, "custom_person");
    }

    /**
     * 自定义集合，批量插入文档
     * @throws Exception
     */
    @Test
    public void insertBatch() throws Exception {
        List<Person> personList = new ArrayList<>();
        Person person1 =new Person();
        person1.setId(10);
        person1.setUserName("张三");
        person1.setPassWord("123456");
        person1.setCreateTime(new Date());
        personList.add(person1);
        Person person2 =new Person();
        person2.setId(20);
        person2.setUserName("李四");
        person2.setPassWord("123456");
        person2.setCreateTime(new Date());
        personList.add(person2);
        mongoTemplate.insert(personList, "custom_person");
    }

    /**
     * 存储文档，如果没有插入，否则更新
     * @throws Exception
     */
    @Test
    public void save() throws Exception {
        Person person =new Person();
        person.setId(13);
        person.setUserName("八八");
        person.setPassWord("123456");
        person.setAge(40);
        person.setCreateTime(new Date());
        mongoTemplate.save(person);
    }

    /**
     * 自定义集合，存储文档
     * @throws Exception
     */
    @Test
    public void saveCustomCollection() throws Exception {
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        mongoTemplate.save(person, "custom_person");
    }

    /**
     * 更新文档，匹配查询到的文档数据中的第一条数据
     * @throws Exception
     */
    @Test
    public void updateFirst() throws Exception {
        //更新对象
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三123");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        //更新条件
        Query query= new Query(Criteria.where("id").is(person.getId()));
        //更新值
        Update update= new Update().set("userName", person.getUserName()).set("passWord", person.getPassWord());
        //更新查询满足条件的文档数据（第一条）
        UpdateResult result =mongoTemplate.updateFirst(query,update, Person.class);
        if(result!=null){
            System.out.println("更新条数：" + result.getMatchedCount());
        }
    }

    /**
     * 更新文档，匹配查询到的文档数据中的所有数据
     * @throws Exception
     */
    @Test
    public void updateMany() throws Exception {
        //更新对象
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        //更新条件
        Query query= new Query(Criteria.where("id").is(person.getId()));
        //更新值
        Update update= new Update().set("userName", person.getUserName()).set("passWord", person.getPassWord());
        //更新查询满足条件的文档数据（全部）
        UpdateResult result = mongoTemplate.updateMulti(query, update, Person.class);
        if(result!=null){
            System.out.println("更新条数：" + result.getMatchedCount());
        }
    }

    /**
     * 删除符合条件的所有文档
     * @throws Exception
     */
    @Test
    public void remove() throws Exception {
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        Query query = new Query(Criteria.where("userName").is(person.getUserName()));
        DeleteResult result = mongoTemplate.remove(query, Person.class);
        System.out.println("删除条数：" + result.getDeletedCount());
    }

    /**
     * 删除符合条件的单个文档，并返回删除的文档
     * @throws Exception
     */
    @Test
    public void findAndRemove() throws Exception {
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        Query query = new Query(Criteria.where("id").is(person.getId()));
        Person result = mongoTemplate.findAndRemove(query, Person.class);
        System.out.println("删除的文档数据：" + result.toString());
    }

    /**
     * 删除符合条件的所有文档，并返回删除的文档
     * @throws Exception
     */
    @Test
    public void findAllAndRemove() throws Exception {
        Person person =new Person();
        person.setId(1);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        Query query = new Query(Criteria.where("id").is(person.getId()));
        List<Person> result = mongoTemplate.findAllAndRemove(query, Person.class);
        System.out.println("删除的文档数据：" + result.toString());
    }



}
