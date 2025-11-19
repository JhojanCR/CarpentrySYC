package com.syc.carpentry.util;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utilidad para generar documentación en formato Word (.docx) a partir de archivos Markdown
 */
public class DocumentacionGenerator {

    public static void main(String[] args) {
        try {
            String markdownPath = "documentacion/DOCUMENTACION_PROYECTO_FINAL.md";
            String wordPath = "documentacion/DOCUMENTACION_PROYECTO_FINAL.docx";

            System.out.println("Generando documento Word...");
            convertirMarkdownAWord(markdownPath, wordPath);
            System.out.println("✓ Documento generado exitosamente: " + wordPath);

        } catch (Exception e) {
            System.err.println("Error al generar documento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void convertirMarkdownAWord(String markdownPath, String wordPath) throws IOException {
        // Leer el archivo Markdown
        List<String> lineas = Files.readAllLines(Paths.get(markdownPath));

        // Crear documento Word
        XWPFDocument document = new XWPFDocument();

        // Configurar márgenes
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(1440));  // 1 pulgada
        pageMar.setRight(BigInteger.valueOf(1440));
        pageMar.setTop(BigInteger.valueOf(1440));
        pageMar.setBottom(BigInteger.valueOf(1440));

        // Procesar cada línea del Markdown
        boolean enBloqueCodigo = false;
        boolean enTabla = false;
        StringBuilder codigoBuffer = new StringBuilder();

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);

            // Detectar bloques de código
            if (linea.startsWith("```")) {
                if (enBloqueCodigo) {
                    // Fin del bloque de código
                    agregarCodigoFormateado(document, codigoBuffer.toString());
                    codigoBuffer = new StringBuilder();
                    enBloqueCodigo = false;
                } else {
                    // Inicio del bloque de código
                    enBloqueCodigo = true;
                }
                continue;
            }

            if (enBloqueCodigo) {
                codigoBuffer.append(linea).append("\n");
                continue;
            }

            // Procesar encabezados
            if (linea.startsWith("# ")) {
                agregarTitulo(document, linea.substring(2), 1);
            } else if (linea.startsWith("## ")) {
                agregarTitulo(document, linea.substring(3), 2);
            } else if (linea.startsWith("### ")) {
                agregarTitulo(document, linea.substring(4), 3);
            } else if (linea.startsWith("#### ")) {
                agregarTitulo(document, linea.substring(5), 4);
            } else if (linea.startsWith("##### ")) {
                agregarTitulo(document, linea.substring(6), 5);
            } else if (linea.startsWith("###### ")) {
                agregarTitulo(document, linea.substring(7), 6);
            }
            // Procesar listas
            else if (linea.trim().startsWith("- ") || linea.trim().startsWith("* ")) {
                agregarItemLista(document, linea.trim().substring(2));
            } else if (linea.trim().matches("^\\d+\\.\\s.*")) {
                agregarItemListaNumerada(document, linea.trim().replaceFirst("^\\d+\\.\\s", ""));
            }
            // Procesar separadores
            else if (linea.trim().equals("---")) {
                agregarSeparador(document);
            }
            // Procesar tablas
            else if (linea.trim().startsWith("|")) {
                // Tablas se procesarán de forma simple (convertidas a párrafos formateados)
                agregarLineaTabla(document, linea);
            }
            // Procesar líneas normales
            else if (!linea.trim().isEmpty()) {
                // Detectar si contiene formato markdown
                if (linea.contains("**") || linea.contains("*") || linea.contains("`")) {
                    agregarParrafoConFormato(document, linea);
                } else {
                    agregarParrafo(document, linea);
                }
            } else {
                // Línea vacía - agregar espacio
                document.createParagraph();
            }
        }

        // Guardar el documento
        try (FileOutputStream out = new FileOutputStream(wordPath)) {
            document.write(out);
        }

        document.close();
    }

    private static void agregarTitulo(XWPFDocument document, String texto, int nivel) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(texto);
        run.setBold(true);

        // Configurar tamaño según nivel
        switch (nivel) {
            case 1:
                run.setFontSize(24);
                run.setColor("2E74B5");
                paragraph.setSpacingAfter(200);
                break;
            case 2:
                run.setFontSize(20);
                run.setColor("2E74B5");
                paragraph.setSpacingAfter(180);
                break;
            case 3:
                run.setFontSize(16);
                run.setColor("4472C4");
                paragraph.setSpacingAfter(160);
                break;
            case 4:
                run.setFontSize(14);
                run.setColor("5B9BD5");
                paragraph.setSpacingAfter(140);
                break;
            default:
                run.setFontSize(12);
                run.setBold(true);
                paragraph.setSpacingAfter(120);
        }

        paragraph.setSpacingBefore(200);
    }

    private static void agregarParrafo(XWPFDocument document, String texto) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(texto);
        run.setFontSize(11);
        run.setFontFamily("Calibri");
        paragraph.setSpacingAfter(120);
    }

    private static void agregarParrafoConFormato(XWPFDocument document, String texto) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingAfter(120);

        // Procesar el texto buscando formatos
        String[] partes = texto.split("(?=\\*\\*)|(?<=\\*\\*)|(?=`)|(?<=`)");
        boolean enNegrita = false;
        boolean enCodigo = false;

        for (String parte : partes) {
            if (parte.equals("**")) {
                enNegrita = !enNegrita;
                continue;
            }
            if (parte.equals("`")) {
                enCodigo = !enCodigo;
                continue;
            }

            if (!parte.isEmpty()) {
                XWPFRun run = paragraph.createRun();
                run.setText(parte);
                run.setFontSize(11);
                run.setFontFamily("Calibri");

                if (enNegrita) {
                    run.setBold(true);
                }
                if (enCodigo) {
                    run.setFontFamily("Courier New");
                    run.setColor("C7254E");
                }
            }
        }
    }

    private static void agregarItemLista(XWPFDocument document, String texto) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setIndentationLeft(720); // Sangría
        XWPFRun run = paragraph.createRun();
        run.setText("• " + texto);
        run.setFontSize(11);
        run.setFontFamily("Calibri");
        paragraph.setSpacingAfter(80);
    }

    private static void agregarItemListaNumerada(XWPFDocument document, String texto) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setIndentationLeft(720);
        XWPFRun run = paragraph.createRun();
        run.setText(texto);
        run.setFontSize(11);
        run.setFontFamily("Calibri");
        paragraph.setSpacingAfter(80);
    }

    private static void agregarCodigoFormateado(XWPFDocument document, String codigo) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setIndentationLeft(360);

        // Fondo gris claro (simulado con borde)
        paragraph.setBorderTop(Borders.SINGLE);
        paragraph.setBorderBottom(Borders.SINGLE);
        paragraph.setBorderLeft(Borders.SINGLE);
        paragraph.setBorderRight(Borders.SINGLE);

        XWPFRun run = paragraph.createRun();
        run.setText(codigo);
        run.setFontFamily("Courier New");
        run.setFontSize(10);
        paragraph.setSpacingAfter(200);
        paragraph.setSpacingBefore(100);
    }

    private static void agregarSeparador(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setBorderBottom(Borders.SINGLE);
        paragraph.setSpacingAfter(200);
        paragraph.setSpacingBefore(200);
    }

    private static void agregarLineaTabla(XWPFDocument document, String linea) {
        // Convertir tabla markdown a formato simple
        String[] celdas = linea.split("\\|");
        StringBuilder textoTabla = new StringBuilder();

        for (String celda : celdas) {
            String celdaLimpia = celda.trim();
            if (!celdaLimpia.isEmpty() && !celdaLimpia.matches("^-+$")) {
                textoTabla.append(celdaLimpia).append(" | ");
            }
        }

        if (textoTabla.length() > 0) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(textoTabla.toString());
            run.setFontSize(10);
            run.setFontFamily("Calibri");
            paragraph.setSpacingAfter(60);
        }
    }
}
