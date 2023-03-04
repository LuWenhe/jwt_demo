package com.example.jwt.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.common.Result;
import com.example.jwt.interceptors.JWTInterceptor;
import com.example.jwt.model.User;
import com.example.jwt.service.UserService;
import com.example.jwt.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 访问该地址不需要经过拦截器
     */
    @GetMapping("/user/login")
    public Result login(User user) {
        User userDB = userService.login(user);

        if (userDB != null) {
            Map<String, String> payload = new HashMap<>();
            // 生成payload
            payload.put("id", String.valueOf(userDB.getId()));
            payload.put("username", user.getUsername());

            // 生成JWT令牌
            String token = JWTUtils.getToken(payload);
            return Result.success("登录成功", token, userDB.getUsername());
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    /**
     * 访问该地址需要经过拦截器
     */
    @PostMapping("/user/test")
    public Result test(HttpServletRequest request) {
        String token = request.getHeader("token");

        // 拿到token中的信息
        DecodedJWT verify = JWTUtils.verify(token);
        log.info("用户id: {}", verify.getClaim("id"));
        log.info("用户名: {}", verify.getClaim("username"));

        // 处理自己的业务逻辑
        return Result.success("请求成功!", token);
    }

}
