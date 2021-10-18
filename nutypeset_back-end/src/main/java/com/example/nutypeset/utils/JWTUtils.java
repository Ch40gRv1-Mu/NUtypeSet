package com.example.nutypeset.utils;

import com.example.nutypeset.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Jwt tool class
 * 1. generated token can be decrepted with base64
 * 2. if ciphertext is modified, then it will fail to decrept
 * 3. Cannot withdraw generated token until expired
 */
public class JWTUtils {
    static final long EXPIRE = 60000 * 60 * 24 * 7;// expired time: one week in ms
    /**
     * Encryption key
     */
    private static final String SECRETE = "nutypeset.net";

    /**
     * token prefix
     */
    private static final String TOKEN_PREFIX = "nutypeset";

    /**
     * subject
     */
    private static final String SUBJECT = "nutypeset";

    /**
     * Generate token according to user's information
     *
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user) {
        //System.out.println("JWTUtils");
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRETE).compact();
        token = TOKEN_PREFIX + token;
        //System.out.println("JWTUtils token:");
        //System.out.println(token);
        return token;
    }


    /**
     * verify token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRETE).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        } catch (Exception e) {
            return null;

        }
    }

}
