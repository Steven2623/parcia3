package com.ues.estudiantes.controller;

import java.awt.Color;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.service.ObservacionService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/observaciones")
public class PdfExportController {

    private final ObservacionService observacionService;

    public PdfExportController(ObservacionService observacionService) {
        this.observacionService = observacionService;
    }

    @GetMapping("/reporte")
    public void exportarPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=observaciones.pdf");

        List<Observacion> lista = observacionService.obtenerTodas();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Título
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitulo.setSize(18);
        Paragraph titulo = new Paragraph("Reporte de Observaciones", fontTitulo);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" ")); // espacio

        // Tabla
        PdfPTable tabla = new PdfPTable(3); // columnas: descripción, tipo, fecha
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{4.5f, 2.5f, 2.5f});
        tabla.setSpacingBefore(10);

        // Encabezados
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);

        Font fontEncabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cell.setPhrase(new Phrase("Descripción", fontEncabezado));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("Tipo", fontEncabezado));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("Fecha", fontEncabezado));
        tabla.addCell(cell);

        // Contenido
        for (Observacion obs : lista) {
            tabla.addCell(obs.getDescripcion());
            tabla.addCell(obs.getTipo());
            tabla.addCell(obs.getFecha() != null ? obs.getFecha().toString() : "Sin fecha");
        }

        document.add(tabla);
        document.close();
    }
}
