# GUÍA EDUCATIVA: HTML CON THYMELEAF EN EL PROYECTO CARPENTRY SYC

## 📚 Tabla de Contenidos
1. [Introducción a HTML](#introducción-a-html)
2. [Estructura básica de HTML](#estructura-básica-de-html)
3. [Elementos HTML fundamentales](#elementos-html-fundamentales)
4. [Formularios HTML](#formularios-html)
5. [Introducción a Thymeleaf](#introducción-a-thymeleaf)
6. [Sintaxis de Thymeleaf](#sintaxis-de-thymeleaf)
7. [Expresiones y variables](#expresiones-y-variables)
8. [Iteraciones y condicionales](#iteraciones-y-condicionales)
9. [Fragmentos y layouts](#fragmentos-y-layouts)
10. [Ejemplos del proyecto](#ejemplos-del-proyecto)

---

## Introducción a HTML

**HTML (HyperText Markup Language)** es el lenguaje de marcado que estructura el contenido de las páginas web.

### ¿Qué es un lenguaje de marcado?

HTML usa **etiquetas** (tags) para definir la estructura y el tipo de contenido:

```html
<!-- Etiqueta de apertura y cierre -->
<etiqueta>Contenido</etiqueta>

<!-- Ejemplos -->
<h1>Título principal</h1>
<p>Este es un párrafo de texto.</p>
<a href="/contacto">Ir a contacto</a>

<!-- Etiquetas auto-cerradas (sin contenido) -->
<img src="imagen.jpg" alt="Descripción">
<br>  <!-- Salto de línea -->
<hr>  <!-- Línea horizontal -->
```

### Anatomía de una etiqueta HTML

```html
<a href="https://ejemplo.com" target="_blank" class="enlace-azul">
│  │                          │              │
│  └─ atributo="valor"        │              └─ contenido
└─ nombre de la etiqueta      └─ más atributos
   Texto del enlace
</a>
└─ etiqueta de cierre
```

---

## Estructura básica de HTML

Todo documento HTML sigue esta estructura:

```html
<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Metadatos: información sobre la página (no visible) -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Título de la página</title>

    <!-- Enlaces a CSS -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Contenido visible de la página -->
    <h1>Bienvenido</h1>
    <p>Este es el contenido de la página.</p>

    <!-- Scripts de JavaScript -->
    <script src="script.js"></script>
</body>
</html>
```

### Explicación de cada sección

#### 1. DOCTYPE
```html
<!DOCTYPE html>
```
- Declara que este es un documento HTML5
- Debe ser la primera línea del archivo
- No es una etiqueta HTML, es una instrucción para el navegador

#### 2. HTML
```html
<html lang="es">
```
- Elemento raíz que contiene toda la página
- `lang="es"` indica que el idioma es español

#### 3. HEAD
```html
<head>
    <!-- Metadatos y recursos -->
</head>
```
- Contiene información sobre la página (no visible)
- Incluye: título, metadatos, enlaces a CSS, fuentes, favicon

**Elementos comunes en `<head>`:**

```html
<head>
    <!-- Codificación de caracteres (UTF-8 soporta acentos, ñ, emojis) -->
    <meta charset="UTF-8">

    <!-- Configuración responsive -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Descripción para SEO -->
    <meta name="description" content="Carpintería SYC - Muebles a medida">

    <!-- Título (aparece en la pestaña del navegador) -->
    <title>Carpintería SYC</title>

    <!-- Enlaces a hojas de estilo CSS -->
    <link rel="stylesheet" href="styles.css">

    <!-- Enlaces a fuentes de Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

    <!-- Font Awesome para iconos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
```

#### 4. BODY
```html
<body>
    <!-- Contenido visible de la página -->
</body>
```
- Contiene todo el contenido visible
- Incluye: textos, imágenes, formularios, scripts

---

## Elementos HTML fundamentales

### Encabezados (Headings)

```html
<h1>Título principal (más importante)</h1>
<h2>Subtítulo de nivel 2</h2>
<h3>Subtítulo de nivel 3</h3>
<h4>Subtítulo de nivel 4</h4>
<h5>Subtítulo de nivel 5</h5>
<h6>Subtítulo de nivel 6 (menos importante)</h6>
```

**Buenas prácticas:**
- Solo un `<h1>` por página (título principal)
- Usar orden lógico: h1 → h2 → h3 (no saltar niveles)

### Párrafos y texto

```html
<!-- Párrafo -->
<p>Este es un párrafo de texto.</p>

<!-- Énfasis (cursiva) -->
<em>Texto enfatizado</em>

<!-- Importancia (negrita) -->
<strong>Texto importante</strong>

<!-- Salto de línea -->
Primera línea<br>Segunda línea

<!-- Línea horizontal -->
<hr>
```

### Enlaces (Links)

```html
<!-- Enlace externo -->
<a href="https://google.com">Ir a Google</a>

<!-- Enlace interno (misma página) -->
<a href="/contacto">Ir a contacto</a>

<!-- Enlace que abre en nueva pestaña -->
<a href="https://google.com" target="_blank">Google (nueva pestaña)</a>

<!-- Enlace a sección de la misma página -->
<a href="#seccion-contacto">Ir a contacto</a>
<div id="seccion-contacto">...</div>

<!-- Enlace de email -->
<a href="mailto:info@carpinteriasyc.com">Enviar email</a>

<!-- Enlace de teléfono -->
<a href="tel:+573001234567">Llamar</a>
```

### Imágenes

```html
<!-- Imagen básica -->
<img src="ruta/imagen.jpg" alt="Descripción de la imagen">

<!-- Con tamaño -->
<img src="imagen.jpg" alt="Logo" width="200" height="100">

<!-- Imagen desde URL -->
<img src="https://example.com/imagen.jpg" alt="Foto">
```

**Atributos importantes:**
- `src`: Ruta de la imagen (obligatorio)
- `alt`: Texto alternativo para accesibilidad (obligatorio)
- `width/height`: Dimensiones de la imagen

### Listas

```html
<!-- Lista desordenada (bullets) -->
<ul>
    <li>Primer elemento</li>
    <li>Segundo elemento</li>
    <li>Tercer elemento</li>
</ul>

<!-- Lista ordenada (números) -->
<ol>
    <li>Paso 1</li>
    <li>Paso 2</li>
    <li>Paso 3</li>
</ol>
```

### Contenedores (Divs y Spans)

```html
<!-- DIV: Contenedor de bloque (ocupa todo el ancho) -->
<div class="contenedor">
    <h2>Título</h2>
    <p>Contenido...</p>
</div>

<!-- SPAN: Contenedor en línea (solo ocupa lo necesario) -->
<p>Este es un <span class="destacado">texto destacado</span> en el párrafo.</p>
```

### Elementos semánticos HTML5

HTML5 introdujo etiquetas semánticas que describen su propósito:

```html
<!-- Encabezado de la página -->
<header>
    <nav>
        <a href="/">Inicio</a>
        <a href="/servicios">Servicios</a>
        <a href="/contacto">Contacto</a>
    </nav>
</header>

<!-- Contenido principal -->
<main>
    <!-- Sección -->
    <section id="servicios">
        <h2>Nuestros Servicios</h2>
        <article>
            <h3>Cocinas</h3>
            <p>Diseñamos cocinas a medida...</p>
        </article>
    </section>
</main>

<!-- Barra lateral -->
<aside>
    <h3>Información adicional</h3>
</aside>

<!-- Pie de página -->
<footer>
    <p>&copy; 2024 Carpintería SYC</p>
</footer>
```

**Ventajas de elementos semánticos:**
- Mejor SEO (Google entiende mejor la estructura)
- Mayor accesibilidad
- Código más legible

---

## Formularios HTML

Los formularios permiten a los usuarios enviar datos al servidor.

### Estructura básica

```html
<form action="/guardar" method="POST">
    <!-- Campos del formulario -->

    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required>

    <button type="submit">Enviar</button>
</form>
```

**Atributos de `<form>`:**
- `action`: URL donde se envían los datos
- `method`: Método HTTP (GET o POST)

### Tipos de inputs

```html
<!-- Texto simple -->
<input type="text" name="nombre" placeholder="Ingrese su nombre">

<!-- Email (valida formato) -->
<input type="email" name="email" placeholder="correo@ejemplo.com">

<!-- Contraseña (oculta el texto) -->
<input type="password" name="password">

<!-- Número -->
<input type="number" name="edad" min="0" max="120">

<!-- Fecha -->
<input type="date" name="fecha">

<!-- Archivo -->
<input type="file" name="archivo">

<!-- Checkbox (casilla de verificación) -->
<input type="checkbox" name="acepto" id="acepto">
<label for="acepto">Acepto términos y condiciones</label>

<!-- Radio buttons (selección única) -->
<input type="radio" name="genero" value="M" id="masculino">
<label for="masculino">Masculino</label>
<input type="radio" name="genero" value="F" id="femenino">
<label for="femenino">Femenino</label>

<!-- Select (lista desplegable) -->
<select name="ciudad">
    <option value="">Seleccione...</option>
    <option value="bogota">Bogotá</option>
    <option value="medellin">Medellín</option>
    <option value="cali">Cali</option>
</select>

<!-- Textarea (texto largo) -->
<textarea name="mensaje" rows="5" cols="40" placeholder="Escriba su mensaje..."></textarea>
```

### Labels y accesibilidad

```html
<!-- Forma 1: label con atributo 'for' -->
<label for="email">Email:</label>
<input type="email" id="email" name="email">

<!-- Forma 2: label envolviendo el input -->
<label>
    Email:
    <input type="email" name="email">
</label>
```

**Importancia del label:**
- Mejora accesibilidad (lectores de pantalla)
- Hacer click en el label también activa el input

### Validación HTML5

```html
<!-- Campo obligatorio -->
<input type="text" name="nombre" required>

<!-- Longitud mínima/máxima -->
<input type="text" name="usuario" minlength="3" maxlength="20">

<!-- Patrón (expresión regular) -->
<input type="text" name="telefono" pattern="[0-9]{10}" title="10 dígitos">

<!-- Rango de números -->
<input type="number" name="edad" min="18" max="100">
```

### Ejemplo completo: Formulario de contacto

```html
<form action="/contacto/enviar" method="POST" class="formulario-contacto">
    <!-- Nombre -->
    <div class="form-group">
        <label for="nombre">Nombre completo*</label>
        <input type="text"
               id="nombre"
               name="nombre"
               class="form-control"
               placeholder="Juan Pérez"
               required>
    </div>

    <!-- Email -->
    <div class="form-group">
        <label for="email">Email*</label>
        <input type="email"
               id="email"
               name="email"
               class="form-control"
               placeholder="juan@ejemplo.com"
               required>
    </div>

    <!-- Asunto -->
    <div class="form-group">
        <label for="asunto">Asunto*</label>
        <input type="text"
               id="asunto"
               name="asunto"
               class="form-control"
               placeholder="Cotización de cocina"
               required>
    </div>

    <!-- Mensaje -->
    <div class="form-group">
        <label for="mensaje">Mensaje*</label>
        <textarea id="mensaje"
                  name="mensaje"
                  class="form-control"
                  rows="5"
                  placeholder="Escriba su mensaje aquí..."
                  required></textarea>
    </div>

    <!-- Botón de envío -->
    <button type="submit" class="btn btn-primary">
        <i class="fas fa-paper-plane"></i> Enviar mensaje
    </button>
</form>
```

---

## Introducción a Thymeleaf

**Thymeleaf** es un motor de plantillas para Java que se integra perfectamente con Spring Boot.

### ¿Qué hace Thymeleaf?

Permite generar HTML dinámico insertando datos del servidor (Java) en las plantillas HTML:

```html
<!-- Sin Thymeleaf (HTML estático) -->
<h1>Bienvenido, Usuario</h1>

<!-- Con Thymeleaf (HTML dinámico) -->
<h1>Bienvenido, <span th:text="${nombreUsuario}">Usuario</span></h1>
<!--                              ↑ Variable del servidor -->
```

### Ventajas de Thymeleaf

1. **Natural templating**: Los archivos son HTML válido (se pueden abrir en navegador sin servidor)
2. **Integración con Spring**: Acceso directo a objetos del modelo
3. **Sintaxis clara**: Usa atributos HTML estándar con prefijo `th:`
4. **Seguridad**: Escape automático de HTML para prevenir XSS

### Configuración en el proyecto

**1. Ubicación de plantillas:**
```
src/main/resources/templates/
    ├── index.html
    ├── admin/
    │   ├── dashboard.html
    │   ├── servicios.html
    │   └── proyectos.html
    └── auth/
        ├── login.html
        └── register.html
```

**2. Namespace en HTML:**
```html
<html xmlns:th="http://www.thymeleaf.org">
<!--      ↑ Declara que usaremos atributos Thymeleaf -->
```

**3. En el controlador (Java):**
```java
@GetMapping("/dashboard")
public String dashboard(Model model) {
    // Agregar datos al modelo
    model.addAttribute("nombreUsuario", "Juan");
    model.addAttribute("serviciosCount", 25);

    // Retornar nombre de la vista (sin extensión .html)
    return "admin/dashboard";
}
```

---

## Sintaxis de Thymeleaf

Thymeleaf usa atributos HTML con prefijo `th:`:

### th:text - Insertar texto

```html
<!-- Insertar texto simple (reemplaza el contenido) -->
<p th:text="${mensaje}">Texto por defecto</p>

<!-- Concatenar texto -->
<p th:text="'Bienvenido, ' + ${nombre}">Bienvenido, Usuario</p>

<!-- Si mensaje = "Hola Mundo", se renderiza: -->
<p>Hola Mundo</p>
```

### th:utext - Insertar HTML sin escapar

```html
<!-- th:text escapa HTML (seguro) -->
<div th:text="${contenidoHtml}"></div>
<!-- Si contenidoHtml = "<b>Hola</b>", renderiza: &lt;b&gt;Hola&lt;/b&gt; -->

<!-- th:utext no escapa HTML (cuidado con XSS) -->
<div th:utext="${contenidoHtml}"></div>
<!-- Si contenidoHtml = "<b>Hola</b>", renderiza: <b>Hola</b> -->
```

⚠️ **Usar th:utext solo con contenido confiable**

### th:attr - Modificar atributos

```html
<!-- Cambiar el atributo 'value' de un input -->
<input type="text" th:attr="value=${nombre}">

<!-- Forma más común (atajos específicos): -->
<input type="text" th:value="${nombre}">
```

### Atajos para atributos comunes

```html
<!-- href -->
<a th:href="${url}">Enlace</a>

<!-- src -->
<img th:src="${urlImagen}" alt="Imagen">

<!-- value -->
<input th:value="${valor}">

<!-- class (agregar clases) -->
<div th:class="${esActivo} ? 'activo' : 'inactivo'">

<!-- disabled -->
<button th:disabled="${!habilitado}">Botón</button>

<!-- placeholder -->
<input th:placeholder="${mensajeAyuda}">
```

### th:href con URLs

```html
<!-- URL absoluta -->
<a th:href="@{/admin/dashboard}">Dashboard</a>
<!-- Renderiza: <a href="/admin/dashboard">Dashboard</a> -->

<!-- URL con parámetros -->
<a th:href="@{/admin/servicio/editar(id=${servicio.id})}">Editar</a>
<!-- Si servicio.id = 5, renderiza: <a href="/admin/servicio/editar?id=5">Editar</a> -->

<!-- URL con múltiples parámetros -->
<a th:href="@{/buscar(q=${query},page=${pageNum})}">Buscar</a>
<!-- Renderiza: <a href="/buscar?q=cocinas&page=1">Buscar</a> -->

<!-- URL dinámica completa -->
<a th:href="@{${urlDinamica}}">Enlace</a>
```

### th:action para formularios

```html
<!-- Acción estática -->
<form th:action="@{/contacto/enviar}" method="POST">
    ...
</form>

<!-- Acción con parámetros -->
<form th:action="@{/servicio/actualizar(id=${servicio.id})}" method="POST">
    ...
</form>
```

---

## Expresiones y variables

### Tipos de expresiones en Thymeleaf

```html
<!-- 1. Variables del modelo: ${...} -->
<p th:text="${nombre}">Juan</p>
<p th:text="${usuario.email}">correo@ejemplo.com</p>

<!-- 2. Parámetros de la URL: ${param...} -->
<!-- URL: /buscar?q=cocina -->
<p th:text="${param.q}">término de búsqueda</p>

<!-- 3. Variables de sesión: ${session...} -->
<p th:text="${session.usuario}">Usuario</p>

<!-- 4. Selección de variable: *{...} (dentro de th:object) -->
<form th:object="${servicio}">
    <input th:field="*{nombre}">  <!-- Equivale a ${servicio.nombre} -->
</form>

<!-- 5. URLs: @{...} -->
<a th:href="@{/contacto}">Contacto</a>

<!-- 6. Mensajes (i18n): #{...} -->
<p th:text="#{mensaje.bienvenida}">Bienvenido</p>

<!-- 7. Fragmentos: ~{...} -->
<div th:insert="~{fragments/footer :: footer}">
```

### Acceso a propiedades de objetos

```java
// En el controlador
Servicio servicio = new Servicio();
servicio.setId(1L);
servicio.setNombre("Cocina integral");
servicio.setPrecio(5000000);
model.addAttribute("servicio", servicio);
```

```html
<!-- En la plantilla -->
<p th:text="${servicio.id}">1</p>
<p th:text="${servicio.nombre}">Cocina integral</p>
<p th:text="${servicio.precio}">5000000</p>

<!-- Llamar métodos -->
<p th:text="${servicio.getNombre()}">Cocina integral</p>

<!-- Usar operador safe navigation (?.) para evitar NullPointerException -->
<p th:text="${servicio?.nombre}">N/A</p>
<!-- Si servicio es null, muestra "N/A" en lugar de error -->
```

### Operadores

```html
<!-- Aritméticos: +, -, *, /, % -->
<p th:text="${precio * cantidad}">Total</p>

<!-- Comparación: >, <, >=, <=, ==, != -->
<p th:text="${edad >= 18} ? 'Mayor de edad' : 'Menor de edad'">Estado</p>

<!-- Lógicos: and, or, not (o &&, ||, !) -->
<p th:text="${activo and verificado}">Estado</p>

<!-- Condicional ternario: condición ? valor_si_true : valor_si_false -->
<span th:text="${stock > 0} ? 'Disponible' : 'Agotado'">Estado</span>

<!-- Elvis operator: ?: (valor por defecto si null) -->
<p th:text="${nombre} ?: 'Sin nombre'">Nombre</p>
```

### Concatenación de strings

```html
<!-- Operador + -->
<p th:text="'Hola, ' + ${nombre} + '!'">Hola, Juan!</p>

<!-- Literal substitution (más legible) -->
<p th:text="|Hola, ${nombre}!|">Hola, Juan!</p>

<!-- En atributos -->
<div th:class="|alert alert-${tipo}|">...</div>
<!-- Si tipo = "success", renderiza: class="alert alert-success" -->
```

---

## Iteraciones y condicionales

### th:if / th:unless - Condicionales

```html
<!-- Mostrar si la condición es true -->
<div th:if="${usuario != null}">
    <p>Bienvenido, <span th:text="${usuario.nombre}">Usuario</span></p>
</div>

<!-- Mostrar si la condición es false (opuesto de th:if) -->
<div th:unless="${usuario != null}">
    <p>Por favor, inicie sesión</p>
</div>

<!-- Equivalente con negación -->
<div th:if="${usuario == null}">
    <p>Por favor, inicie sesión</p>
</div>
```

**Ejemplo con comparaciones:**

```html
<!-- Verificar si hay servicios -->
<div th:if="${!servicios.isEmpty()}">
    <p>Se encontraron <span th:text="${servicios.size()}">0</span> servicios</p>
</div>

<div th:unless="${!servicios.isEmpty()}">
    <p>No hay servicios disponibles</p>
</div>

<!-- Mostrar mensaje de error si existe -->
<div th:if="${error}" class="alert alert-danger">
    <p th:text="${error}">Mensaje de error</p>
</div>

<!-- Mostrar botón solo si es admin -->
<button th:if="${rol == 'ADMIN'}" class="btn btn-danger">Eliminar</button>
```

### th:switch / th:case - Switch case

```html
<div th:switch="${estado}">
    <p th:case="'PENDIENTE'" class="text-warning">Estado: Pendiente</p>
    <p th:case="'EN_PROCESO'" class="text-info">Estado: En proceso</p>
    <p th:case="'COMPLETADO'" class="text-success">Estado: Completado</p>
    <p th:case="'CANCELADO'" class="text-danger">Estado: Cancelado</p>
    <p th:case="*">Estado desconocido</p>  <!-- Default case -->
</div>
```

### th:each - Iteraciones (loops)

```html
<!-- Iterar sobre una lista -->
<table>
    <tr th:each="servicio : ${servicios}">
        <td th:text="${servicio.id}">1</td>
        <td th:text="${servicio.nombre}">Cocina</td>
        <td th:text="${servicio.precio}">5000000</td>
    </tr>
</table>
```

**Sintaxis:**
```html
th:each="variable : ${coleccion}"
<!--      ↑ nombre   ↑ lista del modelo -->
```

**Con índice y estado:**

```html
<tr th:each="servicio, iterStat : ${servicios}">
    <td th:text="${iterStat.index}">0</td>      <!-- Índice (0, 1, 2...) -->
    <td th:text="${iterStat.count}">1</td>      <!-- Contador (1, 2, 3...) -->
    <td th:text="${servicio.nombre}">Nombre</td>
    <td>
        <!-- Verificar si es el primer elemento -->
        <span th:if="${iterStat.first}" class="badge">Primero</span>

        <!-- Verificar si es el último elemento -->
        <span th:if="${iterStat.last}" class="badge">Último</span>

        <!-- Verificar si es par o impar -->
        <span th:if="${iterStat.even}" class="badge">Par</span>
        <span th:if="${iterStat.odd}" class="badge">Impar</span>
    </td>
</tr>
```

**Propiedades de iterStat:**
- `index`: Índice actual (0-based)
- `count`: Contador (1-based)
- `size`: Total de elementos
- `current`: Elemento actual
- `even`: true si el índice es par
- `odd`: true si el índice es impar
- `first`: true si es el primer elemento
- `last`: true si es el último elemento

**Ejemplo completo del proyecto:**

```html
<table class="table table-striped">
    <thead>
        <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <!-- Si no hay servicios -->
        <tr th:if="${servicios.isEmpty()}">
            <td colspan="5" class="text-center">
                No hay servicios registrados
            </td>
        </tr>

        <!-- Iterar servicios -->
        <tr th:each="servicio : ${servicios}">
            <td th:text="${servicio.id}">1</td>
            <td th:text="${servicio.nombre}">Cocina integral</td>
            <td th:text="${servicio.descripcion}">Descripción...</td>
            <td th:text="${'$' + servicio.precio}">$5,000,000</td>
            <td>
                <a th:href="@{/admin/servicio/editar(id=${servicio.id})}"
                   class="btn btn-sm btn-primary">
                    Editar
                </a>
                <form th:action="@{/admin/servicio/eliminar(id=${servicio.id})}"
                      method="POST"
                      style="display: inline;">
                    <button type="submit" class="btn btn-sm btn-danger">
                        Eliminar
                    </button>
                </form>
            </td>
        </tr>
    </tbody>
</table>
```

---

## Fragmentos y layouts

Los **fragmentos** permiten reutilizar código HTML en múltiples páginas.

### Definir un fragmento

**archivo: fragments/header.html**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Fragmentos</title>
</head>
<body>
    <!-- Definir fragmento con th:fragment -->
    <nav th:fragment="navbar" class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/">SYC Admin</a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" th:href="@{/admin/dashboard}">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" th:href="@{/admin/servicios}">Servicios</a>
            </li>
        </ul>
    </nav>
</body>
</html>
```

### Incluir un fragmento

Hay tres formas de incluir fragmentos:

#### 1. th:insert - Insertar dentro del elemento

```html
<div th:insert="~{fragments/header :: navbar}">
    <!-- El navbar se inserta DENTRO del div -->
</div>

<!-- Resultado: -->
<div>
    <nav class="navbar navbar-dark bg-dark">...</nav>
</div>
```

#### 2. th:replace - Reemplazar el elemento

```html
<div th:replace="~{fragments/header :: navbar}">
    <!-- El div es REEMPLAZADO por el navbar -->
</div>

<!-- Resultado: -->
<nav class="navbar navbar-dark bg-dark">...</nav>
```

#### 3. th:include - Solo insertar el contenido (deprecated)

```html
<!-- No recomendado, usar th:insert en su lugar -->
<div th:include="~{fragments/header :: navbar}"></div>
```

### Sintaxis de fragmentos

```html
<!-- Sintaxis completa -->
th:replace="~{ruta/archivo :: nombreFragmento}"
<!--           ↑ ruta       ↑ nombre definido con th:fragment -->

<!-- Sintaxis simplificada (sin ~{}) -->
th:replace="fragments/header :: navbar"

<!-- Incluir todo el archivo -->
th:replace="fragments/header"
```

### Pasar parámetros a fragmentos

**Definir fragmento con parámetros:**

```html
<!-- fragments/card.html -->
<div th:fragment="card(titulo, contenido)" class="card">
    <div class="card-header">
        <h3 th:text="${titulo}">Título</h3>
    </div>
    <div class="card-body">
        <p th:text="${contenido}">Contenido</p>
    </div>
</div>
```

**Usar fragmento con parámetros:**

```html
<!-- Pasar valores literales -->
<div th:replace="~{fragments/card :: card('Mi Título', 'Mi Contenido')}"></div>

<!-- Pasar variables del modelo -->
<div th:replace="~{fragments/card :: card(${servicio.nombre}, ${servicio.descripcion})}"></div>
```

### Layout completo del proyecto

**Estructura típica:**
```
templates/
  ├── admin/
  │   ├── fragments/
  │   │   ├── header.html    (navbar)
  │   │   ├── sidebar.html   (menú lateral)
  │   │   └── footer.html    (pie de página)
  │   └── dashboard.html     (página que usa los fragmentos)
```

**fragments/header.html:**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <nav th:fragment="navbar" class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <a class="navbar-brand ps-3" th:href="@{/admin/dashboard}">SYC Admin</a>
        <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle">
            <i class="fas fa-bars"></i>
        </button>
        <ul class="navbar-nav ms-auto me-3">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                    <i class="fas fa-user fa-fw"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li>
                        <form th:action="@{/logout}" method="post">
                            <button type="submit" class="dropdown-item">Cerrar sesión</button>
                        </form>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</body>
</html>
```

**fragments/sidebar.html:**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <nav th:fragment="sidebar" class="sb-sidenav accordion sb-sidenav-dark">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Principal</div>
                <a class="nav-link" th:href="@{/admin/dashboard}">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>

                <div class="sb-sidenav-menu-heading">Gestión</div>
                <a class="nav-link" th:href="@{/admin/servicios}">
                    <div class="sb-nav-link-icon"><i class="fas fa-hammer"></i></div>
                    Servicios
                </a>
                <a class="nav-link" th:href="@{/admin/proyectos}">
                    <div class="sb-nav-link-icon"><i class="fas fa-briefcase"></i></div>
                    Proyectos
                </a>
            </div>
        </div>
    </nav>
</body>
</html>
```

**dashboard.html (usa los fragmentos):**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title th:text="${pageTitle}">Dashboard</title>
    <link rel="stylesheet" th:href="@{/styles.css}">
</head>
<body class="sb-nav-fixed">

    <!-- Incluir navbar -->
    <div th:replace="~{admin/fragments/header :: navbar}"></div>

    <div id="layoutSidenav">
        <!-- Incluir sidebar -->
        <div id="layoutSidenav_nav">
            <div th:replace="~{admin/fragments/sidebar :: sidebar}"></div>
        </div>

        <!-- Contenido principal -->
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Dashboard</h1>

                    <!-- Contenido específico de esta página -->
                    <p>Estadísticas y gráficos...</p>
                </div>
            </main>

            <!-- Incluir footer -->
            <footer th:replace="~{admin/fragments/footer :: footer}"></footer>
        </div>
    </div>

    <script th:src="@{/scripts.js}"></script>
</body>
</html>
```

**Ventajas de usar fragmentos:**
- ✅ No repetir código (DRY - Don't Repeat Yourself)
- ✅ Cambiar el navbar en un solo archivo actualiza todas las páginas
- ✅ Mantenimiento más fácil
- ✅ Código más organizado

---

## Ejemplos del proyecto

### 1. Formulario con Thymeleaf (Crear servicio)

**AdminController.java:**
```java
@GetMapping("/servicios")
public String servicios(Model model) {
    List<Servicio> servicios = servicioRepository.findAll();
    model.addAttribute("servicios", servicios);
    model.addAttribute("servicioNuevo", new Servicio());  // Objeto vacío para el formulario
    return "admin/servicios";
}

@PostMapping("/servicio/guardar")
public String guardarServicio(@ModelAttribute Servicio servicio) {
    servicioRepository.save(servicio);
    return "redirect:/admin/servicios";
}
```

**servicios.html:**
```html
<form th:action="@{/admin/servicio/guardar}"
      th:object="${servicioNuevo}"
      method="POST">
<!--  ↑ URL de envío          ↑ Objeto del modelo -->

    <!-- Campo de texto -->
    <div class="mb-3">
        <label for="nombre" class="form-label">Nombre del servicio</label>
        <input type="text"
               class="form-control"
               id="nombre"
               th:field="*{nombre}"
               placeholder="Ej: Cocina integral"
               required>
    </div>

    <!-- Campo de textarea -->
    <div class="mb-3">
        <label for="descripcion" class="form-label">Descripción</label>
        <textarea class="form-control"
                  id="descripcion"
                  th:field="*{descripcion}"
                  rows="3"
                  required></textarea>
    </div>

    <!-- Campo numérico -->
    <div class="mb-3">
        <label for="precio" class="form-label">Precio</label>
        <input type="number"
               class="form-control"
               id="precio"
               th:field="*{precio}"
               min="0"
               required>
    </div>

    <!-- Botón de envío -->
    <button type="submit" class="btn btn-primary">
        <i class="fas fa-save me-2"></i>Guardar servicio
    </button>
</form>
```

**¿Qué hace `th:field="*{nombre}"`?**

Es equivalente a escribir:
```html
<input type="text"
       id="nombre"
       name="nombre"
       value="${servicioNuevo.nombre}">
```

Ventajas de `th:field`:
- ✅ Genera automáticamente `id`, `name` y `value`
- ✅ Pre-llena el formulario al editar
- ✅ Mantiene valores si hay error de validación

### 2. Tabla con iteración (Listar servicios)

```html
<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Fecha</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <!-- Si la lista está vacía -->
        <tr th:if="${servicios.isEmpty()}">
            <td colspan="6" class="text-center text-muted">
                <i class="fas fa-inbox fa-2x mb-2"></i>
                <p>No hay servicios registrados</p>
            </td>
        </tr>

        <!-- Iterar servicios -->
        <tr th:each="servicio : ${servicios}">
            <td th:text="${servicio.id}">1</td>
            <td th:text="${servicio.nombre}">Cocina integral</td>
            <td th:text="${servicio.descripcion}">Descripción del servicio</td>
            <td th:text="${'$' + #numbers.formatDecimal(servicio.precio, 0, 'COMMA', 0, 'POINT')}">
                $5,000,000
            </td>
            <td th:text="${#temporals.format(servicio.fechaCreacion, 'dd/MM/yyyy HH:mm')}">
                01/01/2024 10:30
            </td>
            <td>
                <a th:href="@{/admin/servicio/editar(id=${servicio.id})}"
                   class="btn btn-sm btn-primary">
                    <i class="fas fa-edit"></i> Editar
                </a>
                <form th:action="@{/admin/servicio/eliminar(id=${servicio.id})}"
                      method="POST"
                      style="display: inline;"
                      onsubmit="return confirm('¿Está seguro de eliminar este servicio?');">
                    <button type="submit" class="btn btn-sm btn-danger">
                        <i class="fas fa-trash"></i> Eliminar
                    </button>
                </form>
            </td>
        </tr>
    </tbody>
</table>
```

**Utility objects de Thymeleaf:**
- `#numbers`: Formatear números
- `#temporals`: Formatear fechas (Java 8+ Date/Time API)
- `#strings`: Manipular strings
- `#lists`: Operaciones con listas
- `#maps`: Operaciones con mapas

### 3. Buscador con mantenimiento de estado

```html
<!-- Formulario de búsqueda -->
<form method="GET" th:action="@{/admin/servicios}" class="mb-3">
    <div class="row g-2">
        <div class="col-md-8">
            <div class="input-group">
                <span class="input-group-text">
                    <i class="fas fa-search"></i>
                </span>
                <input type="text"
                       class="form-control"
                       name="busqueda"
                       th:value="${busqueda}"
                       placeholder="Buscar por ID o nombre del servicio..."
                       autofocus>
                <!--                ↑ Mantiene el término de búsqueda -->
            </div>
        </div>
        <div class="col-md-4">
            <button type="submit" class="btn btn-primary me-2">
                <i class="fas fa-search me-1"></i>Buscar
            </button>
            <a th:href="@{/admin/servicios}" class="btn btn-secondary">
                <i class="fas fa-times me-1"></i>Limpiar
            </a>
        </div>
    </div>
</form>

<!-- Mensaje de resultados -->
<div th:if="${busqueda != null}" class="alert alert-info alert-dismissible fade show">
    <i class="fas fa-info-circle me-2"></i>
    Mostrando resultados para: <strong th:text="${busqueda}">término</strong>
    (<span th:text="${servicios.size()}">0</span> encontrado(s))
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
</div>
```

### 4. Tarjetas de estadísticas (Dashboard)

```html
<div class="row">
    <!-- Tarjeta de servicios -->
    <div class="col-xl-3 col-md-6">
        <div class="card bg-primary text-white mb-4">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <div class="small">Servicios</div>
                        <div class="fs-2 fw-bold" th:text="${serviciosCount}">0</div>
                    </div>
                    <div class="fs-1"><i class="fas fa-hammer"></i></div>
                </div>
            </div>
            <div class="card-footer d-flex align-items-center justify-content-between">
                <a class="small text-white stretched-link" th:href="@{/admin/servicios}">
                    Ver detalles
                </a>
                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
            </div>
        </div>
    </div>

    <!-- Tarjeta de proyectos -->
    <div class="col-xl-3 col-md-6">
        <div class="card bg-success text-white mb-4">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <div class="small">Proyectos</div>
                        <div class="fs-2 fw-bold" th:text="${proyectosCount}">0</div>
                    </div>
                    <div class="fs-1"><i class="fas fa-briefcase"></i></div>
                </div>
            </div>
            <div class="card-footer d-flex align-items-center justify-content-between">
                <a class="small text-white stretched-link" th:href="@{/admin/proyectos}">
                    Ver detalles
                </a>
                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
            </div>
        </div>
    </div>
</div>
```

### 5. Login con manejo de errores

```html
<form th:action="@{/login}" method="POST">
    <!-- Mensaje de error -->
    <div th:if="${param.error}" class="alert alert-danger">
        <i class="fas fa-exclamation-circle me-2"></i>
        Usuario o contraseña incorrectos
    </div>

    <!-- Mensaje de logout -->
    <div th:if="${param.logout}" class="alert alert-success">
        <i class="fas fa-check-circle me-2"></i>
        Ha cerrado sesión correctamente
    </div>

    <!-- Email -->
    <div class="form-floating mb-3">
        <input class="form-control"
               id="email"
               name="email"
               type="email"
               placeholder="nombre@ejemplo.com"
               required
               autofocus>
        <label for="email">Email</label>
    </div>

    <!-- Contraseña -->
    <div class="form-floating mb-3">
        <input class="form-control"
               id="password"
               name="password"
               type="password"
               placeholder="Contraseña"
               required>
        <label for="password">Contraseña</label>
    </div>

    <!-- Botón de envío -->
    <div class="d-grid">
        <button class="btn btn-primary btn-lg" type="submit">
            <i class="fas fa-sign-in-alt me-2"></i>Iniciar sesión
        </button>
    </div>
</form>
```

**Explicación de `${param.error}`:**
- Spring Security agrega el parámetro `?error` a la URL cuando falla el login
- `${param.error}` detecta este parámetro y muestra el mensaje de error
- Similar con `${param.logout}` cuando se cierra sesión

---

## 🎯 Conceptos clave para la sustentación

### 1. ¿Qué es HTML?
- **Respuesta**: Es el lenguaje de marcado que estructura el contenido de las páginas web usando etiquetas.

### 2. ¿Qué es Thymeleaf y para qué sirve?
- **Respuesta**: Es un motor de plantillas para Java que permite generar HTML dinámico insertando datos del servidor en las plantillas HTML.

### 3. ¿Cómo pasa datos el controlador a la vista?
- **Respuesta**: Usando `model.addAttribute("nombre", valor)` en el controlador, y luego accediendo con `${nombre}` en Thymeleaf.

### 4. ¿Qué hace th:text?
- **Respuesta**: Inserta texto en un elemento HTML de forma segura (escapando caracteres especiales para prevenir XSS).

### 5. ¿Qué hace th:each?
- **Respuesta**: Itera sobre una colección (lista) para generar múltiples elementos HTML dinámicamente.

### 6. ¿Qué hace th:field?
- **Respuesta**: Genera automáticamente los atributos `id`, `name` y `value` de un input basándose en una propiedad del objeto del formulario.

### 7. ¿Para qué sirven los fragmentos?
- **Respuesta**: Para reutilizar código HTML (como navbar, footer) en múltiples páginas sin repetirlo.

### 8. ¿Cuál es la diferencia entre th:insert y th:replace?
- **Respuesta**: `th:insert` inserta el fragmento dentro del elemento host, mientras que `th:replace` reemplaza completamente el elemento host.

### 9. ¿Qué hace la expresión @{...}?
- **Respuesta**: Genera URLs manejando automáticamente el context path de la aplicación.

### 10. ¿Cómo manejar formularios en Thymeleaf?
- **Respuesta**: Usando `th:action` para la URL, `th:object` para el objeto del modelo, y `th:field` para cada campo.

---

## 📝 Glosario de términos

- **Tag/Etiqueta**: Marcador HTML que define un elemento (`<p>`, `<div>`, `<h1>`)
- **Atributo**: Propiedad de una etiqueta (`href`, `src`, `class`)
- **Elemento semántico**: Etiqueta HTML5 que describe su propósito (`<header>`, `<nav>`, `<footer>`)
- **Thymeleaf**: Motor de plantillas para Java integrado con Spring Boot
- **Natural templating**: Plantillas que son HTML válido y pueden abrirse directamente en navegador
- **Model**: Objeto que transfiere datos del controlador a la vista
- **th:text**: Atributo Thymeleaf para insertar texto
- **th:each**: Atributo Thymeleaf para iteraciones
- **th:if**: Atributo Thymeleaf para condicionales
- **th:object**: Define el objeto del formulario
- **th:field**: Genera automáticamente id, name y value de un input
- **Fragmento**: Porción de HTML reutilizable
- **Layout**: Estructura común de las páginas (navbar, sidebar, footer)
- **Escape**: Convertir caracteres especiales HTML (&lt; &gt;) para seguridad
- **XSS**: Cross-Site Scripting, ataque de inyección de código malicioso

---

## ✅ Checklist de conocimientos HTML y Thymeleaf

Para la sustentación, debes poder explicar:

- [ ] Estructura básica de un documento HTML (DOCTYPE, html, head, body)
- [ ] Diferencia entre elementos de bloque y en línea
- [ ] Cómo crear formularios HTML con diferentes tipos de inputs
- [ ] Qué son los elementos semánticos HTML5 y por qué usarlos
- [ ] Cómo funciona la comunicación entre controlador y vista
- [ ] Sintaxis básica de Thymeleaf (th:text, th:if, th:each)
- [ ] Cómo iterar sobre listas con th:each
- [ ] Cómo usar condicionales con th:if y th:unless
- [ ] Cómo vincular formularios con th:object y th:field
- [ ] Cómo generar URLs con @{...}
- [ ] Qué son los fragmentos y cómo reutilizarlos
- [ ] Diferencia entre th:insert y th:replace
- [ ] Cómo pasar parámetros en URLs de Thymeleaf
- [ ] Utility objects de Thymeleaf (#numbers, #temporals, etc.)
- [ ] Cómo manejar mensajes de error y éxito en formularios

---

**📚 Esta guía cubre todos los conceptos de HTML y Thymeleaf usados en el proyecto Carpentry SYC para tu sustentación final.**
