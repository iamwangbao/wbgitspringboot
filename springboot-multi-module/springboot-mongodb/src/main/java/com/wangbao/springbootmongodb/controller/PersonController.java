package com.wangbao.springbootmongodb.controller;

import com.wangbao.springbootmongodb.dao.mongodbCRUD.mongodbCRUDimpl.personCRUDimpl;
import com.wangbao.springbootmongodb.dao.mongodbCRUD.personCRUD;
import com.wangbao.springbootmongodb.pojo.Person;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;


@RestController
@RequestMapping("/person")
public class PersonController {

    personCRUD personCRUDimpl = new personCRUDimpl();

    @SneakyThrows
    @RequestMapping(value="/addone",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addone(@RequestBody String json)
    {
        Person person = new Person();
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(json);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        person.setId(jsonObj.getInt("id"));
        person.setUserName(jsonObj.getString("userName"));
        person.setPassWord(jsonObj.getString("passWord"));
        person.setAge(jsonObj.getInt("age"));
        person.setCreateTime(ft.parse(jsonObj.getString("createTime")));
        personCRUDimpl.insert(person);

        return json;
    }

    @SneakyThrows
    @RequestMapping(value="/modifyone",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modifyone(@RequestBody String json)
    {
        Person person = new Person();
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(json);
        //将json对象的值赋给user对象
        SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        person.setId(jsonObj.getInt("id"));
        person.setUserName(jsonObj.getString("userName"));
        person.setPassWord(jsonObj.getString("passWord"));
        person.setAge(jsonObj.getInt("age"));
        person.setCreateTime(ft.parse(jsonObj.getString("createTime")));
        long updatanumber = personCRUDimpl.updateFirst(person);
        if(updatanumber>0)
            return "更新条数:"+updatanumber;
        else
            return "更新条数:0";
    }

    @RequestMapping("/deleteone/{id}")
    @ResponseBody
    public String deleteone(@PathVariable("id") int personid)
    {
        String deleteoneYN = personCRUDimpl.findAndRemove(personid);
        if (deleteoneYN!=null)
            return deleteoneYN;
        return "null";
    }

    @RequestMapping("/queryone/{id}")
    public String queryone(@PathVariable("id") int personid)
    {
        String queryoneYN = personCRUDimpl.query(personid);
        if (queryoneYN!=null)
            return queryoneYN;
        return "null";
    }
}
