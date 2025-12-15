package com.fitness.fitness.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarHash {
    public static void main(String[] args) {
        String rawPassword = "123456"; // La contraseña que quieres usar
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(rawPassword);

        System.out.println("Hash generado: " + hash);
    }
}