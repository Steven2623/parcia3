package com.ues.estudiantes.repository;

import com.ues.estudiantes.model.AlertaPersonalizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaPersonalizadaRepository extends JpaRepository<AlertaPersonalizada, Long> {
}