package com.example.nutypeset;

import com.example.nutypeset.model.entity.User;
import com.example.nutypeset.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NutypesetApplicationTests {

    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setEmail("test@u.nus.edu");
        user.setUsername("Te");
        user.setPassword("12345");
        String token = JWTUtils.geneJsonWebToken(user);
        //System.out.println("token");
        //System.out.println(token);
        Claims claims=JWTUtils.checkJWT(token);

        //System.out.println(claims.get("username"));
    }



}
