# 📊 GUÍA DE DIAGRAMAS SIMPLIFICADOS

## ¿Cuándo usar versión simple vs completa?

### ✅ USA LOS DIAGRAMAS SIMPLES SI:
- Tu profesor prefiere claridad sobre detalle
- Es una presentación oral (no queremos abrumar)
- El proyecto es de nivel intermedio
- Tienes poco espacio en el documento
- Quieres que se entienda rápido

### ✅ USA LOS DIAGRAMAS COMPLETOS SI:
- Es un proyecto de titulación/grado
- El profesor pide documentación exhaustiva
- Necesitas demostrar conocimiento técnico profundo
- Es para un portafolio profesional

---

## 📁 ARCHIVOS DE DIAGRAMAS

### Versiones SIMPLES (Recomendadas para empezar)
```
✨ DIAGRAMA_FLUJO_SIMPLE.mmd        - Flujo básico del sistema
✨ DIAGRAMA_ER_SIMPLE.mmd           - Base de datos sin anotaciones
✨ DIAGRAMA_CLASES_SIMPLE.mmd       - Solo clases principales
```

### Versiones COMPLETAS (Para proyectos avanzados)
```
📚 DIAGRAMA_ER.mmd                  - Con tipos de datos y constraints
📚 DIAGRAMA_CLASES.mmd              - Todas las clases con métodos
📚 DIAGRAMA_CASOS_USO.mmd           - 20+ casos de uso
📚 DIAGRAMA_ARQUITECTURA.mmd        - Arquitectura completa MVC
```

---

## 🎯 RECOMENDACIÓN PARA TU PROYECTO FINAL

### Opción 1: MÍNIMO REQUERIDO (3 diagramas)
```
1. DIAGRAMA_FLUJO_SIMPLE.mmd       ← Muestra cómo funciona
2. DIAGRAMA_ER_SIMPLE.mmd          ← Muestra la base de datos
3. DIAGRAMA_CLASES_SIMPLE.mmd      ← Muestra el código
```

### Opción 2: COMPLETO PERO SIMPLE (4 diagramas)
```
1. DIAGRAMA_FLUJO_SIMPLE.mmd       ← Flujo del sistema
2. DIAGRAMA_ER_SIMPLE.mmd          ← Base de datos
3. DIAGRAMA_CLASES_SIMPLE.mmd      ← Estructura del código
4. DIAGRAMA_ARQUITECTURA.mmd       ← Cómo se conecta todo
```

### Opción 3: PROFESIONAL (5-6 diagramas)
```
1. DIAGRAMA_FLUJO_SIMPLE.mmd
2. DIAGRAMA_ER.mmd (completo)
3. DIAGRAMA_CLASES.mmd (completo)
4. DIAGRAMA_CASOS_USO.mmd
5. DIAGRAMA_ARQUITECTURA.mmd
```

---

## 📖 ¿QUÉ MUESTRA CADA DIAGRAMA SIMPLE?

### 1. DIAGRAMA DE FLUJO SIMPLE
**Qué muestra:**
- Visitante navega el sitio
- Usuario se registra
- Admin gestiona contenido

**Por qué es útil:**
- Fácil de entender
- Muestra los 3 roles principales
- Cubre todos los casos importantes

### 2. DIAGRAMA ER SIMPLE
**Qué muestra:**
- 4 tablas: Usuario, Servicio, Proyecto, Mensaje
- Campos principales (sin tipos de datos complejos)
- Más limpio y legible

**Diferencia con el completo:**
- ❌ Sin anotaciones "NOT NULL", "UNIQUE"
- ❌ Sin descripciones de constraints
- ✅ Solo lo esencial

### 3. DIAGRAMA DE CLASES SIMPLE
**Qué muestra:**
- 4 entidades principales
- 2 controladores clave
- 2 repositorios
- Relaciones básicas

**Diferencia con el completo:**
- ❌ Sin todos los métodos get/set
- ❌ Sin todos los controladores
- ❌ Sin capa de servicios
- ✅ Solo la estructura principal

---

## 🎨 CÓMO USAR LOS DIAGRAMAS SIMPLES

### Paso 1: Abre Mermaid Live
```
https://mermaid.live/
```

### Paso 2: Copia el contenido del archivo
```bash
# Desde tu terminal:
cat documentacion/DIAGRAMA_FLUJO_SIMPLE.mmd
```

### Paso 3: Pega en Mermaid Live

### Paso 4: Exporta como PNG
- Click en "Actions" → "PNG"
- Descarga con nombre descriptivo

### Paso 5: Inserta en Word
- Centrado
- Tamaño: Ancho de página
- Pie de figura: "Figura 1: Diagrama de flujo del sistema"

---

## 💡 EJEMPLOS DE PIES DE FIGURA

```
Figura 1: Diagrama de flujo principal del sistema Carpentry SYC

Figura 2: Diagrama entidad-relación de la base de datos ecommerce_syc

Figura 3: Diagrama de clases simplificado - Arquitectura MVC

Figura 4: Diagrama de arquitectura del sistema (Vista general)
```

---

## 📐 TAMAÑOS RECOMENDADOS EN WORD

| Diagrama | Ancho | Orientación |
|----------|-------|-------------|
| Flujo Simple | Ancho completo (16cm) | Vertical |
| ER Simple | Ancho completo (16cm) | Horizontal |
| Clases Simple | Ancho completo (16cm) | Vertical |
| Arquitectura | Ancho completo (16cm) | Vertical |

---

## ✅ CHECKLIST PARA INSERTAR DIAGRAMAS

### Antes de exportar:
- [ ] El diagrama se ve completo
- [ ] Los colores son legibles
- [ ] El texto no está cortado

### Al insertar en Word:
- [ ] Imagen centrada
- [ ] Tamaño apropiado (no pixelada)
- [ ] Pie de figura numerado
- [ ] Referencia en el texto ("ver Figura 1")

### Calidad:
- [ ] Exportar en alta resolución (PNG)
- [ ] Si se ve borrosa, usa SVG
- [ ] Probar impresión antes de entregar

---

## 🆘 SI UN DIAGRAMA NO SE VE BIEN

### Problema: "Muy grande para una página"
**Solución:**
- Rótalo (Herramientas de imagen → Girar)
- O divídelo en 2 diagramas
- O usa orientación horizontal en esa página

### Problema: "Se ve pixelado al imprimir"
**Solución:**
- Exporta como SVG en lugar de PNG
- Inserta el SVG en Word
- SVG escala sin perder calidad

### Problema: "No entiendo el diagrama"
**Solución:**
- Agrega una leyenda
- Describe el diagrama en el texto
- Usa colores para categorizar

---

## 📝 EJEMPLO DE TEXTO PARA ACOMPAÑAR

```markdown
### 4.1 Diagrama de Flujo del Sistema

La Figura 1 muestra el flujo principal del sistema Carpentry SYC.
Los usuarios pueden:

1. **Visitantes**: Navegar el sitio, ver servicios y enviar mensajes de contacto
2. **Usuarios registrados**: Acceso a las mismas funcionalidades que visitantes
3. **Administradores**: Acceso completo al panel de administración con operaciones CRUD

El flujo está optimizado para que los visitantes puedan contactar sin
necesidad de registro, facilitando la captación de leads.

[Insertar Figura 1 aquí]
```

---

## 🎓 PREGUNTA FRECUENTE

**P: ¿Necesito todos los diagramas?**
R: No. Para un proyecto final típico, 3-4 diagramas son suficientes.

**P: ¿Simple o completo?**
R: Simple si es tu primera vez. Completo si necesitas impresionar.

**P: ¿Puedo mezclar?**
R: ¡Sí! Usa simples para flujo y ER, completo para clases si quieres.

**P: ¿Cuánto espacio ocupan?**
R: Cada diagrama = 1 página aprox. Total: 3-4 páginas de diagramas.

---

## 🚀 RECOMENDACIÓN FINAL

**Para tu proyecto, usa estos 3:**

1. ✅ `DIAGRAMA_FLUJO_SIMPLE.mmd` - Página 10
2. ✅ `DIAGRAMA_ER_SIMPLE.mmd` - Página 15
3. ✅ `DIAGRAMA_CLASES_SIMPLE.mmd` - Página 20

**Total: 3 diagramas claros y profesionales**

Esto es más que suficiente para un proyecto final bien documentado.

---

¿Necesitas que te muestre cómo se ve alguno de estos diagramas?
¡Dime cuál quieres ver! 👀
