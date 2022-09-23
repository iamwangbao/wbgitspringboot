package com.wangbao.springbootmysql.service.impl;

import com.wangbao.springbootmysql.pojo.User;
import com.wangbao.springbootmysql.mapper.UserMapper;
import com.wangbao.springbootmysql.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-09-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
