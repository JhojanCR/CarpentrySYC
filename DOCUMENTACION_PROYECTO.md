# 📚 DOCUMENTACIÓN DEL PROYECTO - CARPENTRY SYC

## 🎯 ¿QUÉ ES ESTE PROYECTO?

Un sistema web completo para una empresa de carpintería que incluye:
- **Sitio web público**: Para que los clientes vean servicios, proyectos y contacten
- **Panel administrativo (Dashboard)**: Para gestionar todo el contenido
- **Sistema de autenticación**: Login/logout con usuarios y contraseñas
- **Generación de reportes**: Exportar datos en PDF y Excel
- **Recuperación de contraseña**: Sistema completo de reset de contraseña

---

## 🏗️ ARQUITECTURA DEL PROYECTO (Patrón MVC)

```
┌─────────────────────────────────────────────────────┐
│              USUARIO (Navegador Web)                │
└──────────────────┬──────────────────────────────────┘
                   │
         ┌─────────▼──────────┐
         │    VISTA (HTML)    │  ← Thymeleaf templates
         │  dashboard.html    │    Muestran datos al usuario
         │  servicios.html    │
         └─────────┬──────────┘
                   │
         ┌─────────▼──────────┐
         │   CONTROLADOR      │  ← Recibe peticiones HTTP
         │ AdminController    │    Procesa la lógica
         │ AuthController     │    Llama a servicios
         └─────────┬──────────┘
                   │
         ┌─────────▼──────────┐
         │     SERVICIO       │  ← Lógica de negocio
         │  ReportService     │    Operaciones complejas
         └─────────┬──────────┘
                   │
         ┌─────────▼──────────┐
         │   REPOSITORIO      │  ← Acceso a datos
         │ ServicioRepository │    Consultas a MySQL
         └─────────┬──────────┘
                   │
         ┌─────────▼──────────┐
         │   BASE DE DATOS    │
         │      MySQL         │
         └────────────────────┘
```

---

## 📂 ESTRUCTURA DE CARPETAS

```
carpentry/
├── src/main/java/com/syc/carpentry/
│   ├── model/              ← ENTIDADES (Tablas de BD)
│   │   ├── Usuario.java
│   │   ├── Servicio.java
│   │   ├── Proyecto.java
│   │   ├── MensajeContacto.java
│   │   └── PasswordResetToken.java
│   │
│   ├── repository/         ← REPOSITORIOS (Consultas a BD)
│   │   ├── UsuarioRepository.java
│   │   ├── ServicioRepository.java
│   │   ├── ProyectoRepository.java
│   │   └── ContactoRepository.java
│   │
│   ├── service/            ← SERVICIOS (Lógica de negocio)
│   │   ├── CustomUserDetailsService.java
│   │   ├── PasswordResetService.java
│   │   └── ReportService.java
│   │
│   ├── controller/         ← CONTROLADORES (Manejo de rutas)
│   │   ├── HomeController.java
│   │   ├── AdminController.java
│   │   ├── AuthController.java
│   │   ├── ContactoController.java
│   │   └── ReportController.java
│   │
│   └── config/             ← CONFIGURACIÓN
│       └── SecurityConfig.java
│
└── src/main/resources/
    ├── application.properties  ← Configuración de MySQL
    └── templates/              ← Vistas HTML (Thymeleaf)
        ├── index.html
        ├── auth/
        └── admin/
```

---

## 🔑 CONCEPTOS CLAVE DE SPRING BOOT

### 1. **ANOTACIONES (@) - ¿Qué son?**

Las anotaciones son "etiquetas" que le dicen a Spring cómo debe tratar una clase o método.

| Anotación | Significado | Ejemplo de uso |
|-----------|-------------|----------------|
| `@Entity` | Esta clase es una tabla de BD | `@Entity public class Usuario` |
| `@Controller` | Esta clase maneja rutas HTTP | `@Controller public class AdminController` |
| `@Service` | Esta clase tiene lógica de negocio | `@Service public class ReportService` |
| `@Repository` | Esta clase accede a la BD | `interface UsuarioRepository extends...` |
| `@Autowired` | Inyecta automáticamente una dependencia | `@Autowired private UsuarioRepository repo;` |
| `@GetMapping` | Ruta GET (leer/mostrar) | `@GetMapping("/admin/servicios")` |
| `@PostMapping` | Ruta POST (crear/enviar datos) | `@PostMapping("/login")` |

### 2. **INYECCIÓN DE DEPENDENCIAS**

En lugar de crear objetos con `new`, Spring los crea automáticamente:

```java
// ❌ SIN Spring (manual)
ServicioRepository repo = new ServicioRepository();

// ✅ CON Spring (automático)
@Autowired
private ServicioRepository repo; // Spring lo crea solo
```

**Ventaja**: Spring gestiona todo, tú solo usas los objetos.

### 3. **JPA (Java Persistence API)**

Convierte objetos Java ↔ Tablas MySQL automáticamente.

```java
// Guardas un objeto Java...
Servicio s = new Servicio();
s.setNombre("Muebles");
servicioRepository.save(s);

// ...y JPA ejecuta SQL automáticamente:
// INSERT INTO servicio (nombre) VALUES ('Muebles');
```

### 4. **THYMELEAF**

Motor de plantillas para generar HTML dinámico con datos de Java.

```html
<!-- En dashboard.html -->
<div th:text="${serviciosCount}">0</div>

<!-- Spring reemplaza automáticamente:
     ${serviciosCount} → valor real de la variable
-->
```

---

## 🗄️ BASE DE DATOS MYSQL

### Tablas principales:

1. **usuarios** - Usuarios del sistema (admin, clientes)
2. **servicio** - Servicios de carpintería
3. **proyecto** - Proyectos realizados
4. **mensaje_contacto** - Mensajes del formulario de contacto
5. **password_reset_tokens** - Tokens para recuperar contraseña

---

## 🔐 SEGURIDAD CON SPRING SECURITY

### ¿Cómo funciona el login?

```
1. Usuario ingresa: username="admin", password="admin"
   ↓
2. Spring Security busca en BD: UsuarioRepository.findByUsername("admin")
   ↓
3. Compara contraseña ingresada con la de BD
   ↓
4. Si coincide: Crea una SESIÓN (cookie JSESSIONID)
   ↓
5. Usuario accede al dashboard protegido
```

### Rutas protegidas (SecurityConfig.java):

- `/` → Pública (todos pueden ver)
- `/login` → Pública
- `/admin/**` → PROTEGIDA (solo usuarios con rol ADMIN)

---

## 📊 SISTEMA DE REPORTES

### Librerías usadas:

1. **Apache POI** → Genera archivos Excel (.xlsx)
2. **iText** → Genera archivos PDF

### Flujo de generación:

```
1. Usuario hace click: "Descargar PDF - Servicios - Mes"
   ↓
2. ReportController recibe: /admin/reportes/servicios/pdf?periodo=mes
   ↓
3. ReportService consulta BD: servicios del último mes
   ↓
4. Genera PDF con iText usando los datos
   ↓
5. Devuelve archivo descargable al navegador
```

---

## 🛠️ TECNOLOGÍAS UTILIZADAS

| Tecnología | Propósito | Versión |
|------------|-----------|---------|
| Java | Lenguaje de programación | 24 |
| Spring Boot | Framework web | 3.5.3 |
| MySQL | Base de datos | 8.x |
| Thymeleaf | Motor de plantillas HTML | - |
| Bootstrap | Framework CSS | 5.x |
| Apache POI | Generación de Excel | 5.2.5 |
| iText | Generación de PDF | 5.5.13 |
| Spring Security | Autenticación y autorización | 6.x |

---

## 🚀 ¿CÓMO FUNCIONA TODO JUNTO?

### Ejemplo: Crear un servicio desde el dashboard

```
1. VISTA (servicios.html)
   Usuario llena formulario: nombre="Muebles", descripción="..."
   ↓
2. NAVEGADOR
   Envía POST a /admin/servicios/guardar con los datos
   ↓
3. CONTROLADOR (AdminController)
   @PostMapping("/admin/servicios/guardar")
   public String guardarServicio(@ModelAttribute Servicio servicio)
   ↓
4. REPOSITORIO (ServicioRepository)
   servicioRepository.save(servicio);
   ↓
5. JPA + HIBERNATE
   Genera y ejecuta SQL: INSERT INTO servicio (nombre, descripcion) VALUES (...)
   ↓
6. MYSQL
   Guarda en la base de datos
   ↓
7. RESPUESTA
   Redirige al usuario: redirect:/admin/servicios
   ↓
8. VISTA ACTUALIZADA
   Muestra la tabla con el nuevo servicio incluido
```

---

## 💡 PREGUNTAS FRECUENTES PARA SUSTENTACIÓN

### P: ¿Qué es Spring Boot?
R: Es un framework de Java que simplifica la creación de aplicaciones web. Configura automáticamente muchas cosas (BD, servidor web, seguridad) para que te enfoques en programar tu lógica de negocio.

### P: ¿Por qué usas @Autowired?
R: Es inyección de dependencias. Spring crea y gestiona los objetos automáticamente. No tengo que usar `new` ni preocuparme por crear instancias.

### P: ¿Qué diferencia hay entre @GetMapping y @PostMapping?
R:
- GET: Para leer/mostrar datos (ej: ver lista de servicios)
- POST: Para enviar/crear datos (ej: guardar un servicio nuevo)

### P: ¿Cómo se conecta a MySQL?
R: En `application.properties` están las credenciales. Spring Boot + JPA usan estas para conectarse automáticamente. Hibernate genera las tablas si no existen.

### P: ¿Qué hace el @PrePersist en Servicio.java?
R: Es un método que se ejecuta automáticamente ANTES de guardar en BD. Lo uso para establecer la fecha de creación automáticamente sin tener que hacerlo manualmente cada vez.

### P: ¿Cómo funciona la autenticación?
R: Spring Security intercepta todas las peticiones. Si vas a una ruta protegida (/admin/**), verifica si tienes sesión activa. Si no, te redirige a /login.

### P: ¿Por qué hay Font de iText y Font de Apache POI?
R: Ambas librerías tienen una clase Font. Para evitar ambigüedad, uso nombres completos: `com.itextpdf.text.Font` para PDF y `org.apache.poi.ss.usermodel.Font` para Excel.

---

## 📝 CHECKLIST PARA SUSTENTACIÓN

✅ Entiendo qué es una Entidad (@Entity) y cómo se mapea a MySQL
✅ Sé qué hace un Repository y por qué extiende JpaRepository
✅ Comprendo el patrón MVC (Modelo-Vista-Controlador)
✅ Sé explicar el flujo completo de una petición HTTP
✅ Entiendo cómo funciona la inyección de dependencias
✅ Puedo explicar cómo Spring Security protege rutas
✅ Sé cómo se generan los reportes PDF y Excel
✅ Comprendo qué hace @PrePersist y otros métodos del ciclo de vida
✅ Puedo explicar la diferencia entre @GetMapping y @PostMapping
✅ Entiendo cómo Thymeleaf renderiza HTML con datos del backend

---

## 🎓 TIPS PARA LA SUSTENTACIÓN

1. **Demuestra el flujo completo**: "Cuando el usuario hace X, pasa por el controlador Y, luego el servicio Z, y finalmente se guarda en la tabla W"

2. **Explica las anotaciones**: No digas solo "@Controller", di "@Controller le indica a Spring que esta clase maneja rutas HTTP"

3. **Muestra que entiendes MVC**: "Separé el código en capas: Model para datos, View para presentación, Controller para lógica"

4. **Habla de buenas prácticas**: "Usé inyección de dependencias para desacoplar componentes y facilitar testing"

5. **Sé honesto**: Si usaste herramientas o te basaste en ejemplos, dilo. Lo importante es que ENTIENDAS el código final.

---

**Creado para sustentación SENA - Carpentry y Acabados SYC**
**Desarrollado con Spring Boot 3.5.3 + MySQL 8**
