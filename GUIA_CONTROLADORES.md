# 🎮 GUÍA DETALLADA DE CONTROLADORES

## 📌 ¿QUÉ ES UN CONTROLADOR?

Un controlador es la **"puerta de entrada"** de tu aplicación web. Recibe las peticiones HTTP del navegador y decide qué hacer:

```
Usuario → HTTP Request → CONTROLADOR → Procesa → Devuelve HTML/JSON
```

---

## 🏢 ADMINCONTROLLER.JAVA

**Ubicación**: `src/main/java/com/syc/carpentry/controller/AdminController.java`

### 🎯 Propósito

Gestiona TODO el panel administrativo (dashboard):
- Mostrar estadísticas
- CRUD de servicios, proyectos, usuarios y contactos
- Generar datos para gráficos

### 📝 Anotaciones principales

```java
@Controller  // Le dice a Spring: "Esta clase maneja rutas HTTP"
@RequestMapping("/admin")  // Todas las rutas empiezan con /admin
public class AdminController {
```

**@RequestMapping("/admin")**: Prefijo para todas las rutas de esta clase
- `/admin/dashboard` → método dashboard()
- `/admin/servicios` → método servicios()
- `/admin/usuarios` → método usuarios()

### 💉 Inyección de Dependencias

```java
@Autowired private UsuarioRepository usuarioRepository;
@Autowired private ProyectoRepository proyectoRepository;
@Autowired private ServicioRepository servicioRepository;
@Autowired private ContactoRepository contactoRepository;
@Autowired private PasswordEncoder passwordEncoder;
```

**¿Qué hace @Autowired?**
- Spring CREA automáticamente estos objetos
- No necesitas hacer `new UsuarioRepository()`
- Spring los "inyecta" (los pasa) automáticamente

**¿Para qué sirven?**
- **Repositories**: Para consultar/guardar datos en MySQL
- **PasswordEncoder**: Para encriptar contraseñas (actualmente desactivado, usa texto plano)

---

## 🔍 MÉTODO: dashboard()

```java
@GetMapping("/dashboard")
public String dashboard(Model model) {
```

### ¿Qué hace este método?

1. **Ruta**: Responde a `GET /admin/dashboard`
2. **Parámetro Model**: Objeto para pasar datos a la vista HTML
3. **Return**: Nombre de la plantilla Thymeleaf ("admin/dashboard")

### Flujo paso a paso:

```java
// 1. Título de la página
model.addAttribute("pageTitle", "Dashboard - Admin SYC");
```
→ En dashboard.html puedes usar: `<title th:text="${pageTitle}"></title>`

```java
// 2. Contar registros en BD
model.addAttribute("serviciosCount", servicioRepository.count());
```
→ `count()` ejecuta: `SELECT COUNT(*) FROM servicio`
→ En HTML: `<div th:text="${serviciosCount}">0</div>`

```java
// 3. Obtener últimos 5 proyectos
model.addAttribute("proyectosRecientes", proyectoRepository.findTop5ByOrderByIdDesc());
```
→ Ejecuta: `SELECT * FROM proyecto ORDER BY id DESC LIMIT 5`
→ Devuelve una **List<Proyecto>** con los 5 proyectos más recientes

```java
// 4. Generar datos para gráficos de últimos 6 meses
List<String> mesesLabels = new ArrayList<>();  // ["Septiembre", "Octubre", ...]
List<Long> serviciosPorMes = new ArrayList<>();  // [5, 8, 12, ...]
```

**Bucle para 6 meses atrás:**

```java
for (int i = 5; i >= 0; i--) {
    LocalDate mesActual = hoy.minusMonths(i);  // Resta i meses
    // i=5: hace 5 meses
    // i=4: hace 4 meses
    // ...
    // i=0: mes actual
```

**Obtener nombre del mes en español:**

```java
String nombreMes = mesActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
nombreMes = nombreMes.substring(0, 1).toUpperCase() + nombreMes.substring(1);
// Resultado: "Septiembre", "Octubre", "Noviembre"...
```

**Contar servicios creados ese mes:**

```java
LocalDateTime inicioMes = inicioDeMes.atStartOfDay();  // 01/09/2024 00:00:00
LocalDateTime finMes = finDeMes.atStartOfDay();        // 01/10/2024 00:00:00
Long countServicios = servicioRepository.countByFechaCreacionBetween(inicioMes, finMes);
serviciosPorMes.add(countServicios != null ? countServicios : 0L);
```
→ Cuenta cuántos servicios se crearon entre esas fechas
→ Guarda el resultado en la lista

**Pasar a la vista:**

```java
model.addAttribute("mesesLabels", mesesLabels);
model.addAttribute("serviciosPorMes", serviciosPorMes);
// En dashboard.html con JavaScript:
// const labels = /*[[${mesesLabels}]]*/ [];
// const data = /*[[${serviciosPorMes}]]*/ [];
```

---

## 📋 CRUD DE SERVICIOS

### 1. **LISTAR** servicios (READ)

```java
@GetMapping("/servicios")
public String servicios(Model model) {
    model.addAttribute("servicios", servicioRepository.findAll());
    model.addAttribute("servicioNuevo", new Servicio());
    return "admin/servicios";
}
```

**¿Qué hace?**
- `findAll()`: Obtiene TODOS los servicios de la BD
- `new Servicio()`: Objeto vacío para el formulario de creación
- Devuelve la vista `servicios.html`

### 2. **CREAR/EDITAR** servicio (CREATE/UPDATE)

```java
@PostMapping("/servicios/guardar")
public String guardarServicio(@ModelAttribute Servicio servicio) {
    servicioRepository.save(servicio);
    return "redirect:/admin/servicios";
}
```

**@ModelAttribute**: Spring convierte automáticamente los datos del formulario HTML a un objeto Servicio

**Ejemplo**:
```html
<form method="POST" action="/admin/servicios/guardar">
    <input name="nombre" value="Muebles">
    <input name="descripcion" value="Fabricación">
</form>
```

→ Spring crea automáticamente:
```java
Servicio servicio = new Servicio();
servicio.setNombre("Muebles");
servicio.setDescripcion("Fabricación");
```

**save()**:
- Si el ID es null → INSERT (crear nuevo)
- Si el ID existe → UPDATE (actualizar)

**redirect**: Redirige al navegador a otra página (evita enviar formulario dos veces)

### 3. **ELIMINAR** servicio (DELETE)

```java
@GetMapping("/servicios/eliminar/{id}")
public String eliminarServicio(@PathVariable Long id) {
    servicioRepository.deleteById(id);
    return "redirect:/admin/servicios";
}
```

**@PathVariable**: Toma el valor de la URL

Ejemplo: `/admin/servicios/eliminar/5`
→ `id = 5`

**deleteById(id)**: Ejecuta `DELETE FROM servicio WHERE id = 5`

---

## 👥 CRUD DE USUARIOS

### Guardar usuario (con lógica especial)

```java
@PostMapping("/usuarios/guardar")
public String guardarUsuario(@ModelAttribute Usuario usuario) {
    // Si es actualización sin cambio de contraseña...
    if (usuario.getId() != null &&
        (usuario.getPassword() == null || usuario.getPassword().isEmpty())) {

        // Buscar usuario existente en BD
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getId()).orElse(null);

        if (usuarioExistente != null) {
            // Mantener la contraseña anterior
            usuario.setPassword(usuarioExistente.getPassword());
        }
    }
    // Guardar (contraseña en texto plano)
    usuarioRepository.save(usuario);
    return "redirect:/admin/usuarios";
}
```

**¿Por qué esta lógica?**

Cuando EDITAS un usuario, el formulario puede venir con la contraseña vacía:
- Si está vacía → Mantener la contraseña anterior
- Si tiene valor → Actualizar con la nueva

**findById(id).orElse(null)**:
- Busca el usuario con ese ID
- Si no existe, devuelve `null`

---

## 🎨 MODEL - Pasar datos a la vista

El objeto **Model** es como un "paquete" que le pasas a Thymeleaf:

```java
model.addAttribute("nombreVariable", valor);
```

En HTML (Thymeleaf):
```html
<div th:text="${nombreVariable}"></div>
```

**Ejemplo completo:**

```java
// En el controlador:
model.addAttribute("mensaje", "Hola Mundo");
model.addAttribute("numero", 42);

List<String> frutas = Arrays.asList("Manzana", "Pera");
model.addAttribute("frutas", frutas);
```

```html
<!-- En la vista HTML: -->
<p th:text="${mensaje}"></p>  <!-- Hola Mundo -->
<p th:text="${numero}"></p>    <!-- 42 -->

<ul>
    <li th:each="fruta : ${frutas}" th:text="${fruta}"></li>
    <!-- <li>Manzana</li> -->
    <!-- <li>Pera</li> -->
</ul>
```

---

## 🔁 DIFERENCIA ENTRE GET Y POST

| Aspecto | GET | POST |
|---------|-----|------|
| **Propósito** | Leer/Obtener datos | Crear/Modificar datos |
| **URL visible** | Sí (parámetros en URL) | No (datos en body) |
| **Ejemplo** | `/servicios?id=5` | `/servicios/guardar` |
| **Cacheable** | Sí | No |
| **Idempotente** | Sí (repetir no cambia nada) | No (repetir crea duplicados) |
| **Uso típico** | Ver lista, ver detalle | Guardar formulario, eliminar |

---

## 🚀 FLUJO COMPLETO - Ejemplo Real

**Usuario quiere crear un servicio nuevo**

1. **Usuario** visita: `http://localhost:8080/admin/servicios`

2. **Navegador** envía: `GET /admin/servicios`

3. **AdminController** ejecuta método `servicios()`:
   ```java
   @GetMapping("/servicios")
   public String servicios(Model model) {
       model.addAttribute("servicios", servicioRepository.findAll());
       return "admin/servicios";
   }
   ```

4. **Repository** consulta MySQL:
   ```sql
   SELECT * FROM servicio
   ```

5. **Thymeleaf** renderiza `servicios.html` con los datos

6. **Usuario** ve la lista y llena el formulario:
   - Nombre: "Muebles de cocina"
   - Descripción: "Fabricación e instalación"

7. **Usuario** hace click en "Guardar"

8. **Navegador** envía: `POST /admin/servicios/guardar`
   ```
   nombre=Muebles%20de%20cocina&descripcion=Fabricación%20e%20instalación
   ```

9. **AdminController** ejecuta método `guardarServicio()`:
   ```java
   @PostMapping("/servicios/guardar")
   public String guardarServicio(@ModelAttribute Servicio servicio) {
       servicioRepository.save(servicio);  // Guarda en MySQL
       return "redirect:/admin/servicios";  // Redirige
   }
   ```

10. **JPA/Hibernate** ejecuta SQL:
    ```sql
    INSERT INTO servicio (nombre, descripcion, fecha_creacion)
    VALUES ('Muebles de cocina', 'Fabricación e instalación', NOW())
    ```

11. **Navegador** es redirigido a `/admin/servicios` (paso 2)

12. **Usuario** ve la lista actualizada con el nuevo servicio

---

## 💡 PREGUNTAS FRECUENTES

### P: ¿Por qué usar @Autowired en lugar de new?

**Sin @Autowired (manual):**
```java
ServicioRepository repo = new ServicioRepository();
// ❌ Error: ServicioRepository es una interfaz, no puedes hacer new
// ❌ Tendrías que crear tú mismo la conexión a MySQL
// ❌ Tendrías que configurar Hibernate manualmente
```

**Con @Autowired (automático):**
```java
@Autowired
private ServicioRepository repo;
// ✅ Spring crea el objeto automáticamente
// ✅ Spring configura la conexión a MySQL
// ✅ Spring configura Hibernate
```

### P: ¿Qué es el patrón Repository?

Es un patrón de diseño que **separa la lógica de acceso a datos** del resto de la aplicación.

**Sin Repository:**
```java
// En el controlador tendrías que escribir SQL:
Connection conn = DriverManager.getConnection(...);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM servicio");
// ...código tedioso...
```

**Con Repository:**
```java
// Spring Data JPA hace todo por ti:
List<Servicio> servicios = servicioRepository.findAll();
```

### P: ¿Por qué return "redirect:/admin/servicios"?

**Sin redirect:**
```java
return "admin/servicios";  // Renderiza la vista directamente
```
→ Si el usuario recarga la página (F5), **se reenvía el formulario** → Duplica el registro

**Con redirect:**
```java
return "redirect:/admin/servicios";  // Redirige a una nueva petición GET
```
→ El navegador hace una nueva petición GET → No se reenvía el formulario → No se duplica

**Patrón PRG (Post-Redirect-Get)**: Buena práctica para evitar duplicados

---

## 📊 ANALOGÍA SIMPLE

Imagina un **restaurante**:

- **Controlador** = **Mesero**: Recibe pedidos, los procesa y trae la comida
- **Repository** = **Cocina**: Prepara los datos (comida) desde la BD (despensa)
- **Model** = **Bandeja**: Lleva los datos del controlador a la vista
- **Vista (HTML)** = **Mesa**: Donde el cliente (usuario) ve el resultado

**Flujo:**
1. Cliente (usuario) pide un plato (hace click)
2. Mesero (controlador) recibe el pedido
3. Mesero le dice a la cocina (repository) qué preparar
4. Cocina busca ingredientes en la despensa (MySQL)
5. Cocina prepara el plato (consulta los datos)
6. Mesero pone el plato en la bandeja (Model)
7. Mesero lleva la bandeja a la mesa (vista HTML)
8. Cliente ve y disfruta el plato (ve los datos en la página)

---

**Creado para sustentación SENA**
**Guía educativa de controladores Spring Boot**
