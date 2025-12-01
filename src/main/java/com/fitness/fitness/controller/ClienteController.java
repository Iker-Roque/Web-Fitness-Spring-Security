package com.fitness.fitness.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fitness.fitness.entity.Cliente;
import com.fitness.fitness.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    // Listar todos los clientes
    @GetMapping
    public String listar(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodos();
        model.addAttribute("clientes", clientes);
        return "cliente/lista";
    }
    
    // Mostrar formulario para crear
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/crear";
    }
    
    // Guardar nuevo cliente
    @PostMapping("/guardar")
    public String guardar(Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }
    
    // Mostrar formulario para editar
    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Optional<Cliente> cliente = clienteService.obtenerPorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "cliente/editar";
        }
        return "redirect:/clientes";
    }
    
    // Actualizar cliente
    @PostMapping("/actualizar")
    public String actualizar(Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }
    
    // Eliminar cliente
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}

