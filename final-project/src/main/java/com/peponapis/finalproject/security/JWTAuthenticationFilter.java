package com.peponapis.finalproject.security;

import com.peponapis.finalproject.model.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Validates our JWT and applies the token to the session.
 *
 * resources: https://www.youtube.com/watch?v=KiYo3f7Fggs&list=PL82C6-O4XrHe3sDCodw31GjXbwRdCyyuY&index=10
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTGenerator tokenGenerator;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // not doing the typical autowired constructor because it was asking for parameters
    // when using this in the security config

    /**
     *
     * @param request The client request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJWTFromRequest(request);

        // if the token is valid
        if(StringUtils.hasText(token) && tokenGenerator.validateToken(token)){
            // get the username from the token
            String username = tokenGenerator.getUserNameFromJWT(token);
            // load userdetails instance using that username
            UserDetails user = customUserDetailsService.loadUserByUsername(username);
            // create a token using userdetails instance
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            System.out.println("valid token");
        }

        filterChain.doFilter(request, response);
    }

    /**
     *
     * @param request The request from the client
     * @return the token extracted from the request
     */
    private String getJWTFromRequest(HttpServletRequest request){
        // auth tokens are found in Authorization header
        String bearerToken = request.getHeader("Authorization");

        // if the value in the authorization header starts with Bearer &&
        // has the bearerToken has a value
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            // return the token without the Bearer part
            return bearerToken.substring(7);
        }

        return null;
    }
}
