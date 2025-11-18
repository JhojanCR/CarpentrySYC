# GUÍA EDUCATIVA: ARQUITECTURA FRONTEND DEL PROYECTO CARPENTRY SYC

## 📚 Tabla de Contenidos
1. [Visión general de la arquitectura](#visión-general-de-la-arquitectura)
2. [Stack tecnológico frontend](#stack-tecnológico-frontend)
3. [Estructura de archivos](#estructura-de-archivos)
4. [Flujo de datos: Backend → Frontend](#flujo-de-datos-backend--frontend)
5. [Integración de tecnologías](#integración-de-tecnologías)
6. [Patrones de diseño utilizados](#patrones-de-diseño-utilizados)
7. [Sistema de routing](#sistema-de-routing)
8. [Gestión de recursos estáticos](#gestión-de-recursos-estáticos)
9. [Seguridad frontend](#seguridad-frontend)
10. [Responsive design strategy](#responsive-design-strategy)

---

## Visión general de la arquitectura

El proyecto Carpentry SYC sigue una **arquitectura MVC (Model-View-Controller)** con Spring Boot en el backend y Thymeleaf para renderizar las vistas.

### Diagrama de arquitectura

```
┌─────────────────────────────────────────────────────────────┐
│                        NAVEGADOR                             │
│  ┌───────────┐  ┌───────────┐  ┌────────────┐              │
│  │   HTML    │  │    CSS    │  │ JavaScript │              │
│  │(Thymeleaf)│  │ (Bootstrap)│  │  (Vanilla) │              │
│  └─────▲─────┘  └─────▲─────┘  └──────▲─────┘              │
└────────┼──────────────┼────────────────┼────────────────────┘
         │              │                │
         │              │                │
┌────────┼──────────────┼────────────────┼────────────────────┐
│        │     SPRING BOOT SERVER        │                     │
│  ┌─────▼──────────────▼────────────────▼─────┐              │
│  │          THYMELEAF ENGINE                  │              │
│  │  (Procesa plantillas + datos del modelo)  │              │
│  └─────▲───────────────────────────────▲──────┘              │
│        │                               │                     │
│  ┌─────┴─────┐                  ┌──────┴──────┐             │
│  │CONTROLLERS│◄────────────────►│   SERVICES  │             │
│  │(AdminCtrl)│                  │(ServicioSvc)│             │
│  └───────────┘                  └──────▲──────┘             │
│                                        │                     │
│                                 ┌──────┴──────┐             │
│                                 │ REPOSITORIES │             │
│                                 │   (JPA)      │             │
│                                 └──────▲──────┘             │
└────────────────────────────────────────┼────────────────────┘
                                         │
┌────────────────────────────────────────┼────────────────────┐
│                        ┌───────────────┴──────┐              │
│                        │    MySQL DATABASE    │              │
│                        └──────────────────────┘              │
└─────────────────────────────────────────────────────────────┘
```

### Flujo de una petición completa

```
1. Usuario solicita /admin/servicios
   ↓
2. Spring Security verifica autenticación
   ↓
3. AdminController.servicios() procesa la petición
   ↓
4. Consulta datos desde ServicioRepository
   ↓
5. Agrega datos al Model (model.addAttribute())
   ↓
6. Retorna nombre de la vista "admin/servicios"
   ↓
7. Thymeleaf procesa servicios.html + datos del Model
   ↓
8. Genera HTML final con datos dinámicos
   ↓
9. Navegador recibe HTML + CSS + JS
   ↓
10. Navegador renderiza la página
    ↓
11. JavaScript agrega interactividad (eventos, validaciones)
```

---

## Stack tecnológico frontend

### Tecnologías principales

| Tecnología | Propósito | Versión |
|------------|-----------|---------|
| **HTML5** | Estructura del contenido | HTML5 |
| **CSS3** | Estilos y diseño visual | CSS3 |
| **JavaScript** | Interactividad y comportamiento | ES6+ |
| **Thymeleaf** | Motor de plantillas (server-side) | 3.1.x |
| **Bootstrap** | Framework CSS responsive | 5.2.3 |
| **Font Awesome** | Librería de iconos | 6.4.0 |
| **Chart.js** | Gráficos y visualizaciones | Latest |
| **Google Fonts** | Tipografías personalizadas | - |

### ¿Por qué estas tecnologías?

#### Thymeleaf
✅ **Ventajas:**
- Natural templating (HTML válido)
- Integración perfecta con Spring Boot
- Server-side rendering (SEO friendly)
- Seguridad (escape automático de HTML)

❌ **Desventajas:**
- Requiere recarga de página para cambios
- Menos interactividad que frameworks SPA (React, Vue)

#### Bootstrap
✅ **Ventajas:**
- Sistema de grid responsive out-of-the-box
- Componentes pre-diseñados (botones, forms, cards)
- Ahorra tiempo de desarrollo
- Diseño profesional consistente

❌ **Desventajas:**
- Sitios pueden verse similares si no se personaliza
- Puede ser "pesado" si solo se usan pocos componentes

#### JavaScript Vanilla (sin framework)
✅ **Ventajas:**
- Ligero y rápido (no hay overhead de framework)
- Control total sobre el código
- No requiere build process

❌ **Desventajas:**
- Más código manual para funcionalidades complejas
- No hay reactividad automática como React/Vue

---

## Estructura de archivos

### Organización del proyecto

```
CarpentrySYC/
│
├── src/main/
│   ├── java/com/syc/carpentry/
│   │   ├── controller/           # Controladores (lógica de peticiones)
│   │   │   ├── AdminController.java
│   │   │   ├── AuthController.java
│   │   │   └── PublicController.java
│   │   │
│   │   ├── service/              # Servicios (lógica de negocio)
│   │   │   ├── ServicioService.java
│   │   │   └── ProyectoService.java
│   │   │
│   │   ├── repository/           # Repositorios (acceso a datos)
│   │   │   ├── ServicioRepository.java
│   │   │   └── ProyectoRepository.java
│   │   │
│   │   └── model/                # Modelos (entidades JPA)
│   │       ├── Servicio.java
│   │       └── Proyecto.java
│   │
│   └── resources/
│       ├── templates/            # Plantillas Thymeleaf (HTML)
│       │   ├── index.html            # Página pública principal
│       │   │
│       │   ├── admin/                # Admin dashboard
│       │   │   ├── dashboard.html
│       │   │   ├── servicios.html
│       │   │   ├── proyectos.html
│       │   │   ├── contactos.html
│       │   │   ├── usuarios.html
│       │   │   │
│       │   │   └── fragments/        # Componentes reutilizables
│       │   │       ├── header.html   (navbar)
│       │   │       ├── sidebar.html  (menú lateral)
│       │   │       └── footer.html   (pie de página)
│       │   │
│       │   ├── auth/                 # Autenticación
│       │   │   ├── login.html
│       │   │   ├── register.html
│       │   │   ├── forgot-password.html
│       │   │   └── reset-password.html
│       │   │
│       │   ├── public/               # Páginas públicas
│       │   │   ├── servicios.html
│       │   │   ├── nosotros.html
│       │   │   └── contacto.html
│       │   │
│       │   └── errors/               # Páginas de error
│       │       ├── 404.html
│       │       ├── 401.html
│       │       └── 500.html
│       │
│       └── static/               # Recursos estáticos
│           ├── styles.css            # CSS principal (Bootstrap + SB Admin)
│           ├── scripts.js            # JavaScript principal
│           ├── script.js             # JavaScript adicional
│           │
│           └── assets/               # Imágenes, fuentes, etc.
│               └── demo/
│                   ├── chart-bar-demo.js
│                   ├── chart-area-demo.js
│                   └── chart-pie-demo.js
│
├── DOCUMENTACION_PROYECTO.md    # Documentación general
├── GUIA_CONTROLADORES.md        # Guía de controladores
├── GUIA_SPRING_SECURITY.md      # Guía de seguridad
├── GUIA_CSS.md                  # Guía de CSS
├── GUIA_HTML_THYMELEAF.md       # Guía de HTML y Thymeleaf
├── GUIA_JAVASCRIPT.md           # Guía de JavaScript
└── GUIA_ARQUITECTURA_FRONTEND.md # Esta guía
```

### Convenciones de nomenclatura

#### Templates (HTML)
```
- Minúsculas con guiones: servicios.html, forgot-password.html
- Organizados por funcionalidad en carpetas
- Fragmentos en carpeta "fragments/"
```

#### CSS
```
- Clases con guiones: .navbar-brand, .btn-primary
- IDs en camelCase: #sidebarToggle, #layoutSidenav
- Evitar estilos inline excepto en casos específicos
```

#### JavaScript
```
- Variables y funciones en camelCase: let servicioId, function guardarServicio()
- Constantes en UPPER_SNAKE_CASE: const API_URL = '...'
- Clases en PascalCase: class ServicioManager
```

---

## Flujo de datos: Backend → Frontend

### Paso a paso de una petición

#### 1. Controller prepara datos

```java
// AdminController.java
@GetMapping("/admin/servicios")
public String servicios(Model model) {
    // Obtener datos del repositorio
    List<Servicio> servicios = servicioRepository.findAll();

    // Agregar datos al modelo
    model.addAttribute("servicios", servicios);
    model.addAttribute("servicioNuevo", new Servicio());
    model.addAttribute("pageTitle", "Servicios - Admin SYC");

    // Retornar nombre de la vista
    return "admin/servicios";
    // Spring busca: templates/admin/servicios.html
}
```

#### 2. Thymeleaf procesa la plantilla

```html
<!-- servicios.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:text="${pageTitle}">Servicios</title>
    <link rel="stylesheet" th:href="@{/styles.css}">
</head>
<body>
    <h1 th:text="${pageTitle}">Servicios</h1>

    <!-- Iterar sobre la lista de servicios -->
    <table>
        <tr th:each="servicio : ${servicios}">
            <td th:text="${servicio.id}">1</td>
            <td th:text="${servicio.nombre}">Cocina</td>
        </tr>
    </table>
</body>
</html>
```

#### 3. HTML final generado

```html
<!DOCTYPE html>
<html>
<head>
    <title>Servicios - Admin SYC</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <h1>Servicios - Admin SYC</h1>

    <table>
        <tr>
            <td>1</td>
            <td>Cocina integral</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Muebles a medida</td>
        </tr>
    </table>
</body>
</html>
```

#### 4. JavaScript agrega interactividad

```javascript
// scripts.js
document.addEventListener('DOMContentLoaded', function() {
    // Agregar confirmación a botones de eliminar
    document.querySelectorAll('.btn-eliminar').forEach(boton => {
        boton.addEventListener('click', function(e) {
            if (!confirm('¿Está seguro de eliminar?')) {
                e.preventDefault();
            }
        });
    });
});
```

### Comunicación bidireccional

```
┌────────────┐                        ┌────────────┐
│  FRONTEND  │                        │  BACKEND   │
│ (Navegador)│                        │  (Spring)  │
└──────┬─────┘                        └─────┬──────┘
       │                                    │
       │  1. GET /admin/servicios           │
       │───────────────────────────────────►│
       │                                    │
       │           2. Procesa petición      │
       │              (Controller)          │
       │                                    │
       │  3. Consulta BD (Repository)       │
       │                                    │
       │  4. Genera HTML (Thymeleaf)        │
       │◄───────────────────────────────────│
       │                                    │
       │  5. Renderiza HTML (Browser)       │
       │                                    │
       │  6. POST /servicio/guardar         │
       │     (datos del formulario)         │
       │───────────────────────────────────►│
       │                                    │
       │           7. Guarda en BD          │
       │              (Repository)          │
       │                                    │
       │  8. Redirect /admin/servicios      │
       │◄───────────────────────────────────│
       │                                    │
```

---

## Integración de tecnologías

### Cómo trabajan juntas

#### HTML + Thymeleaf
```html
<!-- HTML proporciona la estructura -->
<div class="card">
    <!-- Thymeleaf inyecta datos dinámicos -->
    <h3 th:text="${servicio.nombre}">Nombre del servicio</h3>
    <p th:text="${servicio.descripcion}">Descripción...</p>
</div>
```

#### CSS + Bootstrap
```html
<!-- Bootstrap proporciona clases predefinidas -->
<button class="btn btn-primary">Guardar</button>

<!-- CSS personalizado agrega estilos específicos -->
<style>
.btn-primary {
    background: linear-gradient(135deg, #FF6F00, #FFA726);
}
</style>
```

#### JavaScript + Thymeleaf
```html
<!-- Thymeleaf genera HTML con datos -->
<button th:attr="data-id=${servicio.id}" class="btn-editar">
    Editar
</button>

<!-- JavaScript maneja la interacción -->
<script>
document.querySelectorAll('.btn-editar').forEach(btn => {
    btn.addEventListener('click', function() {
        let id = this.getAttribute('data-id');
        editarServicio(id);
    });
});
</script>
```

### Ejemplo completo: Tarjeta de servicio

```html
<!-- 1. HTML estructura el contenido -->
<div class="service-card">

    <!-- 2. Thymeleaf inyecta datos del backend -->
    <h3 th:text="${servicio.nombre}">Cocina integral</h3>
    <p th:text="${servicio.descripcion}">Diseño y fabricación...</p>

    <!-- 3. Bootstrap estiliza los botones -->
    <button class="btn btn-primary"
            th:attr="data-id=${servicio.id}"
            onclick="verDetalles(this)">
        Ver más
    </button>
</div>

<!-- 4. CSS personaliza el diseño -->
<style>
.service-card {
    background: white;
    border-radius: 15px;
    padding: 30px;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    transition: transform 0.3s ease;
}

.service-card:hover {
    transform: translateY(-10px);
}
</style>

<!-- 5. JavaScript agrega interactividad -->
<script>
function verDetalles(btn) {
    let id = btn.getAttribute('data-id');
    // Mostrar modal con detalles
    fetch('/api/servicio/' + id)
        .then(response => response.json())
        .then(data => mostrarModal(data));
}
</script>
```

---

## Patrones de diseño utilizados

### 1. Template Fragment Pattern

**Problema:** Código HTML repetido en múltiples páginas (navbar, footer)

**Solución:** Fragmentos reutilizables con Thymeleaf

```html
<!-- fragments/header.html -->
<nav th:fragment="navbar" class="navbar">
    <a href="/">Logo</a>
    <ul>
        <li><a href="/servicios">Servicios</a></li>
        <li><a href="/contacto">Contacto</a></li>
    </ul>
</nav>

<!-- dashboard.html -->
<html>
<body>
    <!-- Incluir fragmento -->
    <div th:replace="~{fragments/header :: navbar}"></div>

    <!-- Contenido específico de esta página -->
    <main>...</main>
</body>
</html>
```

**Beneficios:**
- ✅ No repetir código (DRY - Don't Repeat Yourself)
- ✅ Cambiar navbar en un solo lugar actualiza todas las páginas
- ✅ Mantenimiento más fácil

### 2. Progressive Enhancement

**Concepto:** La página funciona sin JavaScript, pero es mejor con él.

```html
<!-- Formulario funcional sin JavaScript -->
<form action="/contacto/enviar" method="POST">
    <input type="email" name="email" required>
    <button type="submit">Enviar</button>
</form>

<!-- JavaScript agrega validación en tiempo real -->
<script>
document.querySelector('form').addEventListener('submit', function(e) {
    e.preventDefault();
    // Validación avanzada
    if (validarFormulario()) {
        this.submit();
    }
});
</script>
```

### 3. CSS Variables Pattern

**Problema:** Colores y valores duplicados en todo el CSS

**Solución:** Variables CSS centralizadas

```css
:root {
    --color-primary: #FF6F00;
    --color-secondary: #5D4037;
    --spacing-unit: 8px;
}

.btn-primary {
    background-color: var(--color-primary);
    padding: var(--spacing-unit);
}

.card {
    border-color: var(--color-secondary);
    margin: calc(var(--spacing-unit) * 2);
}
```

### 4. Event Delegation Pattern

**Problema:** Agregar event listeners a muchos elementos dinámicos

**Solución:** Listener único en el contenedor padre

```javascript
// ❌ Mal: listener en cada botón (no funciona con elementos dinámicos)
document.querySelectorAll('.btn-eliminar').forEach(btn => {
    btn.addEventListener('click', eliminar);
});

// ✅ Bien: listener en el contenedor
document.getElementById('lista').addEventListener('click', function(e) {
    if (e.target.classList.contains('btn-eliminar')) {
        eliminar(e);
    }
});
```

### 5. Module Pattern (JavaScript)

**Problema:** Contaminar el scope global con variables

**Solución:** Encapsular código en funciones autoejectuables

```javascript
// Función autoejecutable (IIFE)
(function() {
    // Variables privadas
    let contadorPrivado = 0;

    // Funciones privadas
    function incrementar() {
        contadorPrivado++;
    }

    // Exponer API pública
    window.MiModulo = {
        obtenerContador: function() {
            return contadorPrivado;
        },
        incrementar: incrementar
    };
})();

// Uso
MiModulo.incrementar();
console.log(MiModulo.obtenerContador());  // 1
```

---

## Sistema de routing

### Spring MVC Routing

#### Controladores definen las rutas

```java
@Controller
public class AdminController {

    // Ruta: /admin/dashboard
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        return "admin/dashboard";
    }

    // Ruta: /admin/servicios (con parámetro opcional)
    @GetMapping("/admin/servicios")
    public String servicios(
        @RequestParam(required = false) String busqueda,
        Model model
    ) {
        return "admin/servicios";
    }

    // Ruta: /admin/servicio/editar?id=5
    @GetMapping("/admin/servicio/editar")
    public String editarServicio(@RequestParam Long id, Model model) {
        return "admin/servicios";
    }

    // Ruta POST: /admin/servicio/guardar
    @PostMapping("/admin/servicio/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio) {
        servicioRepository.save(servicio);
        return "redirect:/admin/servicios";
    }
}
```

#### Thymeleaf genera URLs

```html
<!-- URL simple -->
<a th:href="@{/admin/dashboard}">Dashboard</a>
<!-- Renderiza: <a href="/admin/dashboard">Dashboard</a> -->

<!-- URL con parámetros -->
<a th:href="@{/admin/servicio/editar(id=${servicio.id})}">Editar</a>
<!-- Renderiza: <a href="/admin/servicio/editar?id=5">Editar</a> -->

<!-- URL con múltiples parámetros -->
<a th:href="@{/buscar(q=${query},page=${pageNum})}">Buscar</a>
<!-- Renderiza: <a href="/buscar?q=cocina&page=1">Buscar</a> -->

<!-- Formulario con action -->
<form th:action="@{/admin/servicio/guardar}" method="POST">
    ...
</form>
```

### Mapeo de URLs del proyecto

```
PÚBLICAS (sin autenticación):
  GET  /                          → index.html
  GET  /servicios                 → public/servicios.html
  GET  /nosotros                  → public/nosotros.html
  GET  /contacto                  → public/contacto.html
  POST /contacto/enviar           → Guardar mensaje
  GET  /login                     → auth/login.html
  POST /login                     → Autenticar usuario
  GET  /register                  → auth/register.html
  POST /register                  → Crear usuario
  GET  /forgot-password           → auth/forgot-password.html
  POST /forgot-password           → Enviar email recuperación

ADMIN (requiere autenticación):
  GET  /admin/dashboard           → admin/dashboard.html
  GET  /admin/servicios           → admin/servicios.html
  POST /admin/servicio/guardar    → Guardar servicio
  POST /admin/servicio/eliminar   → Eliminar servicio
  GET  /admin/proyectos           → admin/proyectos.html
  POST /admin/proyecto/guardar    → Guardar proyecto
  GET  /admin/contactos           → admin/contactos.html
  GET  /admin/usuarios            → admin/usuarios.html
  POST /admin/usuario/guardar     → Guardar usuario
  GET  /admin/reportes/pdf        → Generar PDF
  GET  /admin/reportes/excel      → Generar Excel
```

---

## Gestión de recursos estáticos

### Configuración en Spring Boot

```java
// Spring Boot automáticamente sirve archivos de:
src/main/resources/static/
├── styles.css        → /styles.css
├── scripts.js        → /scripts.js
└── assets/
    └── logo.png      → /assets/logo.png
```

### Referenciar recursos en Thymeleaf

```html
<!-- CSS -->
<link rel="stylesheet" th:href="@{/styles.css}">

<!-- JavaScript -->
<script th:src="@{/scripts.js}"></script>

<!-- Imágenes -->
<img th:src="@{/assets/logo.png}" alt="Logo">

<!-- Favicon -->
<link rel="icon" type="image/x-icon" th:href="@{/favicon.ico}">
```

### CDN vs local

#### CDN (Content Delivery Network)
```html
<!-- Bootstrap desde CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome desde CDN -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
```

**Ventajas CDN:**
- ✅ Rápido (servidores distribuidos globalmente)
- ✅ Caché del navegador compartida entre sitios
- ✅ No consume recursos del servidor

**Desventajas CDN:**
- ❌ Requiere conexión a internet
- ❌ Dependencia de servicio externo

#### Archivos locales
```html
<!-- Bootstrap local -->
<link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">
```

**Ventajas local:**
- ✅ Funciona offline
- ✅ Control total sobre la versión
- ✅ No depende de terceros

**Desventajas local:**
- ❌ Aumenta tamaño del proyecto
- ❌ Consume ancho de banda del servidor

### Optimización de recursos

#### Minificación
```
styles.css      (150 KB)  →  styles.min.css      (50 KB)
scripts.js      (80 KB)   →  scripts.min.js      (25 KB)
```

#### Compresión de imágenes
```
imagen.jpg      (2 MB)    →  imagen-optimized.jpg  (200 KB)
```

#### Lazy loading de imágenes
```html
<img src="placeholder.jpg" data-src="imagen-grande.jpg" class="lazy">

<script>
// Cargar imágenes solo cuando sean visibles
let lazyImages = document.querySelectorAll('.lazy');
let observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            let img = entry.target;
            img.src = img.getAttribute('data-src');
            img.classList.remove('lazy');
            observer.unobserve(img);
        }
    });
});

lazyImages.forEach(img => observer.observe(img));
</script>
```

---

## Seguridad frontend

### 1. Protección XSS (Cross-Site Scripting)

**Problema:** Inyección de código malicioso

```html
<!-- ❌ Vulnerable: th:utext no escapa HTML -->
<div th:utext="${comentarioUsuario}"></div>
<!-- Si comentarioUsuario = "<script>alert('Hack!')</script>", se ejecuta -->

<!-- ✅ Seguro: th:text escapa HTML automáticamente -->
<div th:text="${comentarioUsuario}"></div>
<!-- Renderiza: &lt;script&gt;alert('Hack!')&lt;/script&gt; -->
```

**JavaScript también debe escapar:**
```javascript
// ❌ Vulnerable
elemento.innerHTML = contenidoUsuario;

// ✅ Seguro
elemento.textContent = contenidoUsuario;
```

### 2. CSRF (Cross-Site Request Forgery)

Spring Security incluye protección CSRF automáticamente:

```html
<!-- Token CSRF automático en formularios Thymeleaf -->
<form th:action="@{/admin/servicio/guardar}" method="POST">
    <!-- Thymeleaf agrega automáticamente: -->
    <!-- <input type="hidden" name="_csrf" value="token-aleatorio"> -->

    <input type="text" name="nombre">
    <button type="submit">Guardar</button>
</form>
```

### 3. Validación de entrada

**Backend (Java):**
```java
@PostMapping("/servicio/guardar")
public String guardar(@Valid @ModelAttribute Servicio servicio) {
    // @Valid valida anotaciones de la entidad
    servicioRepository.save(servicio);
    return "redirect:/admin/servicios";
}
```

**Frontend (JavaScript):**
```javascript
formulario.addEventListener('submit', function(e) {
    let nombre = document.getElementById('nombre').value;

    // Validar longitud
    if (nombre.length < 3) {
        e.preventDefault();
        alert('El nombre debe tener al menos 3 caracteres');
        return;
    }

    // Validar caracteres permitidos
    if (!/^[a-zA-Z0-9\s]+$/.test(nombre)) {
        e.preventDefault();
        alert('El nombre contiene caracteres inválidos');
        return;
    }
});
```

### 4. Protección de rutas

```java
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/servicios", "/contacto").permitAll()
            .requestMatchers("/admin/**").authenticated()
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
```

### 5. Content Security Policy (CSP)

```html
<!-- Limitar fuentes de recursos para prevenir XSS -->
<meta http-equiv="Content-Security-Policy"
      content="default-src 'self';
               script-src 'self' https://cdn.jsdelivr.net;
               style-src 'self' https://cdn.jsdelivr.net;">
```

---

## Responsive Design Strategy

### Mobile-First Approach

**Concepto:** Diseñar primero para móviles, luego expandir a pantallas grandes.

```css
/* Estilos base (móvil) */
.container {
    padding: 10px;
    font-size: 14px;
}

.grid {
    display: block;  /* Una columna en móvil */
}

/* Tablet (768px+) */
@media (min-width: 768px) {
    .container {
        padding: 20px;
        font-size: 16px;
    }

    .grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);  /* 2 columnas */
    }
}

/* Desktop (1024px+) */
@media (min-width: 1024px) {
    .container {
        padding: 40px;
        max-width: 1200px;
        margin: 0 auto;
    }

    .grid {
        grid-template-columns: repeat(4, 1fr);  /* 4 columnas */
    }
}
```

### Bootstrap Grid System

```html
<!-- En móvil (xs): 100% ancho (1 columna) -->
<!-- En tablet (md): 50% ancho (2 columnas) -->
<!-- En desktop (lg): 25% ancho (4 columnas) -->
<div class="row">
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 1</div>
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 2</div>
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 3</div>
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 4</div>
</div>
```

### Viewport Meta Tag

```html
<!-- Esencial para responsive design -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

**Sin viewport meta:**
- Móviles renderizan la página con zoom out (se ve pequeño)

**Con viewport meta:**
- Móviles adaptan el ancho de la página al dispositivo

### Responsive Typography

```css
/* Tamaños de fuente adaptativos */
h1 {
    font-size: 2rem;    /* 32px en móvil */
}

@media (min-width: 768px) {
    h1 {
        font-size: 3rem;    /* 48px en tablet */
    }
}

@media (min-width: 1024px) {
    h1 {
        font-size: 4rem;    /* 64px en desktop */
    }
}

/* O usando clamp() (moderno) */
h1 {
    font-size: clamp(2rem, 5vw, 4rem);
    /* min: 2rem, preferido: 5% del ancho, max: 4rem */
}
```

### Responsive Images

```html
<!-- Imagen responsive con max-width -->
<img src="imagen.jpg" alt="Descripción" style="max-width: 100%; height: auto;">

<!-- Picture para diferentes resoluciones -->
<picture>
    <source media="(min-width: 1024px)" srcset="imagen-large.jpg">
    <source media="(min-width: 768px)" srcset="imagen-medium.jpg">
    <img src="imagen-small.jpg" alt="Descripción">
</picture>
```

### Testing Responsive

**Herramientas de desarrollo del navegador:**
```
Chrome DevTools:
  F12 → Toggle device toolbar (Ctrl+Shift+M)
  - iPhone
  - iPad
  - Galaxy
  - Responsive (personalizado)
```

**Breakpoints clave:**
```
320px   → Móvil pequeño (iPhone SE)
375px   → Móvil estándar (iPhone 12)
768px   → Tablet (iPad)
1024px  → Laptop
1440px  → Desktop
```

---

## 🎯 Conceptos clave para la sustentación

### 1. ¿Qué arquitectura usa el proyecto?
- **Respuesta**: MVC (Model-View-Controller) con Spring Boot en backend y Thymeleaf para las vistas.

### 2. ¿Cómo se comunica el frontend con el backend?
- **Respuesta**: El controlador agrega datos al Model con `model.addAttribute()`, Thymeleaf procesa la plantilla HTML con esos datos, y genera HTML final que se envía al navegador.

### 3. ¿Qué tecnologías componen el stack frontend?
- **Respuesta**: HTML5, CSS3, JavaScript (vanilla), Thymeleaf, Bootstrap 5, Font Awesome, y Chart.js.

### 4. ¿Por qué usar fragmentos de Thymeleaf?
- **Respuesta**: Para reutilizar código HTML (como navbar y footer) en múltiples páginas, evitando duplicación y facilitando el mantenimiento.

### 5. ¿Qué es el patrón mobile-first?
- **Respuesta**: Diseñar primero para móviles con estilos base, y luego usar media queries para adaptar a pantallas más grandes.

### 6. ¿Cómo se protege el proyecto contra XSS?
- **Respuesta**: Thymeleaf escapa automáticamente HTML con `th:text`, y evitamos `th:utext` con contenido de usuario.

### 7. ¿Cómo funciona el sistema de routing?
- **Respuesta**: Los controladores definen rutas con `@GetMapping` y `@PostMapping`, Thymeleaf genera URLs con `@{...}`, y Spring MVC mapea peticiones a métodos del controlador.

### 8. ¿Qué ventaja tiene usar CDN para recursos?
- **Respuesta**: Mayor velocidad de carga por servidores distribuidos globalmente, caché compartida del navegador, y no consume recursos del servidor.

### 9. ¿Cómo se organiza el código frontend?
- **Respuesta**: Templates HTML en `resources/templates/`, recursos estáticos en `resources/static/`, organizados por funcionalidad en carpetas (admin, auth, public, fragments).

### 10. ¿Qué hace el Bootstrap grid system?
- **Respuesta**: Divide la página en 12 columnas que se adaptan automáticamente según el tamaño de pantalla usando clases responsive (col-12, col-md-6, col-lg-3).

---

## 📝 Checklist de conocimientos arquitectura

Para la sustentación, debes poder explicar:

- [ ] Diagrama de arquitectura del proyecto (MVC)
- [ ] Flujo completo de una petición HTTP
- [ ] Stack tecnológico frontend y por qué se eligió cada tecnología
- [ ] Estructura de carpetas del proyecto
- [ ] Cómo Thymeleaf procesa plantillas
- [ ] Cómo se comunican backend y frontend
- [ ] Patrón de fragmentos para reutilizar código
- [ ] Sistema de routing con Spring MVC
- [ ] Gestión de recursos estáticos
- [ ] Estrategia responsive (mobile-first)
- [ ] Medidas de seguridad frontend (XSS, CSRF)
- [ ] Integración de CSS, JavaScript y Thymeleaf
- [ ] Ventajas y desventajas de cada tecnología
- [ ] Patrones de diseño utilizados
- [ ] Optimización de recursos (minificación, lazy loading)

---

## 📊 Diagrama de componentes frontend

```
┌─────────────────────────────────────────────────────────────┐
│                     NAVEGADOR (Cliente)                      │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌────────────────────────────────────────────────────┐     │
│  │                HTML (Estructura)                   │     │
│  │  - Thymeleaf templates                             │     │
│  │  - Fragmentos reutilizables                        │     │
│  │  - Formularios con th:object y th:field            │     │
│  └────────────────┬───────────────────────────────────┘     │
│                   │                                          │
│  ┌────────────────▼───────────────────────────────────┐     │
│  │                CSS (Presentación)                  │     │
│  │  - Bootstrap 5 (grid, componentes)                 │     │
│  │  - CSS Variables (colores, espaciados)            │     │
│  │  - Media queries (responsive)                      │     │
│  │  - Animaciones y transiciones                      │     │
│  └────────────────┬───────────────────────────────────┘     │
│                   │                                          │
│  ┌────────────────▼───────────────────────────────────┐     │
│  │            JavaScript (Comportamiento)             │     │
│  │  - Vanilla JS (DOM manipulation)                   │     │
│  │  - Event listeners                                 │     │
│  │  - Validación de formularios                       │     │
│  │  - LocalStorage                                    │     │
│  │  - Chart.js (gráficos)                            │     │
│  │  - Bootstrap JS (modal, dropdown)                  │     │
│  └────────────────────────────────────────────────────┘     │
│                                                              │
└──────────────────────────▲───────────────────────────────────┘
                           │
                           │ HTTP Request/Response
                           │
┌──────────────────────────▼───────────────────────────────────┐
│                  SPRING BOOT (Servidor)                      │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌──────────────────────────────────────────────────┐       │
│  │             Thymeleaf Engine                     │       │
│  │  Procesa templates + Model → HTML final          │       │
│  └────────────────┬─────────────────────────────────┘       │
│                   │                                          │
│  ┌────────────────▼─────────────────────────────────┐       │
│  │             Controllers                          │       │
│  │  - AdminController                               │       │
│  │  - AuthController                                │       │
│  │  - PublicController                              │       │
│  └────────────────┬─────────────────────────────────┘       │
│                   │                                          │
│  ┌────────────────▼─────────────────────────────────┐       │
│  │             Services (Lógica de negocio)         │       │
│  └────────────────┬─────────────────────────────────┘       │
│                   │                                          │
│  ┌────────────────▼─────────────────────────────────┐       │
│  │             Repositories (JPA)                   │       │
│  └────────────────┬─────────────────────────────────┘       │
│                   │                                          │
└───────────────────┼──────────────────────────────────────────┘
                    │
                    ▼
              ┌──────────┐
              │  MySQL   │
              │ Database │
              └──────────┘
```

---

**📚 Esta guía proporciona una visión completa de la arquitectura frontend del proyecto Carpentry SYC para tu sustentación final.**
