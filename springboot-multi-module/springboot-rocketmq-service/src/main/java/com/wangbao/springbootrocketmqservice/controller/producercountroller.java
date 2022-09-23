package com.wangbao.springbootrocketmqservice.controller;


import com.wangbao.springbootrocketmqservice.Module.RocketMqConsumer;
import com.wangbao.springbootrocketmqservice.pojo.User;
import com.wangbao.springbootrocketmqservice.service.impl.RocketMqProducer;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rockentmq")
public class producercountroller {
    @SneakyThrows
    @RequestMapping(value="/setInformation",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void setInformation(@RequestBody String json)
    {
        RocketMqConsumer rocketMqConsumer = new RocketMqConsumer();
        User user = new User();
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(json);
        //将json对象的值赋给user对象
        user.setUserName(jsonObj.getString("userName"));
        user.setPassword(jsonObj.getString("password"));
        user.setNickName(jsonObj.getString("nickName"));
        user.setSex(jsonObj.getString("sex"));
        user.setPhone(jsonObj.getInt("phone"));
        RocketMqProducer rocketMqProducer = new RocketMqProducer();
        rocketMqProducer.send("User", user);
        rocketMqConsumer.onMessage("User");

    }

}
