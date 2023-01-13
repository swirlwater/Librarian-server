package com.whx.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL=60*60*1000L;//60*60*1000 一个小时
    //设置密钥明文
    public static final String JWT_KEY="sangeng";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    /**
     * 生成JWT
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject){
        JwtBuilder builder=getJwtBuilder(subject,null,getUUID());//设置过期时间
        return builder.compact();
    }

    /**
     * 生成jwt
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject,Long ttlMillis){
        JwtBuilder builder=getJwtBuilder(subject,ttlMillis,getUUID());
        return builder.compact();
    }

    public static JwtBuilder getJwtBuilder(String subject,Long ttlMills,String uuid){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey =generalKey();
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        if (ttlMills==null){
            ttlMills=JwtUtil.JWT_TTL;
        }
        long expMills = nowMills + ttlMills;
        Date expDate = new Date(expMills);
        return Jwts.builder()
                .setId(uuid) //唯一的ID
                .setSubject(subject) //主题 可以是json数据
                .setIssuer("sg") //签发者
                .setIssuedAt(now) //签发时间
                .signWith(signatureAlgorithm,secretKey) //使用HS256对称加密算法签名，第二个参数为密钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id ,String subject,Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id); //设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的密钥 secretKey
     * @return
     */
    public static SecretKey generalKey(){
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKeySpec key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

    /**
     * 解析
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) {
        String jwt = createJWT("123");
        System.out.println(jwt);
        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5ZGYwZDU2OTg4YmM0NWU4ODk2ZTgxZmYyYzJiNWVlNyIsInN1YiI6IjEyMyIsImlzcyI6InNnIiwiaWF0IjoxNjczMjY0ODQ3LCJleHAiOjE2NzMyNjg0NDd9.kR2U0uXNiC9K1N554Mw6KdIcP_36vVrh52_favglmPo");
        String subject = claims.getSubject();
        System.out.println(subject);
    }
}
