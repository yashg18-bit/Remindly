package com.remindly.backend.security;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import com.remindly.backend.entity.User;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY =
            "VGhpc0lzQVN1cGVyU2VjcmV0S2V5Rm9ySldUU2lnbmluZ1RoYXRNdXN0QmVBdExlYXN0MjU2Qml0cw==";
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey())
                .compact();

    }
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    public <T> T extractClaim(String token,
                              Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
    }
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);

    }
    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }

    public boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }

    public boolean isTokenValid(String token, User user) {

        String username = extractUsername(token);

        return username.equals(user.getEmail())
                && !isTokenExpired(token);

    }
}
