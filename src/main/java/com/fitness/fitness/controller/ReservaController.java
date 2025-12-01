package com.fitness.fitness.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fitness.fitness.entity.Reserva;
import com.fitness.fitness.entity.Cliente;
import com.fitness.fitness.entity.Trainer;
import com.fitness.fitness.service.ReservaService;
import com.fitness.fitness.service.ClienteService;
import com.fitness.fitness.service.TrainerService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private TrainerService trainerService;
    
    // Listar todas las reservas
    @GetMapping
    public String listar(Model model) {
        List<Reserva> reservas = reservaService.obtenerTodos();
        model.addAttribute("reservas", reservas);
        return "reserva/lista";
    }
    
    // Mostrar formulario para crear
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteService.obtenerTodos());
        model.addAttribute("trainers", trainerService.obtenerTodos());
        return "reserva/crear";
    }
    
    // Guardar nueva reserva
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long clienteId, 
                         @RequestParam Long trainerId,
                         @RequestParam String fechaReserva,
                         @RequestParam String tipoEntrenamiento,
                         @RequestParam String ubicacion,
                         @RequestParam String estado,
                         @RequestParam(defaultValue = "Pendiente") String asistencia) {
        
        Optional<Cliente> cliente = clienteService.obtenerPorId(clienteId);
        Optional<Trainer> trainer = trainerService.obtenerPorId(trainerId);
        
        if (cliente.isPresent() && trainer.isPresent()) {
            Reserva reserva = new Reserva();
            reserva.setCliente(cliente.get());
            reserva.setTrainer(trainer.get());
            reserva.setFechaReserva(LocalDateTime.parse(fechaReserva));
            reserva.setTipoEntrenamiento(tipoEntrenamiento);
            reserva.setUbicacion(ubicacion);
            reserva.setEstado(estado);
            reserva.setAsistencia(asistencia);
            
            reservaService.guardar(reserva);
        }
        return "redirect:/reservas";
    }
    
    // Mostrar formulario para editar
    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam Long id, Model model) {
        Optional<Reserva> reserva = reservaService.obtenerPorId(id);
        if (reserva.isPresent()) {
            model.addAttribute("reserva", reserva.get());
            model.addAttribute("clientes", clienteService.obtenerTodos());
            model.addAttribute("trainers", trainerService.obtenerTodos());
            return "reserva/editar";
        }
        return "redirect:/reservas";
    }
    
    // Actualizar reserva
    @PostMapping("/actualizar")
    public String actualizar(@RequestParam Long id,
                            @RequestParam Long clienteId, 
                            @RequestParam Long trainerId,
                            @RequestParam String fechaReserva,
                            @RequestParam String tipoEntrenamiento,
                            @RequestParam String ubicacion,
                            @RequestParam String estado,
                            @RequestParam String asistencia) {
        
        Optional<Cliente> cliente = clienteService.obtenerPorId(clienteId);
        Optional<Trainer> trainer = trainerService.obtenerPorId(trainerId);
        Optional<Reserva> reservaOpt = reservaService.obtenerPorId(id);
        
        if (cliente.isPresent() && trainer.isPresent() && reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            reserva.setCliente(cliente.get());
            reserva.setTrainer(trainer.get());
            reserva.setFechaReserva(LocalDateTime.parse(fechaReserva));
            reserva.setTipoEntrenamiento(tipoEntrenamiento);
            reserva.setUbicacion(ubicacion);
            reserva.setEstado(estado);
            reserva.setAsistencia(asistencia);
            
            reservaService.guardar(reserva);
        }
        return "redirect:/reservas";
    }
    
    // Eliminar reserva
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        reservaService.eliminar(id);
        return "redirect:/reservas";
    }
}