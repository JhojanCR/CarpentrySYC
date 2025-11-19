# INSTRUCCIONES PARA GENERAR DOCUMENTO WORD

## Método 1: Usando Herramientas Online (MÁS FÁCIL)

### Opción A: Pandoc Online
1. Ve a https://pandoc.org/try/
2. Copia y pega el contenido de `DOCUMENTACION_PROYECTO_FINAL.md`
3. Selecciona:
   - Input format: `markdown`
   - Output format: `docx`
4. Haz clic en "Convert"
5. Descarga el archivo .docx generado

### Opción B: Dillinger.io
1. Ve a https://dillinger.io/
2. Importa el archivo `DOCUMENTACION_PROYECTO_FINAL.md`
3. Haz clic en "Export as" → "Styled HTML"
4. Abre el HTML en Microsoft Word
5. Guarda como .docx

### Opción C: Markdown to Word
1. Ve a https://www.markdowntoword.com/
2. Arrastra el archivo `DOCUMENTACION_PROYECTO_FINAL.md`
3. Descarga el archivo Word generado

---

## Método 2: Usando Pandoc (Local)

### Instalación de Pandoc

#### Windows:
```bash
choco install pandoc
```

#### Mac:
```bash
brew install pandoc
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt-get install pandoc
```

### Generar el documento Word:
```bash
cd /home/user/CarpentrySYC/documentacion
pandoc DOCUMENTACION_PROYECTO_FINAL.md -o DOCUMENTACION_PROYECTO_FINAL.docx
```

### Con formato personalizado:
```bash
pandoc DOCUMENTACION_PROYECTO_FINAL.md \
  -o DOCUMENTACION_PROYECTO_FINAL.docx \
  --toc \
  --number-sections \
  --highlight-style=tango
```

---

## Método 3: Usando Microsoft Word Directamente

1. Abre Microsoft Word
2. Ve a "Archivo" → "Abrir"
3. Selecciona el archivo `DOCUMENTACION_PROYECTO_FINAL.md`
4. Word lo abrirá con formato básico
5. Ajusta el formato según necesites:
   - Aplica estilos de título (Título 1, Título 2, etc.)
   - Ajusta los bloques de código
   - Formatea las tablas
6. Guarda como .docx

---

## Método 4: Usando Google Docs

1. Ve a https://docs.google.com
2. Crea un nuevo documento
3. Ve a "Archivo" → "Abrir"
4. Sube el archivo `DOCUMENTACION_PROYECTO_FINAL.md`
5. Google Docs lo convertirá automáticamente
6. Ve a "Archivo" → "Descargar" → "Microsoft Word (.docx)"

---

## Método 5: Usando LibreOffice Writer (Gratuito)

1. Descarga LibreOffice desde https://www.libreoffice.org/
2. Abre LibreOffice Writer
3. Ve a "Archivo" → "Abrir"
4. Selecciona el archivo .md
5. Ajusta el formato
6. Exporta como .docx: "Archivo" → "Guardar como" → Selecciona formato "Microsoft Word 2007-365 (.docx)"

---

## Método 6: Usando Python + python-docx

Si tienes Python instalado:

```bash
pip install python-docx markdown
```

Luego ejecuta este script:

```python
import markdown
from docx import Document
from docx.shared import Pt, RGBColor
from bs4 import BeautifulSoup

# Leer Markdown
with open('DOCUMENTACION_PROYECTO_FINAL.md', 'r', encoding='utf-8') as f:
    md_content = f.read()

# Convertir a HTML
html = markdown.markdown(md_content, extensions=['tables', 'fenced_code'])

# Crear documento Word
doc = Document()

# Parsear HTML y agregar al documento
# ... (código para procesar HTML y agregar al documento)

doc.save('DOCUMENTACION_PROYECTO_FINAL.docx')
```

---

## Recomendaciones de Formato Final

Después de generar el documento Word, considera aplicar estos ajustes:

### Portada
- Título del proyecto en grande y centrado
- Tu nombre
- Nombre de la institución
- Fecha
- Logo de la institución (si aplica)

### Índice
- Tabla de contenido automática
- En Word: Referencias → Tabla de contenido → Automática

### Formato de Texto
- Fuente: Calibri o Arial 11pt para texto normal
- Títulos: Azul oscuro, negritas
- Espaciado: 1.15 o 1.5
- Márgenes: 2.5 cm en todos los lados

### Diagramas
- Inserta los diagramas desde las imágenes generadas
- Centra las imágenes
- Agrega pie de figura numerados

### Encabezado y Pie de Página
- Encabezado: Nombre del proyecto
- Pie de página: Número de página

---

## Archivos Generados

En la carpeta `documentacion/` encontrarás:

1. **DOCUMENTACION_PROYECTO_FINAL.md** - Documentación completa en Markdown
2. **DIAGRAMA_CLASES.mmd** - Diagrama de clases en Mermaid
3. **DIAGRAMA_ER.mmd** - Diagrama entidad-relación en Mermaid
4. **DIAGRAMA_CASOS_USO.mmd** - Diagrama de casos de uso en Mermaid

---

## Herramientas para Renderizar Diagramas Mermaid

Los diagramas están en formato Mermaid. Para convertirlos a imágenes:

### Online:
- https://mermaid.live/ (Recomendado - exporta a PNG/SVG)
- https://mermaid.ink/

### Editor VS Code:
- Instala la extensión "Markdown Preview Mermaid Support"
- Abre el archivo .md y verás los diagramas renderizados

### Insertar en Word:
1. Ve a https://mermaid.live/
2. Copia el código del diagrama
3. Exporta como PNG o SVG
4. Inserta la imagen en Word

---

## Solución de Problemas

### El formato no se ve bien
- Usa Pandoc con plantilla personalizada
- Ajusta manualmente en Word después de convertir

### Los diagramas no aparecen
- Los diagramas Mermaid no se convierten automáticamente
- Debes renderizarlos a imágenes primero (ver sección de Mermaid)

### Las tablas están deformadas
- Ajusta manualmente el ancho de columnas en Word
- Reduce el tamaño de fuente si es necesario

---

## Contacto

Si tienes problemas con la conversión, puedes:
1. Usar el Markdown directamente (muchos profesores aceptan MD)
2. Convertir online con Pandoc (opción más rápida)
3. Abrir con Word directamente y ajustar formato manual
