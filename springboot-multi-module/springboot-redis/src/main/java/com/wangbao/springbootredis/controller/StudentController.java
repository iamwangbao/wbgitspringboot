package com.wangbao.springbootredis.controller;


import com.wangbao.springbootredis.pojo.Student;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private RedisTemplate redisTemplate;

    @SneakyThrows
    @RequestMapping(value="/addone",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean addone(@RequestBody Student student)
    {
        String fileName = "student";
        String uuidFileName= fileName + UUID.randomUUID().toString().replace("-","");

        return redisTemplate.opsForValue().setIfAbsent(uuidFileName, student);
        //return student.toString();
    }

    @SneakyThrows
    @RequestMapping(value="/modifyone",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void modifyone(@RequestBody String json)
    {
        Student student = new Student();
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(json);
        //将json对象的值赋给user对象
        SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String studentkey = jsonObj.getString("studentkey");
        student.setId(jsonObj.getInt("id"));
        student.setName(jsonObj.getString("name"));
        student.setScore(jsonObj.getDouble("score"));
        student.setBirthday(ft.parse(jsonObj.getString("birthday")));

        redisTemplate.opsForValue().set(studentkey,student);
    }

    @RequestMapping("/deleteone/{key}")
    @ResponseBody
    public boolean deleteone(@PathVariable("key") String key)
    {
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }

    @RequestMapping("/queryone/{key}")
    public Student queryone(@PathVariable("key") String key )
    {
        return (Student) redisTemplate.opsForValue().get(key);
    }
}
