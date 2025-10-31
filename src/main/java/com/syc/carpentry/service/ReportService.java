package com.syc.carpentry.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.syc.carpentry.model.MensajeContacto;
import com.syc.carpentry.model.Proyecto;
import com.syc.carpentry.model.Servicio;
import com.syc.carpentry.repository.ContactoRepository;
import com.syc.carpentry.repository.ProyectoRepository;
import com.syc.carpentry.repository.ServicioRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ContactoRepository contactoRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // =================== MÉTODOS PARA CALCULAR RANGOS DE FECHAS ===================

    private LocalDateTime[] getRangoDia() {
        LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime manana = hoy.plusDays(1);
        return new LocalDateTime[]{hoy, manana};
    }

    private LocalDateTime[] getRangoSemana() {
        LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime inicioSemana = hoy.minusDays(7);
        return new LocalDateTime[]{inicioSemana, hoy.plusDays(1)};
    }

    private LocalDateTime[] getRangoMes() {
        LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime inicioMes = hoy.minusDays(30);
        return new LocalDateTime[]{inicioMes, hoy.plusDays(1)};
    }

    // =================== REPORTES DE SERVICIOS ===================

    public byte[] generarReporteServiciosPDF(String periodo) throws DocumentException {
        LocalDateTime[] rango = obtenerRangoPorPeriodo(periodo);
        List<Servicio> servicios = servicioRepository.findByFechaCreacionBetween(rango[0], rango[1]);

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        // Título
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Reporte de Servicios - " + getPeriodoTexto(periodo), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Fecha de generación
        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
        Paragraph date = new Paragraph("Generado el: " + LocalDateTime.now().format(DATE_TIME_FORMATTER), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(20);
        document.add(date);

        // Tabla
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1, 3, 5, 2});

        // Encabezados
        addTableHeader(table, new String[]{"ID", "Nombre", "Descripción", "Fecha Creación"});

        // Datos
        for (Servicio servicio : servicios) {
            table.addCell(String.valueOf(servicio.getId()));
            table.addCell(servicio.getNombre());
            table.addCell(servicio.getDescripcion() != null ? servicio.getDescripcion() : "N/A");
            table.addCell(servicio.getFechaCreacion() != null ?
                servicio.getFechaCreacion().format(DATE_TIME_FORMATTER) : "N/A");
        }

        document.add(table);

        // Total
        Paragraph total = new Paragraph("\nTotal de servicios: " + servicios.size(),
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        total.setSpacingBefore(20);
        document.add(total);

        document.close();
        return baos.toByteArray();
    }

    public byte[] generarReporteServiciosExcel(String periodo) throws IOException {
        LocalDateTime[] rango = obtenerRangoPorPeriodo(periodo);
        List<Servicio> servicios = servicioRepository.findByFechaCreacionBetween(rango[0], rango[1]);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Servicios");

            // Estilos
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);

            // Título
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Reporte de Servicios - " + getPeriodoTexto(periodo));
            titleCell.setCellStyle(headerStyle);

            // Fecha de generación
            Row dateRow = sheet.createRow(1);
            Cell dateCell = dateRow.createCell(0);
            dateCell.setCellValue("Generado el: " + LocalDateTime.now().format(DATE_TIME_FORMATTER));

            // Encabezados
            Row headerRow = sheet.createRow(3);
            String[] headers = {"ID", "Nombre", "Descripción", "Fecha Creación"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Datos
            int rowNum = 4;
            for (Servicio servicio : servicios) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(servicio.getId());
                row.createCell(1).setCellValue(servicio.getNombre());
                row.createCell(2).setCellValue(servicio.getDescripcion() != null ? servicio.getDescripcion() : "N/A");
                row.createCell(3).setCellValue(servicio.getFechaCreacion() != null ?
                    servicio.getFechaCreacion().format(DATE_TIME_FORMATTER) : "N/A");
            }

            // Total
            Row totalRow = sheet.createRow(rowNum + 1);
            Cell totalCell = totalRow.createCell(0);
            totalCell.setCellValue("Total de servicios: " + servicios.size());
            totalCell.setCellStyle(headerStyle);

            // Ajustar ancho de columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            return baos.toByteArray();
        }
    }

    // =================== REPORTES DE PROYECTOS ===================

    public byte[] generarReporteProyectosPDF(String periodo) throws DocumentException {
        LocalDate[] rango = obtenerRangoPorPeriodoProyectos(periodo);
        List<Proyecto> proyectos = proyectoRepository.findByFechaInicioBetween(rango[0], rango[1]);

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        // Título
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Reporte de Proyectos - " + getPeriodoTexto(periodo), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Fecha de generación
        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
        Paragraph date = new Paragraph("Generado el: " + LocalDateTime.now().format(DATE_TIME_FORMATTER), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(20);
        document.add(date);

        // Tabla
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1, 3, 4, 2, 2});

        // Encabezados
        addTableHeader(table, new String[]{"ID", "Nombre", "Descripción", "Fecha Inicio", "Estado"});

        // Datos
        for (Proyecto proyecto : proyectos) {
            table.addCell(String.valueOf(proyecto.getId()));
            table.addCell(proyecto.getNombre());
            table.addCell(proyecto.getDescripcion() != null ? proyecto.getDescripcion() : "N/A");
            table.addCell(proyecto.getFechaInicio() != null ?
                proyecto.getFechaInicio().format(DATE_FORMATTER) : "N/A");
            table.addCell(proyecto.getEstado() != null ? proyecto.getEstado() : "N/A");
        }

        document.add(table);

        // Total
        Paragraph total = new Paragraph("\nTotal de proyectos: " + proyectos.size(),
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        total.setSpacingBefore(20);
        document.add(total);

        document.close();
        return baos.toByteArray();
    }

    public byte[] generarReporteProyectosExcel(String periodo) throws IOException {
        LocalDate[] rango = obtenerRangoPorPeriodoProyectos(periodo);
        List<Proyecto> proyectos = proyectoRepository.findByFechaInicioBetween(rango[0], rango[1]);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Proyectos");

            // Estilos
            CellStyle headerStyle = createHeaderStyle(workbook);

            // Título
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Reporte de Proyectos - " + getPeriodoTexto(periodo));
            titleCell.setCellStyle(headerStyle);

            // Fecha de generación
            Row dateRow = sheet.createRow(1);
            Cell dateCell = dateRow.createCell(0);
            dateCell.setCellValue("Generado el: " + LocalDateTime.now().format(DATE_TIME_FORMATTER));

            // Encabezados
            Row headerRow = sheet.createRow(3);
            String[] headers = {"ID", "Nombre", "Descripción", "Fecha Inicio", "Estado"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Datos
            int rowNum = 4;
            for (Proyecto proyecto : proyectos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(proyecto.getId());
                row.createCell(1).setCellValue(proyecto.getNombre());
                row.createCell(2).setCellValue(proyecto.getDescripcion() != null ? proyecto.getDescripcion() : "N/A");
                row.createCell(3).setCellValue(proyecto.getFechaInicio() != null ?
                    proyecto.getFechaInicio().format(DATE_FORMATTER) : "N/A");
                row.createCell(4).setCellValue(proyecto.getEstado() != null ? proyecto.getEstado() : "N/A");
            }

            // Total
            Row totalRow = sheet.createRow(rowNum + 1);
            Cell totalCell = totalRow.createCell(0);
            totalCell.setCellValue("Total de proyectos: " + proyectos.size());
            totalCell.setCellStyle(headerStyle);

            // Ajustar ancho de columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            return baos.toByteArray();
        }
    }

    // =================== REPORTES DE CONTACTOS ===================

    public byte[] generarReporteContactosPDF(String periodo) throws DocumentException {
        LocalDateTime[] rango = obtenerRangoPorPeriodo(periodo);
        List<MensajeContacto> contactos = contactoRepository.findByFechaEnvioBetween(rango[0], rango[1]);

        Document document = new Document(PageSize.A4.rotate()); // Horizontal para más espacio
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        // Título
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Reporte de Contactos - " + getPeriodoTexto(periodo), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Fecha de generación
        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
        Paragraph date = new Paragraph("Generado el: " + LocalDateTime.now().format(DATE_TIME_FORMATTER), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(20);
        document.add(date);

        // Tabla
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1, 2, 3, 2, 2, 2});

        // Encabezados
        addTableHeader(table, new String[]{"ID", "Nombre", "Correo", "Teléfono", "Asunto", "Fecha Envío"});

        // Datos
        for (MensajeContacto contacto : contactos) {
            table.addCell(String.valueOf(contacto.getId()));
            table.addCell(contacto.getNombre() != null ? contacto.getNombre() : "N/A");
            table.addCell(contacto.getCorreo() != null ? contacto.getCorreo() : "N/A");
            table.addCell(contacto.getTelefono() != null ? contacto.getTelefono() : "N/A");
            table.addCell(contacto.getAsunto() != null ? contacto.getAsunto() : "N/A");
            table.addCell(contacto.getFechaEnvio() != null ?
                contacto.getFechaEnvio().format(DATE_TIME_FORMATTER) : "N/A");
        }

        document.add(table);

        // Total
        Paragraph total = new Paragraph("\nTotal de contactos: " + contactos.size(),
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        total.setSpacingBefore(20);
        document.add(total);

        document.close();
        return baos.toByteArray();
    }

    public byte[] generarReporteContactosExcel(String periodo) throws IOException {
        LocalDateTime[] rango = obtenerRangoPorPeriodo(periodo);
        List<MensajeContacto> contactos = contactoRepository.findByFechaEnvioBetween(rango[0], rango[1]);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Contactos");

            // Estilos
            CellStyle headerStyle = createHeaderStyle(workbook);

            // Título
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Reporte de Contactos - " + getPeriodoTexto(periodo));
            titleCell.setCellStyle(headerStyle);

            // Fecha de generación
            Row dateRow = sheet.createRow(1);
            Cell dateCell = dateRow.createCell(0);
            dateCell.setCellValue("Generado el: " + LocalDateTime.now().format(DATE_TIME_FORMATTER));

            // Encabezados
            Row headerRow = sheet.createRow(3);
            String[] headers = {"ID", "Nombre", "Correo", "Teléfono", "Asunto", "Mensaje", "Fecha Envío"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Datos
            int rowNum = 4;
            for (MensajeContacto contacto : contactos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(contacto.getId());
                row.createCell(1).setCellValue(contacto.getNombre() != null ? contacto.getNombre() : "N/A");
                row.createCell(2).setCellValue(contacto.getCorreo() != null ? contacto.getCorreo() : "N/A");
                row.createCell(3).setCellValue(contacto.getTelefono() != null ? contacto.getTelefono() : "N/A");
                row.createCell(4).setCellValue(contacto.getAsunto() != null ? contacto.getAsunto() : "N/A");
                row.createCell(5).setCellValue(contacto.getMensaje() != null ? contacto.getMensaje() : "N/A");
                row.createCell(6).setCellValue(contacto.getFechaEnvio() != null ?
                    contacto.getFechaEnvio().format(DATE_TIME_FORMATTER) : "N/A");
            }

            // Total
            Row totalRow = sheet.createRow(rowNum + 1);
            Cell totalCell = totalRow.createCell(0);
            totalCell.setCellValue("Total de contactos: " + contactos.size());
            totalCell.setCellStyle(headerStyle);

            // Ajustar ancho de columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            return baos.toByteArray();
        }
    }

    // =================== MÉTODOS AUXILIARES ===================

    private LocalDateTime[] obtenerRangoPorPeriodo(String periodo) {
        return switch (periodo.toLowerCase()) {
            case "dia" -> getRangoDia();
            case "semana" -> getRangoSemana();
            case "mes" -> getRangoMes();
            default -> getRangoMes();
        };
    }

    private LocalDate[] obtenerRangoPorPeriodoProyectos(String periodo) {
        LocalDateTime[] rango = obtenerRangoPorPeriodo(periodo);
        return new LocalDate[]{rango[0].toLocalDate(), rango[1].toLocalDate()};
    }

    private String getPeriodoTexto(String periodo) {
        return switch (periodo.toLowerCase()) {
            case "dia" -> "Hoy";
            case "semana" -> "Última Semana";
            case "mes" -> "Último Mes";
            default -> "Último Mes";
        };
    }

    private void addTableHeader(PdfPTable table, String[] headers) {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }
}
