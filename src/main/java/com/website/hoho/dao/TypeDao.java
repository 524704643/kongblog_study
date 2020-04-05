package com.website.hoho.dao;

import com.website.hoho.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeDao {
    /***
     * 获取文章的所有分类
     * @return
     */
    List<Type> getTypes();
}
