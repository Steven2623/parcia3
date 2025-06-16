package com.ues.estudiantes.controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.service.ObservacionService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/observaciones")
public class ObservacionController {

    private final ObservacionService service;

    public ObservacionController(ObservacionService service) {
        this.service = service;
    }

    // Tus métodos existentes...

    @GetMapping(value = "/reporte", produces = MediaType.APPLICATION_PDF_VALUE)
    public void descargarReportePdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=historial_observaciones.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("Reporte de Observaciones"));
        document.add(new Paragraph(" ")); // salto de línea

        List<Observacion> observaciones = service.obtenerTodas();

        for (Observacion obs : observaciones) {
            String texto = String.format("ID: %d, Tipo: %s, Descripción: %s, Fecha: %s",
                    obs.getId(), obs.getTipo(), obs.getDescripcion(), obs.getFecha());
            document.add(new Paragraph(texto));
        }

        document.close();
    }
}
