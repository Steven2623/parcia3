package com.ues.estudiantes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String redirectToMenu() {
        return "redirect:/menu";
    }

    @GetMapping("/menu")
    public String mostrarMenu() {
        return "menu"; 
    }
}
