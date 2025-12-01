package com.fitness.fitness.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fitness.fitness.entity.Trainer;
import com.fitness.fitness.repository.TrainerRepository;

@Service
public class TrainerService {
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    public List<Trainer> obtenerTodos() {
        return trainerRepository.findAll();
    }
    
    public Optional<Trainer> obtenerPorId(Long id) {
        return trainerRepository.findById(id);
    }
    
    public Trainer guardar(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
    
    public void eliminar(Long id) {
        trainerRepository.deleteById(id);
    }
}