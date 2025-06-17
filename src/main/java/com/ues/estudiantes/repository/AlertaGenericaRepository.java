package com.ues.estudiantes.repository;

import com.ues.estudiantes.model.AlertaGenerica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaGenericaRepository extends JpaRepository<AlertaGenerica, Long> {
}
