package com.fitness.fitness.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypt {
    public static void main(String[] args) {
        String rawPassword = "123456"; // password del login
        String hashFromDB = "$2a$10$7qE9Pp2yM0K0mQy6k3/QuO1xjWwhtvK.Fph/6cJZl0v3lYH4M7u7G"; 

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(rawPassword, hashFromDB);

        System.out.println("Coinciden? " + matches);
    }
}
