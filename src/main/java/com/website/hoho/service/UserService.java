package com.website.hoho.service;

import com.website.hoho.pojo.User;
import com.website.hoho.pojo.dto.UserDto;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> getUsers();

    /**
     * 查询所有用户的部分登录信息
     * @return
     */
    List<UserDto> getUserDtos();

    /**
     * 根据username查找用户
     * @param username 用户名
     * @return
     */
    UserDto getUserDto(String username);
}
