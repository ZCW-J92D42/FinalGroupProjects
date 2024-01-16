package com.peponapis.finalproject.controller;
import com.peponapis.finalproject.dtos.UserDTO;
import com.peponapis.finalproject.model.UserEntity;
import com.peponapis.finalproject.security.JWTGenerator;
import com.peponapis.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

/**
 * User Controller. End points for user registration and user login with authentication
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    /**
     *
     * @param userEntity new user to add to db
     * @return user that was added to db
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity userEntity) {
        try {
            UserEntity newUserEntity = userService.saveUser(userEntity);
            return new ResponseEntity<>(newUserEntity, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("User registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param userEntity takes in a username and password for user login
     * @return user able to log in with authentication with correct username and password
     * @throws AuthenticationException if user is not authenticated with username or password
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> authorizeLogin(@RequestBody UserEntity userEntity) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userEntity.getUserName(), userEntity.getPassword()));

        try {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            UserDTO authenticatedUserEntity = userService.authenicatorUser(userEntity.getUserName(), userEntity.getPassword(), token);
            return new ResponseEntity<>(authenticatedUserEntity, HttpStatus.OK);

        }
        catch(BadCredentialsException e){
            System.out.println("Bad Credentials!");
        }
        catch ( AuthenticationException e){
            System.out.println("Bad credentials!");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
