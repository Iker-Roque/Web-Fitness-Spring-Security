package com.fitness.fitness.service;

import com.fitness.fitness.entity.Usuario;
import com.fitness.fitness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscar usuario en la BD "
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        
        System.out.println("Usuario encontrado: " + usuario.getUsuario());
        System.out.println("Hash en BD: " + usuario.getContrasena());
        System.out.println("Rol: " + usuario.getRol());

        // Devuelve un UserDetails válido
        return User.builder()
                .username(usuario.getUsuario())   
                .password(usuario.getContrasena()) 
                .roles(usuario.getRol())              //rol de usuario     
                .build();
    }
}
