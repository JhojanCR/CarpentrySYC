package com.syc.carpentry.controller;

import com.itextpdf.text.DocumentException;
import com.syc.carpentry.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // =================== REPORTES DE SERVICIOS ===================

    @GetMapping("/servicios/pdf")
    public ResponseEntity<byte[]> descargarReporteServiciosPDF(
            @RequestParam(defaultValue = "mes") String periodo) {
        try {
            byte[] pdfBytes = reportService.generarReporteServiciosPDF(periodo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment",
                "reporte_servicios_" + periodo + "_" + getTimestamp() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/servicios/excel")
    public ResponseEntity<byte[]> descargarReporteServiciosExcel(
            @RequestParam(defaultValue = "mes") String periodo) {
        try {
            byte[] excelBytes = reportService.generarReporteServiciosExcel(periodo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",
                "reporte_servicios_" + periodo + "_" + getTimestamp() + ".xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // =================== REPORTES DE PROYECTOS ===================

    @GetMapping("/proyectos/pdf")
    public ResponseEntity<byte[]> descargarReporteProyectosPDF(
            @RequestParam(defaultValue = "mes") String periodo) {
        try {
            byte[] pdfBytes = reportService.generarReporteProyectosPDF(periodo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment",
                "reporte_proyectos_" + periodo + "_" + getTimestamp() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/proyectos/excel")
    public ResponseEntity<byte[]> descargarReporteProyectosExcel(
            @RequestParam(defaultValue = "mes") String periodo) {
        try {
            byte[] excelBytes = reportService.generarReporteProyectosExcel(periodo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",
                "reporte_proyectos_" + periodo + "_" + getTimestamp() + ".xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // =================== REPORTES DE CONTACTOS ===================

    @GetMapping("/contactos/pdf")
    public ResponseEntity<byte[]> descargarReporteContactosPDF(
            @RequestParam(defaultValue = "mes") String periodo) {
        try {
            byte[] pdfBytes = reportService.generarReporteContactosPDF(periodo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment",
                "reporte_contactos_" + periodo + "_" + getTimestamp() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contactos/excel")
    public ResponseEntity<byte[]> descargarReporteContactosExcel(
            @RequestParam(defaultValue = "mes") String periodo) {
        try {
            byte[] excelBytes = reportService.generarReporteContactosExcel(periodo);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",
                "reporte_contactos_" + periodo + "_" + getTimestamp() + ".xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // =================== MÉTODO AUXILIAR ===================

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }
}
