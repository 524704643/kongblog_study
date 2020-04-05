package com.website.hoho.dao;

import com.website.hoho.pojo.User;
import com.website.hoho.pojo.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    /**
     * 查询所有用户的所有信息
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
