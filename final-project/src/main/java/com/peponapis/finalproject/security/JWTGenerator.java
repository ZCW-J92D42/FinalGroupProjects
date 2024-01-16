package com.peponapis.finalproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Component that handles JWT token.
 *
 * resources:
 * https://www.youtube.com/watch?v=M3OHzfRmJa0&list=PL82C6-O4XrHe3sDCodw31GjXbwRdCyyuY&index=9&pp=iAQB
 */
@Component
public class JWTGenerator {

    /**
     *
     * @param authentication Get the details of the authenticated user.
     * @return A generated token (as a string).
     */
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
                .compact();

        return token;
    }

    /**
     *
     * @param token Token from request
     * @return The username associated with the given token
     */
    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     *
     * @param token Token from request
     * @return a boolean indicating the token's validity
     */
    public boolean validateToken(String token){
        try{
             Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token);
             return true;
        }
        catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("JWT was invalid!");
        }
    }
}
