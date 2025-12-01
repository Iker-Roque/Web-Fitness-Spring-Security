package com.fitness.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fitness.fitness.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}