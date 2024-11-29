package com.yash.SpringSecurityAuthApp.services;

public interface JWTService {
    String generateToken(String username);
}
