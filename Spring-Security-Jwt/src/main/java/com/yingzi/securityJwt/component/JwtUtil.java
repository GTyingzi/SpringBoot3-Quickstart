package com.yingzi.securityJwt.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author yingzi
 * @date 2025/3/17:13:23
 */
@Component
@Slf4j
public class JwtUtil {
    private static final String SECRET = "zxcvbnmfdasaererafafafafafafakjlkjalkfafadffdafadfafafaaafadfadfaf1234567890";
    private static final long EXPIRE = 60 * 24 * 7; // 7 days in minutes
    public static final String HEADER = "Authorization";

    /**
     * 生成jwt token
     */
    public String generateToken(String username) {
        // 过期时间
        LocalDateTime tokenExpirationTime = LocalDateTime.now().plusMinutes(EXPIRE);
        Date expirationDate = Date.from(tokenExpirationTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
        Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes(StandardCharsets.UTF_8));
        return JWT.create()
                .withHeader(Map.of("typ", "JWT"))
                .withIssuedAt(new Date())
                .withSubject(username)
                .withExpiresAt(expirationDate)
                .withClaim("username", username)
                .sign(algorithm);
    }

    public DecodedJWT getDecodedJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes(StandardCharsets.UTF_8));
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTDecodeException e) {
            log.error("JwtUtil getDecodedJWT error: ", e);
            return null;
        }
    }

    /**
     * 检查token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    /**
     * 获得token中的自定义信息,一般是获取token的username，无需secret解密也能获得
     * @param token
     * @param field
     * @return
     */
    public String getClaimField(String token, String field) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(field).asString();
        } catch (JWTDecodeException e) {
            log.error("JwtUtil getClaimField error: ", e);
            return null;
        }
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken("admin");
        System.out.println("token = " + token);

        DecodedJWT decodedJWT = jwtUtil.getDecodedJWT(token);
        System.out.println("decodedJWT = " + decodedJWT);

        String username = jwtUtil.getClaimField(token, "username");
        System.out.println("username = " + username);
    }
}
