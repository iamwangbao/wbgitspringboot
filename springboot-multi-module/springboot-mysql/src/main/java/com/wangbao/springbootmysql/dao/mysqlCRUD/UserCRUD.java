package com.wangbao.springbootmysql.dao.mysqlCRUD;

import com.wangbao.springbootmysql.pojo.User;

public interface UserCRUD {

    /**
     * 查询方法（使用 query 做前缀）
     * 新增方法（使用 add 做前缀）
     * 删除方法（使用 delete 做前缀）
     * 修改方法（使用 modify 做前缀）*/

    boolean addone(User user);
    User queryone(int userid);
    boolean modifyone(User user,int userid);
    boolean deleteone(int userid);
/*
    boolean addones();
    User queryones();
    boolean modifyones();
    boolean deleteones();

 */
}
