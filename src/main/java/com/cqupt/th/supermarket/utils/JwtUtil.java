package com.cqupt.th.supermarket.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author helen
 * @since 2019/10/16
 */
@Component
@ConfigurationProperties(prefix = "jwt")
//@Getter
@Data
public class JwtUtil {
    //读取配置文件

    private String header;
    private String secret;
    private long expire;


    public String createJwtToken(Integer id, String username) {

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire * 1000))
                .claim("id", id)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return JwtToken;
    }


    /**
     * 检查令牌
     *
     * @param jwtToken jwt令牌
     * @return boolean
     */
    public boolean checkToken(String jwtToken) {
        if (!StringUtils.hasText(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader(header);
            if (!StringUtils.hasText(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 是否过期
     *
     * @param jwtToken jwt令牌
     * @return boolean
     */
    public boolean isExpiration(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 被jwt用户名令牌
     *
     * @param jwtToken jwt令牌
     * @return {@link String}
     */
    public String getUserNameByJwtToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 刷新令牌
     *
     * @param jwtToken jwt令牌
     * @return {@link String}
     */
    public String refreshToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            Integer id = (Integer) claims.get("id");
            String username = (String) claims.get("username");
            return createJwtToken(id, username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        String username = getUserNameByJwtToken(jwtToken);
        return username.equals(userDetails.getUsername()) && !isExpiration(jwtToken);
    }
}
