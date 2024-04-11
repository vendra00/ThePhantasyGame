package com.carbon.thephantasyrpg.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// This class is used to encode passwords for the database
public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "user"; // Example password
        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);
    }
}
