package com.website.hoho.service;

import com.website.hoho.pojo.Type;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TypeService {
    /***
     * 获取文章的所有分类
     * @return
     */
    List<Type> getTypes();
}
