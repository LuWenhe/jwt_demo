package com.example.jwt.dao;

import com.example.jwt.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User login(User user);

}
