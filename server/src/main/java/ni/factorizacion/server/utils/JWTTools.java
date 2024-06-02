package ni.factorizacion.server.utils;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exptime}")
    private Integer exp;

    public String generateToken(String content) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(content)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }


    public String generateTokenWithExpTime(String content, Integer time) {
        Map<String, Object> claims = new HashMap<>();

        this.exp = time;

        return Jwts.builder()
                .claims(claims)
                .subject(content)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }


    public Boolean verifyToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build();

            parser.parse(token);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    public String getEmailFrom(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build();

            return parser.parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }
}