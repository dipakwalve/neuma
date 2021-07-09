package com.duroflex.neuma.app.util.jwt;

import com.duroflex.neuma.app.exception.exceptionutils.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${neuma.secret.key}")
    private String SECRET_KEY;

    public String extractUserName(String token) throws Exception {
        return extractClaim(token, Claims::getSubject);

    }

    public Date extractExpiration(String token) throws Exception {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) throws Exception {
        final Claims claims;
        try {
            claims = extractAllClaims(token);

        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws Exception {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) throws Exception {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String token = null;
        try {
            token = createToken(claims, userDetails.getUsername());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return token;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

    }

    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
