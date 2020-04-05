package com.website.hoho.service;

import com.website.hoho.dao.TypeDao;
import com.website.hoho.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    TypeDao typeDao;
    @Override
    public List<Type> getTypes() {
        return typeDao.getTypes();
    }
}
