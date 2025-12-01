package com.fitness.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fitness.fitness.entity.Usuario;
import com.fitness.fitness.service.UsuarioService;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
    
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario, @RequestParam String contrasena) {
        java.util.Optional<Usuario> usuarioEncontrado = usuarioService.buscarPorUsuario(usuario);
        
        if (usuarioEncontrado.isPresent()) {
            Usuario u = usuarioEncontrado.get();
            if (u.getContrasena().equals(contrasena)) {
                return "redirect:/home";
            }
        }
        return "redirect:/login?error=true";
    }
}
