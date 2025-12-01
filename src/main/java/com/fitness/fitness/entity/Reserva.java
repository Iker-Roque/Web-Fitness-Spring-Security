package com.fitness.fitness.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //RELACIÓN CON CLIENTE 
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    //RELACIÓN CON TRAINER (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;
    
    private LocalDateTime fechaReserva;
    private String tipoEntrenamiento; // "Personal" o "Grupal"
    private String estado; // "Confirmada", "Cancelada", "Completada"
    private String asistencia; // "Asistió", "Faltó", "Pendiente"
    private String ubicacion; // "Aire libre" o "Local"
    
    // Constructores
    public Reserva() {
    }

    public Reserva(Long id, Cliente cliente, Trainer trainer, LocalDateTime fechaReserva, 
                   String tipoEntrenamiento, String estado, String asistencia, String ubicacion) {
        this.id = id;
        this.cliente = cliente;
        this.trainer = trainer;
        this.fechaReserva = fechaReserva;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.estado = estado;
        this.asistencia = asistencia;
        this.ubicacion = ubicacion;
    }

    //GETTERS Y SETTERS COMPLETOS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(String tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}