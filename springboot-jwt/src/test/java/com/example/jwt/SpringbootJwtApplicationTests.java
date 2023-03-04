package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class SpringbootJwtApplicationTests {

    // 生成token
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();

        long time = 1000*60*60*24;

        String token = JWT.create()
                // header
                .withHeader(map)
                // payload
                .withClaim("userId", 1001)
                .withClaim("username", "luwenhe")
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                // signature
                .sign(Algorithm.HMAC256("!OCTREE"));

        System.out.println(token);
    }

    // 根据令牌和签名解析数据
    @Test
    void test() {
        // 根据加密时的secret得到验证对象, 先验证签名
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!OCTREE")).build();
        // 验证token
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDI3NDUwMTYsInVzZXJJZCI6MTAwMSwidXNlcm5hbWUiOiJsdXdlbmhlIn0.H4oRU5cErmzvmc0y-62uY4WUD2Dkrmr4NmsuE8-xOks");
        // 拿到token中的数据
        System.out.println(verify.getClaim("userId"));
        System.out.println(verify.getClaim("username"));
        System.out.println(verify.getExpiresAt());
    }

}
