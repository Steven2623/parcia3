package com.ues.estudiantes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ues.estudiantes.model.AlertaGenerica;
import com.ues.estudiantes.repository.AlertaGenericaRepository;

@Controller
@RequestMapping("/alertas-genericas")
public class AlertaController {

    private final AlertaGenericaRepository alertaGenericaRepo;

    public AlertaController(AlertaGenericaRepository alertaGenericaRepo) {
        this.alertaGenericaRepo = alertaGenericaRepo;
    }

    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alertaGenerica", new AlertaGenerica());
        return "crear_alerta_generica";  // nombre de la vista HTML para alertas genéricas
    }

    @PostMapping("/guardar")
    public String guardarAlertaGenerica(@ModelAttribute AlertaGenerica alertaGenerica) {
        alertaGenericaRepo.save(alertaGenerica);
        return "redirect:/menu";
    }
}
