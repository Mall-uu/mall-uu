package com.project.springmall.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtTokenProvider {
    // TODO change Property
    private static final String JWT_SECRET = "fuuFfdv0vICoD3/3ylncZ81w2F3p9zl/s66bTZHl27BuPtvWJz1bV1MhcFY9lddaj7DPUxKO7cTrgpyax4ca7SE0TbYaIdB3qJrMsJgZVtv9X6wXOpHIBR9fs69ttN4d+eSUYk8D2uWYa23XGFMgqI4TXWS9oMqc36XyU7ILRoOu10UGoFtb/LvpZuPvF/XnfmalZpi4Dij2Nj88lZI1HaTIFVHRVx5+Om0wBA==%";
    private static final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    // 토큰 유효시간
    private static final int JWT_EXPIRATION_MS = 604800000;

    // jwt 토큰 생성
    public static String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
            .setSubject((String) authentication.getPrincipal()) // 사용자
            .setIssuedAt(new Date()) // 현재 시간 기반으로 생성
            .setExpiration(expiryDate) // 만료 시간 세팅
            .signWith(key)
            .compact();
    }

    // Jwt 토큰에서 아이디 추출
    public static String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Jwt 토큰 유효성 검사
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }  catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (Exception ex) {
        }
        return false;
    }
}