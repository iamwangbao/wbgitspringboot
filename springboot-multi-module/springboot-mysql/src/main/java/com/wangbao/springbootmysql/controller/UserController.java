package com.wangbao.springbootmysql.controller;


import com.wangbao.springbootmysql.dao.mysqlCRUD.UserCRUD;
import com.wangbao.springbootmysql.dao.mysqlCRUD.mysqlCRUDimpl.UserCRUDimpl;
import com.wangbao.springbootmysql.pojo.User;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-09-09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private User user;
    UserCRUD userCRUDimpl = new UserCRUDimpl();

    @SneakyThrows
    @RequestMapping(value="/addone",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addone(@RequestBody String json)
    {
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(json);
        //将json对象的值赋给user对象
        user.setUserName(jsonObj.getString("userName"));
        user.setPassword(jsonObj.getString("password"));
        user.setNickName(jsonObj.getString("nickName"));
        user.setSex(jsonObj.getString("sex"));
        user.setPhone(jsonObj.getInt("phone"));
        userCRUDimpl.addone(user);
        return json;
    }

    /*
    @RequestMapping(value="/queryone",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryone(@RequestBody String json)
    {
        userCRUDimpl.queryone(12);
        return "";
    }

     */
    @SneakyThrows
    @RequestMapping(value="/modifyone",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modifyone(@RequestBody String json)
    {
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(json);
        //将json对象的值赋给user对象
        user.setId(jsonObj.getInt("id"));
        user.setUserName(jsonObj.getString("userName"));
        user.setPassword(jsonObj.getString("password"));
        user.setNickName(jsonObj.getString("nickName"));
        user.setSex(jsonObj.getString("sex"));
        user.setPhone(jsonObj.getInt("phone"));
        userCRUDimpl.modifyone(user,user.getId());
        return json;
    }

    @RequestMapping("/deleteone/{id}")
    @ResponseBody
    public String deleteone(@PathVariable("id") int userid)
    {
        boolean deleteoneYN = userCRUDimpl.deleteone(userid);
        if (deleteoneYN)
            return "删除成功";
        return "null";
    }

    @RequestMapping("/queryone/{id}")
    public String queryone(@PathVariable("id") int userid)
    {
       user = userCRUDimpl.queryone(userid);
       user.getAll();
       if (user != null)
        return "查询成功";
       return "null";
    }

}

