package com.peponapis.finalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


/**
 * Security configuration for user passwords encryption in database
 *
 * Resources:
 * https://www.youtube.com/watch?v=L8M_eXV0OVk
 * https://www.youtube.com/watch?v=c9qCrekFTG4
 * https://www.youtube.com/watch?v=Nw-HupzygTM
 * https://www.youtube.com/watch?v=BRl2ZHqF-wQ
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthEntryPoint authEntryPoint;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthEntryPoint authEntryPoint){
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth ->
                // the paths identified here are open to anyone without authentication
                auth.requestMatchers("/users/**","/docment/update", "/document/getAll","/document/view/**","/document/search/**", "/folder,", "/document/getsorted"
                        ).permitAll()
                        .anyRequest().authenticated()) // to reach any other path, you must be authenticated
                .csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
                .exceptionHandling((e) -> e.authenticationEntryPoint(authEntryPoint))
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // resource: https://stackoverflow.com/questions/77266685/spring-security-6-cors-is-deprecated-and-marked-for-removal
    // have to configure cors because it wasn't allowing put request
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //Make the below setting as * to allow connection from any hos
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000")); // allow requests from localhost:3000
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // allow these types  of requests
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}
