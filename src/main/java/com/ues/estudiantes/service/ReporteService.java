package com.ues.estudiantes.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.repository.ObservacionRepository;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReporteService {

    private final ObservacionRepository observacionRepository;

    public ReporteService(ObservacionRepository observacionRepository) {
        this.observacionRepository = observacionRepository;
    }

    public void exportarHistorialObservacionesPDF(HttpServletResponse response) throws IOException {
        List<Observacion> observaciones = observacionRepository.findAll();

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitulo.setSize(18);
        fontTitulo.setColor(Color.BLUE);

        Paragraph titulo = new Paragraph("Historial de Observaciones", fontTitulo);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[] {3.5f, 3.0f, 2.5f, 2.0f});
        tabla.setSpacingBefore(10);

        tabla.addCell("Descripción");
        tabla.addCell("Tipo");
        tabla.addCell("Fecha");
        tabla.addCell("Estudiante");

        for (Observacion obs : observaciones) {
            tabla.addCell(obs.getDescripcion());
            tabla.addCell(obs.getTipo());
            tabla.addCell(obs.getFecha() != null ? obs.getFecha().toString() : "Sin fecha");
            tabla.addCell(obs.getEstudiante().getNombre()); // Suponiendo que `getNombre()` existe
        }

        documento.add(tabla);
        documento.close();
    }
}
