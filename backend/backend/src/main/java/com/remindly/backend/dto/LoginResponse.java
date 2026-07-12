package com.remindly.backend.dto;

public class LoginResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public LoginResponse(String token) {
        this.token = token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
