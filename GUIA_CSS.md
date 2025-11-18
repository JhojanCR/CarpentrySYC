# GUÍA EDUCATIVA: CSS EN EL PROYECTO CARPENTRY SYC

## 📚 Tabla de Contenidos
1. [Introducción a CSS](#introducción-a-css)
2. [CSS Variables (Custom Properties)](#css-variables-custom-properties)
3. [Bootstrap Framework](#bootstrap-framework)
4. [Responsive Design](#responsive-design)
5. [Animaciones y Transiciones](#animaciones-y-transiciones)
6. [Selectores y Especificidad](#selectores-y-especificidad)
7. [Flexbox y Grid](#flexbox-y-grid)
8. [Ejemplos del Proyecto](#ejemplos-del-proyecto)

---

## Introducción a CSS

**CSS (Cascading Style Sheets)** es el lenguaje utilizado para dar estilo y diseño visual a las páginas web HTML.

### ¿Qué hace CSS?

```css
/* CSS controla: */
- Colores y fondos
- Tamaños y espaciados (padding, margin)
- Tipografía (fuentes, tamaños, pesos)
- Posicionamiento de elementos
- Animaciones y transiciones
- Diseño responsive (adaptable a móviles)
```

### Estructura básica de CSS

```css
/* Selector: indica QUÉ elementos se van a estilizar */
selector {
    /* Propiedad: indica QUÉ aspecto cambiar */
    propiedad: valor;

    /* Ejemplos */
    color: #FF6F00;           /* Color del texto */
    background-color: white;   /* Color de fondo */
    font-size: 16px;          /* Tamaño de fuente */
    padding: 20px;            /* Espacio interno */
    margin: 10px;             /* Espacio externo */
}
```

### Tres formas de aplicar CSS

1. **CSS Inline (en línea)** - directamente en el HTML
```html
<div style="color: red; font-size: 20px;">Texto rojo</div>
```

2. **CSS Interno** - dentro de la etiqueta `<style>` en el HTML
```html
<head>
    <style>
        .mi-clase {
            color: blue;
        }
    </style>
</head>
```

3. **CSS Externo** - archivo separado (mejor práctica)
```html
<head>
    <link rel="stylesheet" href="styles.css">
</head>
```

---

## CSS Variables (Custom Properties)

Las **CSS Variables** permiten definir valores reutilizables en todo el proyecto.

### Sintaxis básica

```css
/* Definir variables globales en :root */
:root {
    /* Nombre de variable: --nombre-variable */
    --color-primary: #0d6efd;
    --color-secondary: #6c757d;
    --font-size-base: 16px;
    --spacing-unit: 8px;
}

/* Usar variables con var() */
.boton {
    background-color: var(--color-primary);
    font-size: var(--font-size-base);
    padding: var(--spacing-unit);
}
```

### Ventajas de las CSS Variables

1. **Mantenimiento fácil**: Cambiar un color en un solo lugar actualiza todo el sitio
2. **Consistencia**: Asegura que todos los elementos usen los mismos valores
3. **Legibilidad**: Nombres descriptivos en lugar de códigos hexadecimales
4. **Temas dinámicos**: Facilita crear temas claros/oscuros

### Ejemplo del proyecto (página pública)

```css
/* Variables de colores temáticos de carpintería */
:root {
    /* Colores de madera */
    --madera-oscura: #3E2723;      /* Marrón oscuro */
    --madera-media: #5D4037;       /* Marrón medio */
    --madera-clara: #8D6E63;       /* Marrón claro */

    /* Colores de acento */
    --naranja-carpinteria: #FF6F00; /* Naranja corporativo */
    --naranja-claro: #FFA726;       /* Naranja claro */

    /* Colores de fondo */
    --crema: #FFF8E1;              /* Fondo crema */
    --beige: #FFECB3;              /* Beige */
}

/* Aplicación en elementos */
.hero-section {
    background-color: var(--madera-oscura);
    color: var(--crema);
}

.btn-primary {
    background-color: var(--naranja-carpinteria);
}

.btn-primary:hover {
    background-color: var(--naranja-claro);
}
```

### Variables de Bootstrap

Bootstrap 5 también usa CSS Variables extensivamente:

```css
:root {
    /* Colores del sistema de diseño */
    --bs-blue: #0d6efd;
    --bs-primary: #0d6efd;
    --bs-secondary: #6c757d;
    --bs-success: #198754;
    --bs-danger: #dc3545;
    --bs-warning: #ffc107;
    --bs-info: #0dcaf0;

    /* Espaciados y bordes */
    --bs-border-radius: 0.375rem;
    --bs-border-width: 1px;

    /* Tipografía */
    --bs-font-sans-serif: system-ui, -apple-system, "Segoe UI", Roboto;
    --bs-body-font-size: 1rem;
    --bs-body-line-height: 1.5;
}
```

---

## Bootstrap Framework

**Bootstrap** es un framework CSS que proporciona componentes pre-diseñados y un sistema de grid responsive.

### Sistema de Grid de Bootstrap

Bootstrap divide la página en **12 columnas**:

```html
<!-- Contenedor principal -->
<div class="container">
    <!-- Fila (row) -->
    <div class="row">
        <!-- Columnas que suman 12 -->
        <div class="col-md-6">Columna 1 (50% del ancho)</div>
        <div class="col-md-6">Columna 2 (50% del ancho)</div>
    </div>
</div>
```

### Breakpoints (puntos de quiebre)

Bootstrap adapta el diseño según el tamaño de pantalla:

```
xs (extra small)  < 576px   - Móviles pequeños
sm (small)        ≥ 576px   - Móviles grandes
md (medium)       ≥ 768px   - Tablets
lg (large)        ≥ 992px   - Laptops
xl (extra large)  ≥ 1200px  - Monitores
xxl               ≥ 1400px  - Monitores grandes
```

### Ejemplo: Grid responsive

```html
<div class="row">
    <!-- En móvil (xs): 100% ancho -->
    <!-- En tablet (md): 50% ancho -->
    <!-- En desktop (lg): 25% ancho -->
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 1</div>
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 2</div>
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 3</div>
    <div class="col-12 col-md-6 col-lg-3">Tarjeta 4</div>
</div>
```

### Clases de utilidad de Bootstrap

```css
/* ESPACIADO (m = margin, p = padding) */
.m-3     /* margin: 1rem en todos los lados */
.mt-4    /* margin-top: 1.5rem */
.mb-4    /* margin-bottom: 1.5rem */
.px-4    /* padding-left y padding-right: 1.5rem */
.py-2    /* padding-top y padding-bottom: 0.5rem */

/* DISPLAY */
.d-flex           /* display: flex */
.d-none           /* display: none (ocultar) */
.d-md-block       /* display: block solo en pantallas md+ */

/* COLORES */
.text-primary     /* Color de texto primario (azul) */
.text-danger      /* Color de texto peligro (rojo) */
.bg-light         /* Fondo claro */
.bg-dark          /* Fondo oscuro */

/* ALINEACIÓN */
.text-center      /* Texto centrado */
.text-end         /* Texto a la derecha */
.justify-content-between   /* Espaciado entre elementos flex */
.align-items-center        /* Alineación vertical centrada */
```

### Componentes de Bootstrap usados en el proyecto

#### 1. Navbar (Barra de navegación)

```html
<nav class="navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand ps-3" href="/admin/dashboard">SYC Admin</a>
    <ul class="navbar-nav ms-auto">
        <li class="nav-item">
            <a class="nav-link" href="#">Item</a>
        </li>
    </ul>
</nav>
```

**Clases importantes:**
- `navbar`: Contenedor principal
- `navbar-dark`: Tema oscuro (texto claro)
- `bg-dark`: Fondo oscuro
- `navbar-expand`: Navbar siempre expandida (no colapsa)
- `ms-auto`: Margin start auto (empuja hacia la derecha)

#### 2. Cards (Tarjetas)

```html
<div class="card">
    <div class="card-header">
        <h3>Título de la tarjeta</h3>
    </div>
    <div class="card-body">
        <p class="card-text">Contenido de la tarjeta</p>
    </div>
</div>
```

**Variantes de colores:**
```html
<div class="card bg-primary text-white">...</div>
<div class="card bg-success text-white">...</div>
<div class="card bg-danger text-white">...</div>
```

#### 3. Buttons (Botones)

```html
<!-- Botones de diferentes estilos -->
<button class="btn btn-primary">Primario</button>
<button class="btn btn-success">Éxito</button>
<button class="btn btn-danger">Peligro</button>

<!-- Botones de diferentes tamaños -->
<button class="btn btn-primary btn-sm">Pequeño</button>
<button class="btn btn-primary">Normal</button>
<button class="btn btn-primary btn-lg">Grande</button>
```

#### 4. Forms (Formularios)

```html
<form>
    <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" placeholder="Ingrese nombre">
    </div>

    <div class="mb-3">
        <label for="descripcion" class="form-label">Descripción</label>
        <textarea class="form-control" id="descripcion" rows="3"></textarea>
    </div>

    <button type="submit" class="btn btn-primary">Enviar</button>
</form>
```

**Clases importantes:**
- `form-label`: Estilo de etiqueta
- `form-control`: Estilo de input/textarea
- `mb-3`: Margin bottom para separar campos

#### 5. Tables (Tablas)

```html
<table class="table table-striped table-hover">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>Servicio 1</td>
            <td>
                <button class="btn btn-sm btn-primary">Editar</button>
                <button class="btn btn-sm btn-danger">Eliminar</button>
            </td>
        </tr>
    </tbody>
</table>
```

**Clases importantes:**
- `table`: Tabla básica
- `table-striped`: Filas con colores alternados (cebra)
- `table-hover`: Efecto hover en filas
- `table-responsive`: Hace la tabla scrolleable en móviles

---

## Responsive Design

El **diseño responsive** adapta la interfaz a diferentes tamaños de pantalla.

### Media Queries

Las **media queries** permiten aplicar CSS solo en ciertos tamaños de pantalla:

```css
/* Estilos base (móvil-first) */
.container {
    padding: 10px;
    font-size: 14px;
}

/* En tablets (768px+) */
@media (min-width: 768px) {
    .container {
        padding: 20px;
        font-size: 16px;
    }
}

/* En desktop (1024px+) */
@media (min-width: 1024px) {
    .container {
        padding: 40px;
        font-size: 18px;
        max-width: 1200px;
        margin: 0 auto;
    }
}
```

### Ejemplo del proyecto (página pública)

```css
/* Hero section - Estilos móvil */
.hero-section {
    padding: 60px 20px;
}

.hero-section h1 {
    font-size: 2rem;    /* 32px en móvil */
}

/* Tablets (768px+) */
@media (min-width: 768px) {
    .hero-section {
        padding: 100px 40px;
    }

    .hero-section h1 {
        font-size: 3rem;    /* 48px en tablet */
    }
}

/* Desktop (1024px+) */
@media (min-width: 1024px) {
    .hero-section {
        padding: 150px 60px;
    }

    .hero-section h1 {
        font-size: 4rem;    /* 64px en desktop */
    }
}
```

### Unidades responsive

```css
/* Unidades absolutas (fijas) */
px   /* Píxeles - siempre el mismo tamaño */

/* Unidades relativas (escalables) */
em   /* Relativo al font-size del padre */
rem  /* Relativo al font-size del root (html) */
%    /* Porcentaje del contenedor padre */
vw   /* 1vw = 1% del ancho del viewport */
vh   /* 1vh = 1% del alto del viewport */

/* Ejemplos */
.container {
    width: 100%;        /* 100% del padre */
    max-width: 1200px;  /* Máximo 1200px */
    padding: 2rem;      /* 32px si root font-size = 16px */
}

.hero {
    height: 100vh;      /* Altura completa de la ventana */
}

.titulo {
    font-size: 2rem;    /* 32px si root = 16px */
}
```

---

## Animaciones y Transiciones

### Transiciones (Transitions)

Las **transiciones** animan cambios de propiedades CSS:

```css
/* Sintaxis básica */
.elemento {
    /* Propiedad inicial */
    background-color: blue;

    /* Configurar transición */
    transition: background-color 0.3s ease;
    /*          ↑ propiedad    ↑ duración ↑ timing */
}

.elemento:hover {
    /* Propiedad final */
    background-color: red;
    /* La transición animará el cambio */
}
```

### Propiedades de transition

```css
transition-property: all;              /* Qué animar (all, color, transform, etc.) */
transition-duration: 0.3s;            /* Duración (segundos) */
transition-timing-function: ease;      /* Curva de animación */
transition-delay: 0s;                 /* Retardo antes de iniciar */

/* Shorthand (forma corta) */
transition: all 0.3s ease 0s;
/*          ↑   ↑    ↑    ↑
          prop dur  ease delay */
```

### Timing functions

```css
ease            /* Inicio lento, rápido en medio, lento al final (default) */
linear          /* Velocidad constante */
ease-in         /* Inicio lento, aceleración */
ease-out        /* Rápido al inicio, desaceleración */
ease-in-out     /* Lento al inicio y final, rápido en medio */

/* Curva personalizada */
cubic-bezier(0.4, 0, 0.2, 1)
```

### Ejemplo del proyecto (botones)

```css
.btn {
    background-color: #FF6F00;
    color: white;
    padding: 12px 30px;
    border: none;
    border-radius: 8px;

    /* Transiciones múltiples */
    transition: background-color 0.3s ease,
                transform 0.2s ease,
                box-shadow 0.3s ease;
}

.btn:hover {
    background-color: #FFA726;
    transform: translateY(-2px);      /* Sube 2px */
    box-shadow: 0 8px 16px rgba(0,0,0,0.2);
}

.btn:active {
    transform: translateY(0);         /* Vuelve a posición original */
}
```

### Animaciones (Animations)

Las **animaciones** permiten crear secuencias más complejas:

```css
/* 1. Definir la animación con @keyframes */
@keyframes fadeInUp {
    /* Estado inicial (0%) */
    from {
        opacity: 0;
        transform: translateY(30px);
    }

    /* Estado final (100%) */
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

/* 2. Aplicar la animación al elemento */
.hero-title {
    animation: fadeInUp 1s ease-out;
    /*         ↑ nombre ↑ dur ↑ timing */
}
```

### Animación con múltiples pasos

```css
@keyframes pulse {
    0% {
        transform: scale(1);
    }
    50% {
        transform: scale(1.05);
    }
    100% {
        transform: scale(1);
    }
}

.boton-destacado {
    animation: pulse 2s infinite;
    /*                   ↑ se repite infinitamente */
}
```

### Ejemplo del proyecto (hero section)

```css
/* Animación de entrada para el hero */
@keyframes fadeInUp {
    from {
        opacity: 0;
        transform: translateY(30px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.hero-content h1 {
    animation: fadeInUp 1s ease-out;
}

.hero-content p {
    animation: fadeInUp 1s ease-out 0.2s;
    /* ↑ 0.2s de delay para efecto en cascada */
    animation-fill-mode: backwards;
    /* ↑ mantiene el estado 'from' durante el delay */
}

.hero-content .btn {
    animation: fadeInUp 1s ease-out 0.4s;
    animation-fill-mode: backwards;
}
```

### Transform (transformaciones)

```css
/* Mover (translate) */
transform: translateX(50px);      /* Mover 50px a la derecha */
transform: translateY(-20px);     /* Mover 20px arriba */
transform: translate(50px, -20px); /* Ambos ejes */

/* Escalar (scale) */
transform: scale(1.2);            /* 120% del tamaño original */
transform: scaleX(0.5);           /* 50% del ancho */

/* Rotar (rotate) */
transform: rotate(45deg);         /* Rotar 45 grados */

/* Sesgar (skew) */
transform: skewX(10deg);

/* Combinar transformaciones */
transform: translateY(-5px) scale(1.1) rotate(5deg);
```

---

## Selectores y Especificidad

### Tipos de selectores

```css
/* 1. Selector de elemento (tag) */
h1 {
    color: blue;
}

/* 2. Selector de clase */
.mi-clase {
    color: red;
}

/* 3. Selector de ID */
#mi-id {
    color: green;
}

/* 4. Selector de atributo */
input[type="text"] {
    border: 1px solid gray;
}

/* 5. Pseudo-clases (:) */
a:hover {
    color: orange;
}

button:active {
    transform: scale(0.95);
}

input:focus {
    border-color: blue;
}

li:first-child {
    font-weight: bold;
}

li:last-child {
    margin-bottom: 0;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

/* 6. Pseudo-elementos (::) */
p::first-letter {
    font-size: 2em;
    font-weight: bold;
}

.btn::before {
    content: "→ ";
}

/* 7. Selectores combinadores */
/* Descendiente (espacio) */
.card p {
    /* Todos los <p> dentro de .card */
}

/* Hijo directo (>) */
.card > p {
    /* Solo <p> que son hijos directos de .card */
}

/* Hermano adyacente (+) */
h2 + p {
    /* <p> que sigue inmediatamente a <h2> */
}

/* Hermanos generales (~) */
h2 ~ p {
    /* Todos los <p> que son hermanos de <h2> */
}
```

### Especificidad (prioridad)

Cuando múltiples reglas se aplican al mismo elemento, la **especificidad** determina cuál gana:

```
Puntos de especificidad:
- Estilos inline:      1000 puntos
- IDs (#):             100 puntos
- Clases, atributos:   10 puntos
- Elementos (tags):    1 punto
```

Ejemplos:

```css
/* Especificidad: 1 (1 elemento) */
p {
    color: black;
}

/* Especificidad: 10 (1 clase) */
.texto {
    color: blue;    /* Esta regla gana sobre la anterior */
}

/* Especificidad: 11 (1 clase + 1 elemento) */
p.texto {
    color: red;     /* Esta gana sobre .texto */
}

/* Especificidad: 100 (1 ID) */
#parrafo {
    color: green;   /* Esta gana sobre todas las anteriores */
}

/* Especificidad: 1000 (inline) */
<p style="color: purple;">  <!-- Esta gana sobre todas -->
```

### !important (usar con precaución)

```css
.texto {
    color: red !important;  /* Fuerza esta regla sobre todas las demás */
}
```

⚠️ **Evitar !important** porque rompe la cascada natural de CSS y dificulta el mantenimiento.

---

## Flexbox y Grid

### Flexbox

**Flexbox** es ideal para layouts en una dimensión (fila o columna):

```css
/* Contenedor flex */
.container {
    display: flex;

    /* Dirección */
    flex-direction: row;        /* horizontal (default) */
    flex-direction: column;     /* vertical */

    /* Justificación (eje principal) */
    justify-content: flex-start;    /* inicio */
    justify-content: center;        /* centro */
    justify-content: flex-end;      /* final */
    justify-content: space-between; /* espaciado entre items */
    justify-content: space-around;  /* espaciado alrededor */
    justify-content: space-evenly;  /* espaciado uniforme */

    /* Alineación (eje cruzado) */
    align-items: stretch;       /* estirar (default) */
    align-items: flex-start;    /* arriba */
    align-items: center;        /* centro */
    align-items: flex-end;      /* abajo */

    /* Wrap (envolver) */
    flex-wrap: nowrap;          /* no envolver (default) */
    flex-wrap: wrap;            /* envolver a nueva línea */

    /* Gap (espaciado) */
    gap: 20px;                  /* espacio entre items */
}

/* Items flex */
.item {
    /* Flexibilidad */
    flex: 1;                    /* crecer para llenar espacio */
    flex-grow: 1;               /* factor de crecimiento */
    flex-shrink: 1;             /* factor de encogimiento */
    flex-basis: 200px;          /* tamaño base */
}
```

### Ejemplo del proyecto (navbar)

```html
<nav class="navbar">
    <a class="navbar-brand">Logo</a>
    <ul class="navbar-nav ms-auto">...</ul>
</nav>
```

```css
.navbar {
    display: flex;
    justify-content: space-between;  /* Logo a la izquierda, nav a la derecha */
    align-items: center;             /* Centrado vertical */
}

.navbar-nav {
    display: flex;
    gap: 20px;                       /* Espacio entre items del menú */
}
```

### CSS Grid

**CSS Grid** es ideal para layouts en dos dimensiones (filas y columnas):

```css
.grid-container {
    display: grid;

    /* Definir columnas */
    grid-template-columns: 1fr 1fr 1fr;     /* 3 columnas iguales */
    grid-template-columns: 200px 1fr 200px; /* Fijo-Flexible-Fijo */
    grid-template-columns: repeat(4, 1fr);   /* 4 columnas iguales */

    /* Definir filas */
    grid-template-rows: auto 1fr auto;      /* Header-Content-Footer */

    /* Espaciado */
    gap: 20px;                  /* espacio entre celdas */
    row-gap: 20px;             /* espacio entre filas */
    column-gap: 30px;          /* espacio entre columnas */
}

/* Items del grid */
.item {
    /* Posicionar item en grid */
    grid-column: 1 / 3;        /* Ocupa columnas 1-2 */
    grid-row: 1 / 2;           /* Ocupa fila 1 */

    /* Shorthand */
    grid-area: 1 / 1 / 2 / 3;  /* row-start / col-start / row-end / col-end */
}
```

### Ejemplo: Grid de tarjetas responsive

```css
.tarjetas-grid {
    display: grid;

    /* Móvil: 1 columna */
    grid-template-columns: 1fr;
    gap: 20px;
}

/* Tablet: 2 columnas */
@media (min-width: 768px) {
    .tarjetas-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

/* Desktop: 4 columnas */
@media (min-width: 1024px) {
    .tarjetas-grid {
        grid-template-columns: repeat(4, 1fr);
    }
}
```

---

## Ejemplos del Proyecto

### 1. Página pública (index.html)

#### Hero Section con gradiente y parallax

```css
.hero-section {
    /* Fondo con imagen y gradiente */
    background: linear-gradient(
        135deg,
        rgba(62, 39, 35, 0.95),    /* Madera oscura con transparencia */
        rgba(255, 111, 0, 0.85)    /* Naranja con transparencia */
    ), url('https://images.unsplash.com/photo-carpinteria');

    background-size: cover;         /* Imagen cubre todo el área */
    background-position: center;    /* Centrada */
    background-attachment: fixed;   /* Efecto parallax */

    /* Espaciado */
    padding: 150px 20px;

    /* Centrado */
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;

    /* Altura completa */
    min-height: 100vh;

    /* Color del texto */
    color: var(--crema);
}
```

#### Tarjetas de servicios con hover

```css
.service-card {
    /* Diseño básico */
    background: white;
    border-radius: 15px;
    padding: 30px;

    /* Sombra suave */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

    /* Transición suave */
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.service-card:hover {
    /* Efecto de elevación */
    transform: translateY(-10px);
    box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
}

.service-card img {
    width: 100%;
    height: 250px;
    object-fit: cover;         /* Recorta la imagen para llenar el espacio */
    border-radius: 10px;
    margin-bottom: 20px;
}

.service-card h3 {
    color: var(--madera-oscura);
    margin-bottom: 15px;
}
```

#### Botón con efectos

```css
.btn-carpinteria {
    /* Diseño */
    background: linear-gradient(
        135deg,
        var(--naranja-carpinteria),
        var(--naranja-claro)
    );
    color: white;
    padding: 15px 40px;
    border: none;
    border-radius: 50px;        /* Bordes muy redondeados */
    font-size: 1.1rem;
    font-weight: 600;

    /* Sombra */
    box-shadow: 0 4px 15px rgba(255, 111, 0, 0.3);

    /* Cursor */
    cursor: pointer;

    /* Transiciones */
    transition: all 0.3s ease;
}

.btn-carpinteria:hover {
    transform: translateY(-3px);
    box-shadow: 0 8px 25px rgba(255, 111, 0, 0.4);
}

.btn-carpinteria:active {
    transform: translateY(-1px);
}
```

### 2. Dashboard administrativo

#### Layout sidebar + contenido

```css
/* Layout principal con flexbox */
#layoutSidenav {
    display: flex;
}

/* Sidebar fijo */
#layoutSidenav_nav {
    width: 225px;
    flex-shrink: 0;         /* No se encoge */
    background-color: #212529;
    min-height: 100vh;
}

/* Contenido flexible */
#layoutSidenav_content {
    flex-grow: 1;           /* Crece para llenar espacio */
    background-color: #f8f9fa;
}
```

#### Tarjetas de estadísticas

```css
.stat-card {
    /* Diseño con gradiente */
    background: linear-gradient(135deg, #0d6efd, #0a58ca);
    color: white;
    border-radius: 8px;
    padding: 20px;

    /* Layout interno */
    display: flex;
    justify-content: space-between;
    align-items: center;

    /* Sombra */
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);

    /* Transición */
    transition: transform 0.2s ease;
}

.stat-card:hover {
    transform: scale(1.05);
}

.stat-card .numero {
    font-size: 2rem;
    font-weight: bold;
}

.stat-card .icono {
    font-size: 3rem;
    opacity: 0.8;
}
```

### 3. Formularios estilizados

```css
/* Input con borde enfocado */
.form-control {
    border: 2px solid #dee2e6;
    border-radius: 8px;
    padding: 12px 15px;
    font-size: 1rem;
    transition: border-color 0.2s ease, box-shadow 0.2s ease;
}

.form-control:focus {
    border-color: var(--naranja-carpinteria);
    box-shadow: 0 0 0 3px rgba(255, 111, 0, 0.1);
    outline: none;          /* Quitar borde azul del navegador */
}

/* Label con animación */
.form-label {
    font-weight: 600;
    color: var(--madera-oscura);
    margin-bottom: 8px;
    display: block;
    transition: color 0.2s ease;
}

.form-control:focus + .form-label {
    color: var(--naranja-carpinteria);
}
```

### 4. Tabla responsive

```css
.table-container {
    overflow-x: auto;       /* Scroll horizontal en móviles */
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.table {
    background: white;
    border-collapse: collapse;
}

.table thead {
    background-color: var(--madera-oscura);
    color: white;
}

.table th {
    padding: 15px;
    text-align: left;
    font-weight: 600;
}

.table td {
    padding: 12px 15px;
    border-bottom: 1px solid #dee2e6;
}

/* Fila hover */
.table tbody tr:hover {
    background-color: #f8f9fa;
}

/* Filas alternas (zebra) */
.table tbody tr:nth-child(even) {
    background-color: #fafafa;
}
```

---

## 🎯 Conceptos clave para la sustentación

### 1. ¿Por qué usar CSS Variables?
- **Respuesta**: Permiten mantener consistencia de colores y valores en todo el proyecto. Si cambio `--naranja-carpinteria` en un lugar, se actualiza en todos los elementos que lo usan.

### 2. ¿Qué es Bootstrap y por qué lo usamos?
- **Respuesta**: Es un framework CSS que nos da componentes pre-diseñados (botones, formularios, tarjetas) y un sistema de grid responsive. Nos ahorra tiempo y asegura que el diseño sea profesional y responsive.

### 3. ¿Cómo funciona el diseño responsive?
- **Respuesta**: Usamos media queries y el sistema de grid de Bootstrap para adaptar el layout a diferentes tamaños de pantalla. Por ejemplo, en móvil mostramos 1 columna, en tablet 2 columnas, y en desktop 4 columnas.

### 4. ¿Qué son las transiciones y animaciones?
- **Respuesta**: Las transiciones animan cambios de propiedades (como hover). Las animaciones con @keyframes permiten crear secuencias más complejas con múltiples estados.

### 5. ¿Qué es Flexbox y cuándo lo usamos?
- **Respuesta**: Es un sistema de layout unidimensional (fila o columna). Lo usamos para el navbar, para centrar elementos, y para distribuir espacio entre items.

---

## 📝 Glosario de términos CSS

- **Cascading**: El orden y especificidad de las reglas CSS determinan qué estilos se aplican
- **Selector**: Indica qué elementos HTML se van a estilizar
- **Property**: La característica que se va a modificar (color, tamaño, etc.)
- **Value**: El valor que se asigna a la propiedad
- **Inline styles**: Estilos aplicados directamente en el atributo style del HTML
- **External stylesheet**: Archivo CSS separado vinculado con `<link>`
- **Pseudo-class**: Estado especial de un elemento (`:hover`, `:focus`, `:active`)
- **Pseudo-element**: Parte específica de un elemento (`::before`, `::after`, `::first-letter`)
- **Media query**: Regla CSS que se aplica solo en ciertos tamaños de pantalla
- **Viewport**: El área visible de la página web en el navegador
- **Box model**: Modelo de cajas (content, padding, border, margin)
- **Z-index**: Controla el orden de apilamiento de elementos superpuestos
- **Opacity**: Transparencia de un elemento (0 = invisible, 1 = opaco)
- **Transform**: Aplica transformaciones 2D/3D (mover, escalar, rotar)
- **Gradient**: Transición suave entre dos o más colores

---

## ✅ Checklist de conocimientos CSS

Para la sustentación, debes poder explicar:

- [ ] Qué es CSS y para qué sirve
- [ ] Diferencia entre clase, ID y selector de elemento
- [ ] Cómo funcionan las CSS Variables y por qué las usamos
- [ ] Qué es Bootstrap y qué componentes usamos en el proyecto
- [ ] Cómo funciona el sistema de grid de Bootstrap (12 columnas)
- [ ] Qué son los breakpoints y cuáles usa Bootstrap
- [ ] Cómo funcionan las media queries para diseño responsive
- [ ] Diferencia entre transiciones y animaciones
- [ ] Cómo crear un efecto hover con transiciones
- [ ] Qué es Flexbox y cuándo usarlo
- [ ] Cómo centrar elementos vertical y horizontalmente
- [ ] Qué son las clases de utilidad de Bootstrap (m-3, p-4, d-flex, etc.)
- [ ] Cómo funciona la especificidad de CSS
- [ ] Qué estilos específicos aplicamos en el proyecto (colores de madera, efectos hover, etc.)

---

**📚 Esta guía cubre todos los conceptos de CSS usados en el proyecto Carpentry SYC para tu sustentación final.**
