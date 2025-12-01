package com.fitness.fitness.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fitness.fitness.entity.Reserva;
import com.fitness.fitness.repository.ReservaRepository;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    public List<Reserva> obtenerTodos() {
        return reservaRepository.findAll();
    }
    
    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }
    
    public Reserva guardar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}