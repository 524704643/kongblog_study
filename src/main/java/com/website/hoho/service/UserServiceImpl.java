package com.website.hoho.service;

import com.website.hoho.dao.UserDao;
import com.website.hoho.pojo.User;
import com.website.hoho.pojo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public List<UserDto> getUserDtos() {
        return userDao.getUserDtos();
    }

    @Override
    public UserDto getUserDto(String username) {
        return userDao.getUserDto(username);
    }
}
