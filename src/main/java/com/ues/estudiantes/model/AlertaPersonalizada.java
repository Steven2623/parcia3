package com.ues.estudiantes.model;

import jakarta.persistence.*;

@Entity
public class AlertaPersonalizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroObservaciones;
    private int periodoDias;

    private Long tutorId; // ID del tutor que configura la alerta

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroObservaciones() {
        return numeroObservaciones;
    }

    public void setNumeroObservaciones(int numeroObservaciones) {
        this.numeroObservaciones = numeroObservaciones;
    }

    public int getPeriodoDias() {
        return periodoDias;
    }

    public void setPeriodoDias(int periodoDias) {
        this.periodoDias = periodoDias;
    }

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }
}
