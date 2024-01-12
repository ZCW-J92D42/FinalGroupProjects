package com.peponapis.finalproject.service;


import com.peponapis.finalproject.dtos.UserDTO;
import com.peponapis.finalproject.model.UserEntity;
import com.peponapis.finalproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.naming.AuthenticationException;
import java.util.Optional;

/**
 * User Service. Operations relating to users are found here.
 */

@Service
@CrossOrigin(origins = "http://localhost:3000")
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    // Password encoder to ensure password security in database

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param userEntity to create or update
     * @return the new user into the user repo
     * if the username already exists, unable to create user
     */
    public UserEntity saveUser(UserEntity userEntity){
        if (userRepo.existsByUserName(userEntity.getUserName())){
            return null;
        }
        String password = passwordEncoder.encode((userEntity.getPassword()));
        userEntity.setPassword(password);

        return userRepo.save(userEntity);
    }

    /**
     * @param id for the user
     * @return the user with given id or return null
     */
    public UserEntity getUser(int id){
        if(userRepo.findById(id).isPresent()){
            return userRepo.findById(id).get();
        }
        return null;
    }

    /**
     *
     * @param name of the user
     * @return the user with given name that exists in user repo
     */
    public UserEntity findByName(String name){
        return userRepo.findByName(name);
    }

    /**
     *
     * @param username of the user
     * @return the username with given username that exists in user repo
     */
    public Optional<UserEntity> findByUserName(String username){
        return userRepo.findByUserName(username);
    }

    /**
     *
     * @param password of the user,
     * user password is encrypted, must encrypt the password before searching,
     * @return the password that is given that exists in user repo
     */
    public UserEntity findByPassword(String password) {
        String encryptPass = passwordEncoder.encode(password);
        return userRepo.findByPassword(encryptPass);
    }

    /**
     *
     * @param username of the user
     * @param password of the user
     * @return User is authenticated
     * @throws AuthenticationException if user is not authenticated throws exception
     *                                  for username and user password
     */

    public UserDTO authenicatorUser (String username, String password, String token) throws AuthenticationException {
        UserEntity userEntity = userRepo.findByUserName(username)
                .orElseThrow(() -> new AuthenticationException("Username doesn't exist"));
        if (!passwordEncoder.matches(password, userEntity.getPassword())){
            throw new AuthenticationException("Invalid Login");
        }

        UserDTO userDTO = new UserDTO(userEntity, token);

        return userDTO;
    }


}
