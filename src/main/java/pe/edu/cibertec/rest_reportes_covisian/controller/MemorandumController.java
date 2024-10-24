package pe.edu.cibertec.rest_reportes_covisian.controller;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Memorandum;
import pe.edu.cibertec.rest_reportes_covisian.service.IMemorandumService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/memorandum")
public class MemorandumController {
    private final IMemorandumService memorandumService;

    @GetMapping("/report/{idMemorandum}")
    public ResponseEntity<ByteArrayResource> reportFeedback(@PathVariable("idMemorandum") Integer idMemorandum) throws IOException, JRException {
        Memorandum memorandum = memorandumService.findMemorandumById(idMemorandum);
        List<Memorandum> memorandums = new ArrayList<>();
        memorandums.add(memorandum);
        InputStream inputStream = getClass().getResourceAsStream("/reportes/Reporte_Feedback.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(memorandums);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idMemorandum", idMemorandum);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
        byte[] bytesReporte = JasperExportManager.exportReportToPdf(jasperPrint);

        ByteArrayResource resource = new ByteArrayResource(bytesReporte);

        return ResponseEntity
                .ok()
                .contentLength(bytesReporte.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
