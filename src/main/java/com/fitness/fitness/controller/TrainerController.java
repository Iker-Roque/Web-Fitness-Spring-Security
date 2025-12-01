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
import com.fitness.fitness.entity.Trainer;
import com.fitness.fitness.service.TrainerService;

@Controller
@RequestMapping("/trainers")
public class TrainerController {
    
    @Autowired
    private TrainerService trainerService;
    
    @GetMapping
    public String listar(Model model) {
        List<Trainer> trainers = trainerService.obtenerTodos();
        model.addAttribute("trainers", trainers);
        return "trainer/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "trainer/crear";
    }
    
    @PostMapping("/guardar")
    public String guardar(Trainer trainer) {
        trainerService.guardar(trainer);
        return "redirect:/trainers";
    }
    
    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Optional<Trainer> trainer = trainerService.obtenerPorId(id);
        if (trainer.isPresent()) {
            model.addAttribute("trainer", trainer.get());
            return "trainer/editar";
        }
        return "redirect:/trainers";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(Trainer trainer) {
        trainerService.guardar(trainer);
        return "redirect:/trainers";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        trainerService.eliminar(id);
        return "redirect:/trainers";
    }
}