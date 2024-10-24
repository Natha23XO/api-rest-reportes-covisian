package pe.edu.cibertec.rest_reportes_covisian.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Empleado;
import pe.edu.cibertec.rest_reportes_covisian.service.IEmpleadoService;

import java.awt.*;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/empleado")
public class UserController {
    private final IEmpleadoService iEmpleadoService;

    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportXlsEmpleados() throws IOException {
        List<Empleado> empleados = iEmpleadoService.listaEmpleados();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Empleados");

        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        XSSFColor headerColor = new XSSFColor(new Color(57, 191, 142), null);
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(headerColor);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        Row headerRow = sheet.createRow(0);
        String[] headers = {"DNI", "Nombres", "Apellidos", "Área"};
        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);

        int rowCount = 1;
        for(Empleado empleado: empleados) {
            Row row = sheet.createRow(rowCount++);
            Cell dniCell = row.createCell(0);
            dniCell.setCellValue(empleado.getDniEmpleado());
            dniCell.setCellStyle(cellStyle);

            Cell nomCell = row.createCell(1);
            nomCell.setCellValue(empleado.getNombreEmpleado());
            nomCell.setCellStyle(cellStyle);

            Cell apeCell = row.createCell(2);
            apeCell.setCellValue(empleado.getApellidoEmpleado());
            apeCell.setCellStyle(cellStyle);

            Cell areaCell = row.createCell(3);
            areaCell.setCellValue(empleado.getArea());
            areaCell.setCellStyle(cellStyle);
        }

        for(int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        byte[] bytesReporte = outputStream.toByteArray();

        return ResponseEntity
                .ok()
                .contentLength(bytesReporte.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=empleados.xlsx")
                .body(new ByteArrayResource(bytesReporte));
    }
}
