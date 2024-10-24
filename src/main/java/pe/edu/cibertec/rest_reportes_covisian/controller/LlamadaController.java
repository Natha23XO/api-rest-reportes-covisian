package pe.edu.cibertec.rest_reportes_covisian.controller;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Llamada;
import pe.edu.cibertec.rest_reportes_covisian.service.ILlamadaService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/llamada")
public class LlamadaController {
    private final ILlamadaService iLlamadasService;

    @GetMapping("/report/{nroOrden}")
    public ResponseEntity<ByteArrayResource> reportLLamada(@PathVariable("nroOrden") Integer nroOrden) {
        try {
            Llamada llamada = iLlamadasService.llamadaPorOrden(nroOrden);
            if (llamada == null) {
                return ResponseEntity.notFound().build();
            }
            List<Llamada> llamadas = new ArrayList<>();
            llamadas.add(llamada);
            try (InputStream inputStream = getClass().getResourceAsStream("/reportes/Reporte_Llamada.jrxml")) {
                if (inputStream == null) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(llamadas);
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("nroOrden", nroOrden);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
                byte[] bytesReporte = JasperExportManager.exportReportToPdf(jasperPrint);
                ByteArrayResource resource = new ByteArrayResource(bytesReporte);
                return ResponseEntity
                        .ok()
                        .contentLength(bytesReporte.length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            }
        } catch (IOException | JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
