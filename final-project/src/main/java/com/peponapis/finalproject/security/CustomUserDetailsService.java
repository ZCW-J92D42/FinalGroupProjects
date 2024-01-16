package com.peponapis.finalproject.security;

import com.peponapis.finalproject.model.UserEntity;
import com.peponapis.finalproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUserName(username).orElseThrow( () -> new UsernameNotFoundException("Username not found!"));
        return new User(userEntity.getUserName(), userEntity.getPassword(), new ArrayList<>());
    }
}
