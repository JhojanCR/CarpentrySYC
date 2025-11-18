# GUÍA EDUCATIVA: JAVASCRIPT EN EL PROYECTO CARPENTRY SYC

## 📚 Tabla de Contenidos
1. [Introducción a JavaScript](#introducción-a-javascript)
2. [Sintaxis básica](#sintaxis-básica)
3. [Funciones](#funciones)
4. [DOM (Document Object Model)](#dom-document-object-model)
5. [Eventos](#eventos)
6. [Validación de formularios](#validación-de-formularios)
7. [LocalStorage](#localstorage)
8. [Chart.js para gráficos](#chartjs-para-gráficos)
9. [Bootstrap JavaScript](#bootstrap-javascript)
10. [Ejemplos del proyecto](#ejemplos-del-proyecto)

---

## Introducción a JavaScript

**JavaScript** es el lenguaje de programación que añade interactividad y comportamiento dinámico a las páginas web.

### ¿Qué hace JavaScript?

```javascript
// JavaScript puede:
- Responder a eventos (clicks, hover, teclas)
- Modificar el contenido HTML dinámicamente (DOM manipulation)
- Validar formularios antes de enviarlos
- Hacer peticiones al servidor sin recargar la página (AJAX)
- Crear animaciones y efectos
- Almacenar datos en el navegador (LocalStorage)
- Generar gráficos y visualizaciones (Chart.js)
```

### Tres pilares del desarrollo web

```
HTML       →  Estructura y contenido
CSS        →  Diseño y presentación
JavaScript →  Comportamiento e interactividad
```

### Incluir JavaScript en HTML

```html
<!-- 1. JavaScript inline (en línea) -->
<button onclick="alert('¡Hola!')">Click aquí</button>

<!-- 2. JavaScript interno (dentro de <script>) -->
<script>
    console.log('Hola desde JavaScript');
    document.getElementById('titulo').textContent = 'Nuevo título';
</script>

<!-- 3. JavaScript externo (archivo separado - mejor práctica) -->
<script src="script.js"></script>

<!-- Cargar script al final del body (mejora rendimiento) -->
<body>
    <!-- Contenido HTML -->

    <script src="script.js"></script>
</body>
```

---

## Sintaxis básica

### Variables

```javascript
// var (antiguo - evitar)
var nombre = 'Juan';

// let (variable que puede cambiar)
let edad = 25;
edad = 26;  // Puede cambiar

// const (constante - no puede cambiar)
const PI = 3.14159;
// PI = 3.14;  // ❌ Error: no se puede reasignar

// Tipos de datos
let texto = 'Hola';              // String (texto)
let numero = 42;                 // Number (número)
let decimal = 3.14;              // Number (decimal)
let verdadero = true;            // Boolean (true/false)
let falso = false;               // Boolean
let nulo = null;                 // Null (sin valor)
let indefinido = undefined;      // Undefined
let lista = [1, 2, 3];          // Array (lista)
let objeto = {nombre: 'Juan'};   // Object (objeto)
```

### Operadores

```javascript
// Aritméticos
let suma = 5 + 3;        // 8
let resta = 10 - 4;      // 6
let multiplicacion = 6 * 7;  // 42
let division = 20 / 4;   // 5
let modulo = 10 % 3;     // 1 (resto de la división)

// Concatenación de strings
let saludo = 'Hola' + ' ' + 'Mundo';  // "Hola Mundo"
let mensaje = `Bienvenido ${nombre}`;  // Template literals (ES6)

// Comparación
5 == '5'    // true (compara valor, no tipo)
5 === '5'   // false (compara valor y tipo)
5 != '5'    // false
5 !== '5'   // true
10 > 5      // true
10 < 5      // false
10 >= 10    // true

// Lógicos
true && false   // false (AND: ambos deben ser true)
true || false   // true (OR: al menos uno debe ser true)
!true          // false (NOT: invierte el valor)
```

### Condicionales

```javascript
// if - else
let edad = 18;

if (edad >= 18) {
    console.log('Mayor de edad');
} else {
    console.log('Menor de edad');
}

// if - else if - else
let calificacion = 85;

if (calificacion >= 90) {
    console.log('Excelente');
} else if (calificacion >= 70) {
    console.log('Bueno');
} else if (calificacion >= 50) {
    console.log('Regular');
} else {
    console.log('Insuficiente');
}

// Operador ternario (forma corta)
let mensaje = edad >= 18 ? 'Mayor' : 'Menor';
//            ↑ condición  ↑ si true ↑ si false
```

### Bucles (loops)

```javascript
// for (contador)
for (let i = 0; i < 5; i++) {
    console.log(i);  // 0, 1, 2, 3, 4
}

// while (mientras)
let contador = 0;
while (contador < 5) {
    console.log(contador);
    contador++;
}

// for...of (iterar arrays)
let frutas = ['manzana', 'banana', 'naranja'];
for (let fruta of frutas) {
    console.log(fruta);
}

// forEach (método de arrays)
frutas.forEach(function(fruta, index) {
    console.log(index + ': ' + fruta);
});
```

### Arrays (listas)

```javascript
// Crear array
let numeros = [1, 2, 3, 4, 5];
let frutas = ['manzana', 'banana', 'naranja'];

// Acceder a elementos (índice empieza en 0)
console.log(frutas[0]);  // "manzana"
console.log(frutas[1]);  // "banana"

// Métodos de arrays
frutas.push('uva');         // Agregar al final
frutas.pop();               // Eliminar del final
frutas.unshift('fresa');    // Agregar al inicio
frutas.shift();             // Eliminar del inicio

// Longitud del array
console.log(frutas.length);  // 3

// Buscar elemento
let indice = frutas.indexOf('banana');  // 1
let existe = frutas.includes('manzana');  // true

// Filtrar
let numerosPares = numeros.filter(num => num % 2 === 0);
// [2, 4]

// Mapear (transformar)
let dobles = numeros.map(num => num * 2);
// [2, 4, 6, 8, 10]
```

### Objetos

```javascript
// Crear objeto
let persona = {
    nombre: 'Juan',
    edad: 25,
    email: 'juan@ejemplo.com',
    saludar: function() {
        console.log('Hola, soy ' + this.nombre);
    }
};

// Acceder a propiedades
console.log(persona.nombre);     // "Juan"
console.log(persona['edad']);    // 25

// Modificar propiedades
persona.edad = 26;

// Agregar propiedades
persona.telefono = '123456789';

// Llamar métodos
persona.saludar();  // "Hola, soy Juan"
```

---

## Funciones

Las funciones son bloques de código reutilizables.

### Declaración de funciones

```javascript
// 1. Function declaration (declaración de función)
function saludar(nombre) {
    return 'Hola, ' + nombre;
}

// 2. Function expression (expresión de función)
const saludar = function(nombre) {
    return 'Hola, ' + nombre;
};

// 3. Arrow function (función flecha - ES6)
const saludar = (nombre) => {
    return 'Hola, ' + nombre;
};

// Arrow function simplificada (una sola expresión)
const saludar = nombre => 'Hola, ' + nombre;

// Llamar función
let mensaje = saludar('Juan');
console.log(mensaje);  // "Hola, Juan"
```

### Parámetros y return

```javascript
// Función sin parámetros
function obtenerFechaActual() {
    return new Date();
}

// Función con múltiples parámetros
function sumar(a, b) {
    return a + b;
}

// Función con parámetros por defecto
function saludar(nombre = 'Invitado') {
    return 'Hola, ' + nombre;
}

console.log(saludar());       // "Hola, Invitado"
console.log(saludar('Ana'));  // "Hola, Ana"

// Función sin return (retorna undefined)
function imprimirMensaje(mensaje) {
    console.log(mensaje);
    // No hay return
}
```

---

## DOM (Document Object Model)

El **DOM** es la representación en árbol del documento HTML que JavaScript puede manipular.

### Seleccionar elementos

```javascript
// Por ID (retorna un elemento)
let elemento = document.getElementById('miId');

// Por clase (retorna una colección)
let elementos = document.getElementsByClassName('miClase');

// Por etiqueta (retorna una colección)
let parrafos = document.getElementsByTagName('p');

// Query selector (CSS selector - retorna el primero)
let elemento = document.querySelector('.miClase');
let elemento = document.querySelector('#miId');
let elemento = document.querySelector('div.container > p');

// Query selector all (retorna todos)
let elementos = document.querySelectorAll('.miClase');
let botones = document.querySelectorAll('button.btn');
```

### Modificar contenido

```javascript
// Cambiar texto
let titulo = document.getElementById('titulo');
titulo.textContent = 'Nuevo título';        // Solo texto
titulo.innerHTML = '<strong>Título</strong>';  // HTML incluido

// Obtener contenido
let contenido = titulo.textContent;
```

### Modificar atributos

```javascript
let imagen = document.querySelector('img');

// Obtener atributo
let src = imagen.getAttribute('src');
let alt = imagen.alt;  // Shortcut para atributos comunes

// Cambiar atributo
imagen.setAttribute('src', 'nueva-imagen.jpg');
imagen.alt = 'Nueva descripción';  // Shortcut

// Eliminar atributo
imagen.removeAttribute('alt');

// Verificar si tiene atributo
if (imagen.hasAttribute('alt')) {
    console.log('Tiene alt');
}
```

### Modificar estilos

```javascript
let caja = document.getElementById('caja');

// Cambiar estilo inline
caja.style.backgroundColor = 'blue';
caja.style.color = 'white';
caja.style.padding = '20px';
caja.style.fontSize = '18px';  // CSS: font-size → camelCase

// Obtener estilos computados
let estilos = window.getComputedStyle(caja);
let ancho = estilos.width;
```

### Modificar clases CSS

```javascript
let elemento = document.getElementById('miElemento');

// Agregar clase
elemento.classList.add('activo');

// Eliminar clase
elemento.classList.remove('inactivo');

// Toggle (agregar si no tiene, eliminar si tiene)
elemento.classList.toggle('destacado');

// Verificar si tiene clase
if (elemento.classList.contains('activo')) {
    console.log('Está activo');
}

// Reemplazar clase
elemento.classList.replace('viejo', 'nuevo');
```

### Crear y agregar elementos

```javascript
// Crear elemento
let nuevoDiv = document.createElement('div');
nuevoDiv.textContent = 'Contenido nuevo';
nuevoDiv.classList.add('mi-clase');

// Agregar al final de un contenedor
let contenedor = document.getElementById('contenedor');
contenedor.appendChild(nuevoDiv);

// Agregar al inicio
contenedor.insertBefore(nuevoDiv, contenedor.firstChild);

// Eliminar elemento
let elementoAEliminar = document.getElementById('viejo');
elementoAEliminar.remove();

// O desde el padre
contenedor.removeChild(elementoAEliminar);
```

---

## Eventos

Los **eventos** permiten que JavaScript responda a interacciones del usuario.

### Tipos de eventos comunes

```javascript
// Eventos de mouse
click       // Click del mouse
dblclick    // Doble click
mouseenter  // Mouse entra al elemento
mouseleave  // Mouse sale del elemento
mouseover   // Mouse sobre el elemento
mousedown   // Botón del mouse presionado
mouseup     // Botón del mouse liberado

// Eventos de teclado
keydown     // Tecla presionada
keyup       // Tecla liberada
keypress    // Tecla presionada (deprecated)

// Eventos de formulario
submit      // Formulario enviado
change      // Valor de input cambiado
input       // Contenido de input cambiado (tiempo real)
focus       // Input recibe foco
blur        // Input pierde foco

// Eventos de documento
DOMContentLoaded  // HTML cargado y parseado
load              // Página completamente cargada (incluyendo imágenes)
scroll            // Scroll de la página
resize            // Ventana redimensionada
```

### Agregar event listeners

```javascript
// Forma 1: addEventListener (recomendada)
let boton = document.getElementById('miBoton');

boton.addEventListener('click', function() {
    alert('¡Botón clickeado!');
});

// Arrow function
boton.addEventListener('click', () => {
    alert('¡Botón clickeado!');
});

// Función con nombre
function manejarClick() {
    alert('¡Botón clickeado!');
}
boton.addEventListener('click', manejarClick);

// Forma 2: Propiedad on[evento] (menos flexible)
boton.onclick = function() {
    alert('¡Botón clickeado!');
};
```

### Objeto event

```javascript
boton.addEventListener('click', function(event) {
    console.log(event.target);        // Elemento que disparó el evento
    console.log(event.type);          // Tipo de evento ("click")
    console.log(event.clientX);       // Coordenada X del mouse
    console.log(event.clientY);       // Coordenada Y del mouse

    // Prevenir comportamiento por defecto
    event.preventDefault();

    // Detener propagación del evento
    event.stopPropagation();
});

// Ejemplo: prevenir envío de formulario
let formulario = document.querySelector('form');

formulario.addEventListener('submit', function(event) {
    event.preventDefault();  // No enviar el formulario

    // Validar datos
    let email = document.getElementById('email').value;
    if (!email.includes('@')) {
        alert('Email inválido');
        return;
    }

    // Si es válido, enviarlo manualmente
    formulario.submit();
});
```

### Event delegation (delegación de eventos)

```javascript
// En lugar de agregar listener a cada botón...
let botones = document.querySelectorAll('.btn-eliminar');
botones.forEach(boton => {
    boton.addEventListener('click', eliminarItem);
});

// ...agregar un listener al contenedor padre
let contenedor = document.getElementById('lista');
contenedor.addEventListener('click', function(event) {
    // Verificar si se hizo click en un botón eliminar
    if (event.target.classList.contains('btn-eliminar')) {
        eliminarItem(event);
    }
});

// Ventaja: Funciona con elementos agregados dinámicamente
```

---

## Validación de formularios

### Validación básica

```javascript
// Validar formulario antes de enviar
let formulario = document.getElementById('formularioContacto');

formulario.addEventListener('submit', function(event) {
    event.preventDefault();  // Prevenir envío

    // Obtener valores
    let nombre = document.getElementById('nombre').value.trim();
    let email = document.getElementById('email').value.trim();
    let mensaje = document.getElementById('mensaje').value.trim();

    // Limpiar mensajes de error previos
    limpiarErrores();

    // Validaciones
    let esValido = true;

    if (nombre === '') {
        mostrarError('nombre', 'El nombre es obligatorio');
        esValido = false;
    }

    if (email === '') {
        mostrarError('email', 'El email es obligatorio');
        esValido = false;
    } else if (!validarEmail(email)) {
        mostrarError('email', 'El email no es válido');
        esValido = false;
    }

    if (mensaje === '') {
        mostrarError('mensaje', 'El mensaje es obligatorio');
        esValido = false;
    } else if (mensaje.length < 10) {
        mostrarError('mensaje', 'El mensaje debe tener al menos 10 caracteres');
        esValido = false;
    }

    // Si es válido, enviar
    if (esValido) {
        formulario.submit();
    }
});

// Función para validar email
function validarEmail(email) {
    let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

// Función para mostrar error
function mostrarError(campoId, mensaje) {
    let campo = document.getElementById(campoId);
    campo.classList.add('is-invalid');

    let errorDiv = document.createElement('div');
    errorDiv.className = 'invalid-feedback';
    errorDiv.textContent = mensaje;

    campo.parentElement.appendChild(errorDiv);
}

// Función para limpiar errores
function limpiarErrores() {
    let camposInvalidos = document.querySelectorAll('.is-invalid');
    camposInvalidos.forEach(campo => {
        campo.classList.remove('is-invalid');
    });

    let mensajesError = document.querySelectorAll('.invalid-feedback');
    mensajesError.forEach(mensaje => {
        mensaje.remove();
    });
}
```

### Validación en tiempo real

```javascript
// Validar mientras el usuario escribe
let emailInput = document.getElementById('email');

emailInput.addEventListener('input', function() {
    let email = this.value;

    if (validarEmail(email)) {
        this.classList.remove('is-invalid');
        this.classList.add('is-valid');
    } else {
        this.classList.remove('is-valid');
        this.classList.add('is-invalid');
    }
});

// Mostrar contador de caracteres
let mensajeTextarea = document.getElementById('mensaje');
let contador = document.getElementById('contador');

mensajeTextarea.addEventListener('input', function() {
    let caracteres = this.value.length;
    contador.textContent = caracteres + ' / 500';

    if (caracteres > 500) {
        contador.classList.add('text-danger');
    } else {
        contador.classList.remove('text-danger');
    }
});
```

---

## LocalStorage

**LocalStorage** permite almacenar datos en el navegador de forma persistente.

### Guardar y recuperar datos

```javascript
// Guardar datos
localStorage.setItem('nombre', 'Juan');
localStorage.setItem('edad', '25');

// Recuperar datos
let nombre = localStorage.getItem('nombre');  // "Juan"
let edad = localStorage.getItem('edad');      // "25"

// Eliminar un dato
localStorage.removeItem('edad');

// Eliminar todos los datos
localStorage.clear();

// Verificar si existe una clave
if (localStorage.getItem('nombre')) {
    console.log('El nombre está guardado');
}
```

### Guardar objetos y arrays

```javascript
// LocalStorage solo guarda strings, usar JSON

// Guardar objeto
let usuario = {
    nombre: 'Juan',
    email: 'juan@ejemplo.com',
    edad: 25
};

localStorage.setItem('usuario', JSON.stringify(usuario));

// Recuperar objeto
let usuarioGuardado = JSON.parse(localStorage.getItem('usuario'));
console.log(usuarioGuardado.nombre);  // "Juan"

// Guardar array
let servicios = ['Cocina', 'Muebles', 'Remodelación'];
localStorage.setItem('servicios', JSON.stringify(servicios));

// Recuperar array
let serviciosGuardados = JSON.parse(localStorage.getItem('servicios'));
console.log(serviciosGuardados[0]);  // "Cocina"
```

### Ejemplo del proyecto: Persistir estado del sidebar

```javascript
// scripts.js
window.addEventListener('DOMContentLoaded', event => {
    const sidebarToggle = document.body.querySelector('#sidebarToggle');

    if (sidebarToggle) {
        // Restaurar estado del sidebar al cargar la página
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }

        // Guardar estado al hacer toggle
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');

            // Guardar estado en localStorage
            let estado = document.body.classList.contains('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', estado);
        });
    }
});
```

---

## Chart.js para gráficos

**Chart.js** es una librería para crear gráficos interactivos.

### Incluir Chart.js

```html
<!-- CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
```

### Crear un gráfico de barras

```html
<!-- Canvas para el gráfico -->
<canvas id="miGrafico" width="400" height="200"></canvas>
```

```javascript
// Obtener contexto del canvas
let ctx = document.getElementById('miGrafico').getContext('2d');

// Crear gráfico
let miGrafico = new Chart(ctx, {
    type: 'bar',  // Tipo: bar, line, pie, doughnut, etc.

    data: {
        // Etiquetas del eje X
        labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo'],

        // Conjuntos de datos
        datasets: [{
            label: 'Ventas',
            data: [12, 19, 3, 5, 2],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 1
        }]
    },

    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
```

### Ejemplo del proyecto: Gráfico de servicios por mes

```javascript
// chart-bar-demo.js
(function() {
    // Datos del backend (pasados por Thymeleaf)
    let labels = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun'];
    let data = [10, 15, 8, 12, 20, 18];

    // Obtener contexto
    let ctx = document.getElementById('chartServicios').getContext('2d');

    // Crear gráfico de barras
    let chartServicios = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Servicios creados',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgb(54, 162, 235)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize: 1  // Incrementos de 1
                    }
                }
            },
            plugins: {
                legend: {
                    display: true,
                    position: 'top'
                },
                title: {
                    display: true,
                    text: 'Servicios creados por mes'
                }
            }
        }
    });
})();
```

### Gráfico de líneas

```javascript
let chartLinea = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May'],
        datasets: [{
            label: 'Proyectos',
            data: [5, 8, 12, 15, 10],
            borderColor: 'rgb(75, 192, 192)',
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            tension: 0.4  // Suavizar línea
        }]
    },
    options: {
        responsive: true
    }
});
```

### Gráfico circular (pie/doughnut)

```javascript
let chartPie = new Chart(ctx, {
    type: 'pie',  // O 'doughnut' para circular con hueco
    data: {
        labels: ['Cocinas', 'Muebles', 'Remodelación', 'Reparaciones'],
        datasets: [{
            data: [40, 30, 20, 10],
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)'
            ]
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'bottom'
            }
        }
    }
});
```

---

## Bootstrap JavaScript

Bootstrap incluye componentes JavaScript para interactividad.

### Modals (ventanas modales)

```html
<!-- Botón que abre modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#miModal">
    Abrir modal
</button>

<!-- Modal -->
<div class="modal fade" id="miModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Título del modal</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>Contenido del modal</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>
```

```javascript
// Abrir modal con JavaScript
let modal = new bootstrap.Modal(document.getElementById('miModal'));
modal.show();

// Cerrar modal
modal.hide();

// Event listeners
let modalElement = document.getElementById('miModal');
modalElement.addEventListener('shown.bs.modal', function() {
    console.log('Modal abierto');
});

modalElement.addEventListener('hidden.bs.modal', function() {
    console.log('Modal cerrado');
});
```

### Dropdowns (menús desplegables)

```html
<div class="dropdown">
    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
        Menú
    </button>
    <ul class="dropdown-menu">
        <li><a class="dropdown-item" href="#">Opción 1</a></li>
        <li><a class="dropdown-item" href="#">Opción 2</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="#">Opción 3</a></li>
    </ul>
</div>
```

### Alerts (alertas)

```html
<div class="alert alert-success alert-dismissible fade show" role="alert">
    ¡Operación exitosa!
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
</div>
```

```javascript
// Crear alerta dinámicamente
function mostrarAlerta(mensaje, tipo) {
    let alerta = document.createElement('div');
    alerta.className = `alert alert-${tipo} alert-dismissible fade show`;
    alerta.role = 'alert';
    alerta.innerHTML = `
        ${mensaje}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;

    let contenedor = document.getElementById('alertas');
    contenedor.appendChild(alerta);

    // Auto-cerrar después de 5 segundos
    setTimeout(() => {
        alerta.remove();
    }, 5000);
}

// Uso
mostrarAlerta('Servicio guardado correctamente', 'success');
mostrarAlerta('Error al guardar', 'danger');
```

---

## Ejemplos del proyecto

### 1. Toggle del sidebar

```javascript
// scripts.js
window.addEventListener('DOMContentLoaded', event => {
    // Botón toggle del sidebar
    const sidebarToggle = document.body.querySelector('#sidebarToggle');

    if (sidebarToggle) {
        // Restaurar estado desde localStorage
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }

        // Event listener para el toggle
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();

            // Toggle de la clase
            document.body.classList.toggle('sb-sidenav-toggled');

            // Guardar estado en localStorage
            let isToggled = document.body.classList.contains('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', isToggled);
        });
    }
});
```

### 2. Confirmación antes de eliminar

```javascript
// Agregar confirmación a todos los botones de eliminar
document.querySelectorAll('.btn-eliminar').forEach(boton => {
    boton.addEventListener('click', function(event) {
        if (!confirm('¿Está seguro de eliminar este elemento?')) {
            event.preventDefault();  // Cancelar eliminación
        }
    });
});
```

```html
<!-- En el HTML con Thymeleaf -->
<form th:action="@{/admin/servicio/eliminar(id=${servicio.id})}"
      method="POST"
      style="display: inline;"
      onsubmit="return confirm('¿Está seguro de eliminar este servicio?');">
    <button type="submit" class="btn btn-sm btn-danger">
        <i class="fas fa-trash"></i> Eliminar
    </button>
</form>
```

### 3. Smooth scroll para navegación

```javascript
// index.html (página pública)
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();

        let targetId = this.getAttribute('href');
        let targetElement = document.querySelector(targetId);

        if (targetElement) {
            targetElement.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});
```

### 4. Validación de formulario de contacto

```javascript
let formularioContacto = document.getElementById('formularioContacto');

formularioContacto.addEventListener('submit', function(event) {
    let nombre = document.getElementById('nombre').value.trim();
    let email = document.getElementById('email').value.trim();
    let asunto = document.getElementById('asunto').value.trim();
    let mensaje = document.getElementById('mensaje').value.trim();

    // Validaciones
    if (nombre === '' || email === '' || asunto === '' || mensaje === '') {
        event.preventDefault();
        alert('Por favor, complete todos los campos');
        return;
    }

    if (!email.includes('@')) {
        event.preventDefault();
        alert('Por favor, ingrese un email válido');
        return;
    }

    if (mensaje.length < 10) {
        event.preventDefault();
        alert('El mensaje debe tener al menos 10 caracteres');
        return;
    }

    // Si pasa las validaciones, el formulario se envía normalmente
});
```

### 5. Contador de caracteres en textarea

```javascript
let mensajeTextarea = document.getElementById('mensaje');
let contadorDiv = document.getElementById('contador');

if (mensajeTextarea && contadorDiv) {
    mensajeTextarea.addEventListener('input', function() {
        let caracteres = this.value.length;
        let maximo = 500;

        contadorDiv.textContent = caracteres + ' / ' + maximo;

        if (caracteres > maximo) {
            contadorDiv.classList.add('text-danger');
            contadorDiv.classList.remove('text-muted');
        } else {
            contadorDiv.classList.add('text-muted');
            contadorDiv.classList.remove('text-danger');
        }
    });
}
```

### 6. Ocultar/mostrar contraseña

```javascript
let togglePassword = document.getElementById('togglePassword');
let passwordInput = document.getElementById('password');

togglePassword.addEventListener('click', function() {
    // Toggle del tipo de input
    let type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordInput.setAttribute('type', type);

    // Toggle del icono
    this.classList.toggle('fa-eye');
    this.classList.toggle('fa-eye-slash');
});
```

```html
<div class="input-group">
    <input type="password" class="form-control" id="password" placeholder="Contraseña">
    <button class="btn btn-outline-secondary" type="button" id="togglePassword">
        <i class="fas fa-eye"></i>
    </button>
</div>
```

### 7. Filtrar tabla en tiempo real

```javascript
let inputBusqueda = document.getElementById('busqueda');
let tabla = document.getElementById('tablaServicios');
let filas = tabla.querySelectorAll('tbody tr');

inputBusqueda.addEventListener('input', function() {
    let termino = this.value.toLowerCase();

    filas.forEach(fila => {
        let texto = fila.textContent.toLowerCase();

        if (texto.includes(termino)) {
            fila.style.display = '';  // Mostrar
        } else {
            fila.style.display = 'none';  // Ocultar
        }
    });
});
```

---

## 🎯 Conceptos clave para la sustentación

### 1. ¿Qué es JavaScript?
- **Respuesta**: Es el lenguaje de programación que añade interactividad y comportamiento dinámico a las páginas web.

### 2. ¿Qué es el DOM?
- **Respuesta**: Es la representación en árbol del documento HTML que JavaScript puede manipular para modificar contenido, estilos y estructura de forma dinámica.

### 3. ¿Qué hace addEventListener?
- **Respuesta**: Permite ejecutar código JavaScript cuando ocurre un evento específico (click, submit, input, etc.) en un elemento HTML.

### 4. ¿Qué hace event.preventDefault()?
- **Respuesta**: Previene el comportamiento por defecto de un evento, como evitar que un formulario se envíe o que un enlace navegue a otra página.

### 5. ¿Qué es LocalStorage?
- **Respuesta**: Es una API del navegador que permite almacenar datos de forma persistente en el navegador del usuario, incluso después de cerrar la página.

### 6. ¿Para qué sirve Chart.js?
- **Respuesta**: Es una librería JavaScript que permite crear gráficos interactivos (barras, líneas, circulares) para visualizar datos de forma atractiva.

### 7. ¿Qué diferencia hay entre let y const?
- **Respuesta**: `let` declara una variable que puede cambiar su valor, mientras que `const` declara una constante que no puede ser reasignada.

### 8. ¿Qué hace querySelector?
- **Respuesta**: Selecciona el primer elemento del DOM que coincida con el selector CSS especificado.

### 9. ¿Cómo validar un formulario con JavaScript?
- **Respuesta**: Usando event.preventDefault() en el evento submit, luego verificando las condiciones de validación, y solo enviando el formulario si pasa todas las validaciones.

### 10. ¿Qué hace classList.toggle()?
- **Respuesta**: Agrega una clase CSS si el elemento no la tiene, o la elimina si ya la tiene, permitiendo alternar entre estados.

---

## 📝 Glosario de términos JavaScript

- **Variable**: Contenedor para almacenar valores
- **Función**: Bloque de código reutilizable
- **DOM**: Document Object Model - representación en árbol del HTML
- **Event**: Acción que ocurre en la página (click, scroll, submit, etc.)
- **Event Listener**: Función que escucha y responde a eventos
- **Callback**: Función que se pasa como argumento a otra función
- **Arrow function**: Sintaxis moderna y concisa para definir funciones (=>)
- **Array**: Lista ordenada de elementos
- **Object**: Colección de pares clave-valor
- **JSON**: JavaScript Object Notation - formato de intercambio de datos
- **LocalStorage**: Almacenamiento persistente en el navegador
- **API**: Application Programming Interface - conjunto de funciones para interactuar con servicios
- **Asíncrono**: Código que no bloquea la ejecución (setTimeout, fetch, etc.)
- **Template literal**: String con interpolación usando backticks (`)
- **Ternary operator**: Operador condicional corto (condición ? true : false)

---

## ✅ Checklist de conocimientos JavaScript

Para la sustentación, debes poder explicar:

- [ ] Tipos de datos en JavaScript (string, number, boolean, etc.)
- [ ] Declaración de variables (let, const)
- [ ] Estructuras de control (if, for, while)
- [ ] Funciones (declaración, parámetros, return)
- [ ] Qué es el DOM y cómo manipularlo
- [ ] Seleccionar elementos del DOM (querySelector, getElementById)
- [ ] Modificar contenido (textContent, innerHTML)
- [ ] Modificar atributos y estilos
- [ ] Agregar y eliminar clases CSS con classList
- [ ] Qué son los eventos y cómo escucharlos
- [ ] Uso de addEventListener
- [ ] Prevenir comportamiento por defecto con preventDefault()
- [ ] Validación de formularios con JavaScript
- [ ] LocalStorage para almacenar datos
- [ ] Chart.js para crear gráficos
- [ ] Componentes JavaScript de Bootstrap (modal, dropdown, alert)

---

**📚 Esta guía cubre todos los conceptos de JavaScript usados en el proyecto Carpentry SYC para tu sustentación final.**
