package com.ues.estudiantes.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 

import com.ues.estudiantes.model.Estudiante;
import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.repository.EstudianteRepository;
import com.ues.estudiantes.repository.ObservacionRepository;

@Controller
@RequestMapping("/historial")
public class HistorialObservacionesController {

    @Autowired
    private ObservacionRepository observacionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/{id}")
    public String verHistorial(
            @PathVariable Long id,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            Model model
    ) {

        System.out.println("DEBUG >>> tipo: " + tipo + ", fecha: " + fecha);


        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);



        if (estudiante == null) {
            model.addAttribute("error", "Estudiante no encontrado");
            return "error";
        }

        List<Observacion> historial;

        if (fecha != null) {
            historial = observacionRepository.filtrarPorEstudianteYFechaExactaYTipo(
                    id,
                    (tipo != null && !tipo.isEmpty()) ? tipo : null,
                    fecha
            );
        } else {
            // Si no hay fecha, obtener todas las observaciones del estudiante filtradas solo por tipo (o todas si tipo también es null)
            historial = observacionRepository.filtrarPorEstudianteYFechaYTipo(
                    id,
                    (tipo != null && !tipo.isEmpty()) ? tipo : null,
                    null,
                    null
            );
        }

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("historial", historial);
        model.addAttribute("tipo", tipo);
        model.addAttribute("fecha", fecha);

        return "historial/historial-observaciones";
    }
}
