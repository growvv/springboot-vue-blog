package com.rogn.springbootvue.service.impl;

import com.rogn.springbootvue.entity.User;
import com.rogn.springbootvue.mapper.UserMapper;
import com.rogn.springbootvue.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
