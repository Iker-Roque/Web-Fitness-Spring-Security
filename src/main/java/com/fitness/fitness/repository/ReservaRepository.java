package com.fitness.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fitness.fitness.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}