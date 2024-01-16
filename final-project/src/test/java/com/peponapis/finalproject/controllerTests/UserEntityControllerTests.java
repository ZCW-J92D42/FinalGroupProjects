package com.peponapis.finalproject.controllerTests;

import com.peponapis.finalproject.controller.UserController;
import com.peponapis.finalproject.model.UserEntity;
import com.peponapis.finalproject.security.JWTGenerator;
import com.peponapis.finalproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserEntityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTGenerator jwtGenerator;

    @MockBean
    private UserService userService;


    @Test
    void contextLoads(){

    }

    @Test
    public void registerUserTest() throws Exception {
        when(userService.findByUserName("userTest")).thenReturn(Optional.of(new UserEntity()));
        when(userService.findByPassword("testPassword")).thenReturn(new UserEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .param("userName", "userTest")
                .param("password", "testPassword"));
    }

    @Test
    public void loginUserTest() throws Exception {
        when(userService.findByUserName("userTest")).thenReturn(Optional.of(new UserEntity()));
        when(userService.findByPassword("testPassword")).thenReturn(new UserEntity());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .param("userName", "userTest")
                .param("password", "testPassword"));

    }
}
