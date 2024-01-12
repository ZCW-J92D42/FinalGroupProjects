package com.peponapis.finalproject.dtos;

import com.peponapis.finalproject.model.UserEntity;

public class UserDTO {
    int id;
    String name;
    private String accessToken;
    private String tokenType = "Bearer ";

    public UserDTO(UserEntity user, String token){
        this.id = user.getUserId();
        this.name = user.getName();
        System.out.println(token);
        this.accessToken = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
