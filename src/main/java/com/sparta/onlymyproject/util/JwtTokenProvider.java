package com.sparta.onlymyproject.util;

import com.sparta.onlymyproject.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtTokenProvider {
    private JwtTokenProvider() {

    }

    // 토큰의 사용자 이름 생성
    public static String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    //토큰의 만료기한
    public static Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    // 토큰의 클레임
    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JwtConfig.staticSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰이 만료되었는지 검사하는 메서드
    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 주어진 사용자 이름을 사용하여 새로운 토큰 생성
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // 위에 generateToken 에 사용될 메서드인 createToken 메서드의 구현부.
    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 토큰의 시간을 구하는 정하는 로직
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.staticExpiration))
                .signWith(SignatureAlgorithm.HS256, JwtConfig.staticSecretKey).compact();
    }

    // 토큰의 유효성 검사와 사용자 이름이 일치하는지 확인.
    public static Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

}