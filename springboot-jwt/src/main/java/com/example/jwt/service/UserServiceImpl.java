package com.example.jwt.service;

import com.example.jwt.dao.UserDao;
import com.example.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        User userDB = userDao.login(user);

        // 如果在数据库中查询到用户信息
        if (userDB != null) {
            return userDB;
        }

        return null;
    }

}
