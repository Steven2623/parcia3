package com.ues.estudiantes.repository;

import com.ues.estudiantes.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ObservacionRepository extends JpaRepository<Observacion, Long> {

    List<Observacion> findByDescripcionContainingIgnoreCase(String descripcion);

   @Query("SELECT o FROM Observacion o WHERE o.estudiante.id = :estudianteId " +
       "AND (:tipo IS NULL OR o.tipo = :tipo) " +
       "AND (:desde IS NULL OR o.fecha >= :desde) " +
       "AND (:hasta IS NULL OR o.fecha <= :hasta) " +
       "ORDER BY o.fecha DESC")
    List<Observacion> filtrarPorEstudianteYFechaYTipo(
      @Param("estudianteId") Long estudianteId,
      @Param("tipo") String tipo,
      @Param("desde") Date desde,
      @Param("hasta") Date hasta
    );

    @Query("SELECT o FROM Observacion o WHERE o.estudiante.id = :estudianteId " +
       "AND (:tipo IS NULL OR o.tipo = :tipo) " +
       "AND FUNCTION('DATE', o.fecha) = :fecha " +
       "ORDER BY o.fecha DESC")
    List<Observacion> filtrarPorEstudianteYFechaExactaYTipo(
      @Param("estudianteId") Long estudianteId,
      @Param("tipo") String tipo,
      @Param("fecha") Date fecha
    );



}
