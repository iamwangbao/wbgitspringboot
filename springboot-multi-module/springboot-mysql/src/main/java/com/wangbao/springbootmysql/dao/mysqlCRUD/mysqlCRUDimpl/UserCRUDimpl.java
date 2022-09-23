package com.wangbao.springbootmysql.dao.mysqlCRUD.mysqlCRUDimpl;

import com.wangbao.springbootmysql.dao.mysqlCRUD.UserCRUD;
import com.wangbao.springbootmysql.pojo.User;
import com.wangbao.springbootmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class UserCRUDimpl implements UserCRUD {

    public static UserCRUDimpl userCRUDimpl;
    @PostConstruct
    public void init()
    {
        userCRUDimpl=this;
    }

    @Autowired
    private UserService userService;

    @Override
    public boolean addone(User user) {

        return userCRUDimpl.userService.save(user);

    }

    @Override
    public User queryone(int userid) {

        return  userCRUDimpl.userService.getById(userid);
    }

    @Override
    public boolean modifyone(User user,int userid) {

        return  userCRUDimpl.userService.updateById(user);
    }

    @Override
    public boolean deleteone(int userid) {

        return  userCRUDimpl.userService.removeById(userid);
    }

/*
    @Override
    public boolean addones() {
        return false;
    }

    @Override
    public User queryones() {
        return null;
    }

    @Override
    public boolean modifyones() {
        return false;
    }

    @Override
    public boolean deleteones() {
        return false;
    }
 */
}
