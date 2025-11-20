# 🔐 GUÍA COMPLETA DE SPRING SECURITY

## 📌 ¿QUÉ ES SPRING SECURITY?

Spring Security es un **framework de seguridad** para aplicaciones Java que proporciona:

- ✅ **Autenticación**: Verificar quién es el usuario (login)
- ✅ **Autorización**: Verificar qué puede hacer el usuario (permisos)
- ✅ **Protección contra ataques**: CSRF, XSS, Session Fixation, etc.
- ✅ **Gestión de sesiones**: Cookies, tokens, remember-me

**En este proyecto se usa para:**
1. Sistema de login (usuario/contraseña)
2. Proteger el dashboard admin (solo usuarios con rol ADMIN)
3. Permitir acceso público a la página principal
4. Gestionar sesiones de usuario

---

## 🏗️ ARQUITECTURA DE SEGURIDAD

```
┌─────────────────────────────────────────────────────────┐
│              USUARIO INTENTA ACCEDER                    │
│               http://localhost:8080/admin/dashboard     │
└───────────────────────┬─────────────────────────────────┘
                        │
         ┌──────────────▼───────────────┐
         │   SPRING SECURITY FILTER     │
         │  (Intercepta TODAS las       │
         │   peticiones HTTP)           │
         └──────────────┬───────────────┘
                        │
           ┌────────────▼────────────┐
           │  ¿Usuario autenticado?  │
           │  (¿Tiene sesión?)       │
           └────┬───────────────┬────┘
                │ NO            │ SÍ
                │               │
        ┌───────▼─────┐   ┌────▼──────────────┐
        │ Redirigir a │   │ ¿Tiene permisos?  │
        │   /login    │   │ (¿Rol correcto?)  │
        └─────────────┘   └────┬──────────┬───┘
                               │ NO       │ SÍ
                               │          │
                        ┌──────▼────┐  ┌──▼────────┐
                        │ 403 Error │  │  ACCESO   │
                        │ Forbidden │  │ PERMITIDO │
                        └───────────┘  └───────────┘
```

---

## 📁 ARCHIVOS PRINCIPALES

### 1. SecurityConfig.java
**Ubicación**: `src/main/java/com/syc/carpentry/config/SecurityConfig.java`
**Propósito**: Configurar cómo funciona la seguridad

### 2. CustomUserDetailsService.java
**Ubicación**: `src/main/java/com/syc/carpentry/service/CustomUserDetailsService.java`
**Propósito**: Cargar usuarios desde MySQL para autenticación

### 3. PasswordResetService.java
**Ubicación**: `src/main/java/com/syc/carpentry/service/PasswordResetService.java`
**Propósito**: Gestionar recuperación de contraseña

---

## 🔧 SECURITYCONFIG.JAVA - EXPLICACIÓN LÍNEA POR LÍNEA

```java
@Configuration  // Le dice a Spring: "Esta clase tiene configuración"
public class SecurityConfig {
```

### 1️⃣ **Inyección del servicio de usuarios**

```java
@Autowired
private CustomUserDetailsService userDetailsService;
```
→ Spring inyecta el servicio que carga usuarios desde MySQL

### 2️⃣ **PasswordEncoder - Manejo de contraseñas**

```java
@Bean
@SuppressWarnings("deprecation")
public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
}
```

**¿Qué es un @Bean?**
- Un objeto que Spring gestiona y puede inyectar en otras clases
- Spring crea UNA sola instancia y la reutiliza (Singleton)

**NoOpPasswordEncoder**:
- "NoOp" = No Operation (sin operación)
- NO encripta contraseñas, las guarda en texto plano
- ⚠️ **Solo para desarrollo**: En producción deberías usar BCryptPasswordEncoder

**@SuppressWarnings("deprecation")**:
- NoOpPasswordEncoder está deprecado (no recomendado)
- Esta anotación oculta la advertencia del compilador

**¿Cómo funciona?**
```java
// Al guardar un usuario:
usuario.setPassword("admin");  // Se guarda tal cual en BD

// Al hacer login:
String passwordIngresada = "admin";
String passwordBD = "admin";
passwordEncoder.matches(passwordIngresada, passwordBD);  // true
```

### 3️⃣ **SecurityFilterChain - Configuración principal**

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
```

**HttpSecurity**: Objeto para configurar la seguridad HTTP

#### **A) Autorización de rutas**

```java
.authorizeHttpRequests(auth -> auth
    // Rutas públicas (sin autenticación)
    .requestMatchers("/", "/login", "/register", "/forgot-password", "/reset-password",
            "/api/**", "/styles.css", "/script.js", "/scripts.js",
            "/assets/**", "/public/**", "/servicios", "/proyectos", "/contacto", "/nosotros").permitAll()

    // Rutas del admin requieren autenticación y rol ADMIN
    .requestMatchers("/admin/**").hasRole("ADMIN")

    // Cualquier otra ruta requiere autenticación
    .anyRequest().authenticated()
)
```

**Explicación:**

| Método | Significado | Ejemplo |
|--------|-------------|---------|
| `requestMatchers("/")` | Define qué rutas se configuran | `/login`, `/admin/**` |
| `.permitAll()` | Permite acceso sin login | Cualquiera puede entrar |
| `.hasRole("ADMIN")` | Requiere rol específico | Solo usuarios con rol ADMIN |
| `.authenticated()` | Requiere estar logueado | Cualquier usuario autenticado |

**Ejemplos reales:**

```
✅ GET / → permitAll() → ACCESO PÚBLICO
✅ GET /servicios → permitAll() → ACCESO PÚBLICO
✅ GET /login → permitAll() → ACCESO PÚBLICO
❌ GET /admin/dashboard → hasRole("ADMIN") → REQUIERE LOGIN + ROL ADMIN
✅ POST /login → permitAll() → ACCESO PÚBLICO (para procesar login)
```

**Comodines:**
- `/api/**`: Cualquier ruta que empiece con `/api/`
  - `/api/servicios` ✅
  - `/api/usuarios/123` ✅
- `/admin/**`: Cualquier ruta bajo `/admin/`
  - `/admin/dashboard` ✅
  - `/admin/servicios/eliminar/5` ✅

#### **B) Configuración del formulario de login**

```java
.formLogin(form -> form
    .loginPage("/login")  // Página personalizada de login
    .loginProcessingUrl("/login")  // URL donde se procesa el login
    .defaultSuccessUrl("/admin/dashboard", true)  // Redirección después de login exitoso
    .failureUrl("/login?error=true")  // Redirección si falla el login
    .usernameParameter("username")  // Nombre del parámetro de usuario
    .passwordParameter("password")  // Nombre del parámetro de contraseña
    .permitAll()
)
```

**Explicación:**

| Configuración | Valor | Qué hace |
|---------------|-------|----------|
| `loginPage` | `/login` | Ruta de tu página de login HTML |
| `loginProcessingUrl` | `/login` | Spring intercepta POST a esta URL para autenticar |
| `defaultSuccessUrl` | `/admin/dashboard` | Redirige aquí si login es exitoso |
| `failureUrl` | `/login?error=true` | Redirige aquí si login falla |
| `usernameParameter` | `username` | Nombre del input en HTML: `<input name="username">` |
| `passwordParameter` | `password` | Nombre del input en HTML: `<input name="password">` |

**Formulario HTML correspondiente:**

```html
<form method="POST" action="/login">
    <input type="text" name="username" placeholder="Usuario">
    <input type="password" name="password" placeholder="Contraseña">
    <button type="submit">Iniciar Sesión</button>
</form>
```

#### **C) Configuración del logout**

```java
.logout(logout -> logout
    .logoutUrl("/logout")  // URL para cerrar sesión
    .logoutSuccessUrl("/login?logout=true")  // Redirección después de logout
    .invalidateHttpSession(true)  // Invalidar la sesión
    .deleteCookies("JSESSIONID")  // Eliminar cookies
    .permitAll()
)
```

**¿Qué hace cada opción?**

| Opción | Qué hace |
|--------|----------|
| `logoutUrl("/logout")` | POST a esta URL cierra sesión |
| `logoutSuccessUrl` | Redirige aquí después de logout |
| `invalidateHttpSession(true)` | Destruye la sesión del servidor |
| `deleteCookies("JSESSIONID")` | Elimina la cookie de sesión del navegador |

**Formulario de logout (en dashboard):**

```html
<form method="POST" action="/logout">
    <button type="submit">Cerrar Sesión</button>
</form>
```

#### **D) Protección CSRF**

```java
.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
```

**¿Qué es CSRF?**
- **Cross-Site Request Forgery**: Ataque donde un sitio malicioso envía peticiones en tu nombre
- Spring Security protege automáticamente todos los POST/PUT/DELETE

**¿Cómo funciona la protección?**
1. Spring genera un **token secreto** por sesión
2. Cada formulario debe incluir este token
3. Si el token no coincide → Spring rechaza la petición

**Thymeleaf lo hace automáticamente:**
```html
<form method="POST" th:action="@{/login}">
    <!-- Thymeleaf agrega automáticamente: -->
    <input type="hidden" name="_csrf" value="token-secreto-generado">
</form>
```

**ignoringRequestMatchers("/api/**")**:
- Desactiva CSRF para rutas `/api/**`
- Útil si tienes una API REST que usa tokens JWT en lugar de sesiones

### 4️⃣ **AuthenticationManager - Gestor de autenticación**

```java
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);

    authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

    return authenticationManagerBuilder.build();
}
```

**¿Qué hace este Bean?**

Configura CÓMO se autentican los usuarios:

1. **userDetailsService**: Servicio que carga usuarios desde BD
2. **passwordEncoder**: Cómo comparar contraseñas

**Flujo de autenticación:**
```
Usuario ingresa: username="admin", password="admin"
         ↓
AuthenticationManager usa userDetailsService para buscar usuario
         ↓
CustomUserDetailsService.loadUserByUsername("admin")
         ↓
Consulta MySQL: SELECT * FROM usuarios WHERE username = 'admin'
         ↓
Compara contraseñas: passwordEncoder.matches("admin", passwordBD)
         ↓
Si coincide → Autenticación exitosa → Crea sesión
Si no → AuthenticationException → Redirige a /login?error=true
```

---

## 👤 CUSTOMUSERDETAILSSERVICE.JAVA

**Ubicación**: `src/main/java/com/syc/carpentry/service/CustomUserDetailsService.java`

### Propósito

Conecta Spring Security con tu base de datos MySQL.

### Código explicado:

```java
@Service  // Le dice a Spring que es un servicio
public class CustomUserDetailsService implements UserDetailsService {
```

**UserDetailsService**: Interfaz de Spring Security que requiere implementar un método.

```java
@Autowired
private UsuarioRepository usuarioRepository;
```
→ Inyecta el repositorio para consultar usuarios en MySQL

```java
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
```

**UserDetails**: Interfaz de Spring Security que representa un usuario autenticado

**¿Cuándo se llama este método?**
- Cuando el usuario envía el formulario de login
- Spring Security llama automáticamente a `loadUserByUsername(username)`

### Paso a paso del método:

#### 1. Buscar usuario en BD

```java
Usuario usuario = usuarioRepository.findByUsername(username)
    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
```

**findByUsername(username)**:
- Consulta MySQL: `SELECT * FROM usuarios WHERE username = ?`
- Devuelve `Optional<Usuario>` (puede existir o no)

**orElseThrow()**:
- Si el usuario NO existe → Lanza excepción
- Spring Security captura la excepción → Redirige a `/login?error=true`

#### 2. Asignar roles/autoridades

```java
String rol = usuario.getRol();  // "ADMIN" o "USER"
List<GrantedAuthority> authorities = new ArrayList<>();

if ("ADMIN".equals(rol)) {
    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
} else {
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
}
```

**GrantedAuthority**: Representa un permiso o rol

**IMPORTANTE**: Spring Security requiere que los roles empiecen con "ROLE_"
- En BD guardas: `rol = "ADMIN"`
- Spring Security necesita: `ROLE_ADMIN`
- Por eso agregamos el prefijo "ROLE_"

#### 3. Devolver objeto User de Spring Security

```java
return new User(
    usuario.getUsername(),  // Nombre de usuario
    usuario.getPassword(),  // Contraseña (en texto plano)
    authorities             // Lista de roles
);
```

**User**: Clase de Spring Security (no es tu clase Usuario)

**Ejemplo completo:**
```java
Usuario usuario = {
    id: 1,
    nombre: "Juan Pérez",
    username: "admin",
    email: "admin@syc.com",
    password: "admin",
    rol: "ADMIN"
}

// Se convierte en:
User springUser = {
    username: "admin",
    password: "admin",
    authorities: ["ROLE_ADMIN"]
}
```

---

## 🔑 FLUJO COMPLETO DEL LOGIN

### Escenario: Usuario intenta iniciar sesión

```
┌─────────────────────────────────────────────────────┐
│ 1. Usuario abre: http://localhost:8080/login       │
└───────────────────────┬─────────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ AuthController.mostrarLogin()│
         │ return "auth/login";        │
         └──────────────┬──────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ Thymeleaf renderiza         │
         │ login.html                  │
         └──────────────┬──────────────┘
                        │
┌───────────────────────▼─────────────────────────────┐
│ 2. Usuario llena:                                   │
│    username: admin                                  │
│    password: admin                                  │
│    Click: "Iniciar Sesión"                          │
└───────────────────────┬─────────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ Navegador envía:            │
         │ POST /login                 │
         │ username=admin&password=admin│
         └──────────────┬──────────────┘
                        │
┌───────────────────────▼─────────────────────────────┐
│ 3. Spring Security intercepta POST /login          │
│    (configurado en SecurityConfig)                  │
└───────────────────────┬─────────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ AuthenticationManager       │
         │ llama a:                    │
         │ loadUserByUsername("admin") │
         └──────────────┬──────────────┘
                        │
┌───────────────────────▼─────────────────────────────┐
│ 4. CustomUserDetailsService                         │
│    Consulta MySQL:                                  │
│    SELECT * FROM usuarios WHERE username = 'admin'  │
└───────────────────────┬─────────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ Usuario encontrado:         │
         │ password en BD: "admin"     │
         │ rol: "ADMIN"                │
         └──────────────┬──────────────┘
                        │
┌───────────────────────▼─────────────────────────────┐
│ 5. PasswordEncoder compara contraseñas:             │
│    passwordEncoder.matches("admin", "admin")        │
│    Resultado: true ✅                                │
└───────────────────────┬─────────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ ✅ Autenticación EXITOSA     │
         │                             │
         │ Spring Security:            │
         │ - Crea sesión HTTP          │
         │ - Genera cookie JSESSIONID  │
         │ - Guarda usuario en sesión  │
         └──────────────┬──────────────┘
                        │
┌───────────────────────▼─────────────────────────────┐
│ 6. Redirige a:                                      │
│    http://localhost:8080/admin/dashboard            │
│    (defaultSuccessUrl configurado)                  │
└───────────────────────┬─────────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ Usuario ve el Dashboard     │
         │ ✅ Sesión activa             │
         └─────────────────────────────┘
```

### ¿Qué pasa si la contraseña es incorrecta?

```
┌───────────────────────────────────────────────────┐
│ Usuario ingresa: username=admin, password=MALO    │
└───────────────────────┬───────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ PasswordEncoder compara:    │
         │ matches("MALO", "admin")    │
         │ Resultado: false ❌          │
         └──────────────┬──────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ ❌ Autenticación FALLIDA     │
         │                             │
         │ Spring Security:            │
         │ - NO crea sesión            │
         │ - Redirige a failureUrl     │
         └──────────────┬──────────────┘
                        │
┌───────────────────────▼───────────────────────────┐
│ Redirige a:                                       │
│ http://localhost:8080/login?error=true            │
└───────────────────────┬───────────────────────────┘
                        │
         ┌──────────────▼──────────────┐
         │ Muestra mensaje de error:   │
         │ "Usuario o contraseña       │
         │  incorrectos"               │
         └─────────────────────────────┘
```

---

## 🍪 SESIONES Y COOKIES

### ¿Qué es una sesión?

Una **sesión** es un espacio de memoria en el servidor donde Spring guarda información del usuario autenticado.

```java
// Spring guarda esto en memoria:
Sesión {
    id: "A1B2C3D4E5F6...",
    usuario: "admin",
    roles: ["ROLE_ADMIN"],
    fechaCreacion: "2025-01-20 10:30:00",
    ultimoAcceso: "2025-01-20 10:35:00"
}
```

### ¿Qué es JSESSIONID?

**JSESSIONID** es una **cookie** que el navegador guarda con el ID de la sesión.

**Flujo:**
1. Usuario hace login exitoso
2. Spring crea sesión en servidor con ID único: `A1B2C3D4E5F6`
3. Spring envía cookie al navegador: `Set-Cookie: JSESSIONID=A1B2C3D4E5F6`
4. Navegador guarda la cookie
5. En cada petición, el navegador envía: `Cookie: JSESSIONID=A1B2C3D4E5F6`
6. Spring busca la sesión con ese ID
7. Si existe y es válida → Usuario autenticado ✅
8. Si no existe o expiró → Usuario sin autenticar ❌

### ¿Cuándo se elimina la sesión?

- **Logout**: Usuario hace logout → `invalidateHttpSession(true)` destruye la sesión
- **Timeout**: Inactividad (por defecto 30 minutos)
- **Reinicio del servidor**: Todas las sesiones se pierden

---

## 🔄 PASSWORDRESETSERVICE - Recuperación de contraseña

### Flujo completo:

```
┌─────────────────────────────────────────────────┐
│ 1. Usuario olvidó su contraseña                │
│    Hace click: "¿Olvidaste tu contraseña?"     │
└───────────────────┬─────────────────────────────┘
                    │
     ┌──────────────▼──────────────┐
     │ GET /forgot-password        │
     │ Muestra formulario          │
     └──────────────┬──────────────┘
                    │
┌───────────────────▼─────────────────────────────┐
│ 2. Usuario ingresa su email                    │
│    jhojangs27@gmail.com                         │
│    Click: "Enviar"                              │
└───────────────────┬─────────────────────────────┘
                    │
     ┌──────────────▼──────────────┐
     │ POST /forgot-password       │
     │ email=jhojangs27@gmail.com  │
     └──────────────┬──────────────┘
                    │
┌───────────────────▼─────────────────────────────┐
│ 3. PasswordResetService                         │
│    createPasswordResetToken(email)              │
│                                                 │
│    - Busca usuario por email en BD              │
│    - Genera token UUID aleatorio                │
│    - Guarda token en BD con expiración 24h     │
│    - Simula envío de email (imprime consola)   │
└───────────────────┬─────────────────────────────┘
                    │
     ┌──────────────▼──────────────┐
     │ Token generado:             │
     │ 8f4a9b2c-1d5e-4a3f-9c7b...  │
     │                             │
     │ Link impreso en consola:    │
     │ http://localhost:8080/      │
     │ reset-password?token=...    │
     └──────────────┬──────────────┘
                    │
┌───────────────────▼─────────────────────────────┐
│ 4. Usuario copia el link de la consola         │
│    y lo abre en el navegador                    │
└───────────────────┬─────────────────────────────┘
                    │
     ┌──────────────▼──────────────┐
     │ GET /reset-password?        │
     │ token=8f4a9b2c...           │
     └──────────────┬──────────────┘
                    │
┌───────────────────▼─────────────────────────────┐
│ 5. AuthController valida token                 │
│    validatePasswordResetToken(token)            │
│                                                 │
│    ✅ Token válido → Muestra formulario         │
│    ❌ Token inválido/expirado → Error           │
└───────────────────┬─────────────────────────────┘
                    │
┌───────────────────▼─────────────────────────────┐
│ 6. Usuario ingresa nueva contraseña            │
│    password: nuevaContraseña123                 │
│    confirmPassword: nuevaContraseña123          │
│    Click: "Restablecer"                         │
└───────────────────┬─────────────────────────────┘
                    │
     ┌──────────────▼──────────────┐
     │ POST /reset-password        │
     │ token=...&password=...      │
     └──────────────┬──────────────┘
                    │
┌───────────────────▼─────────────────────────────┐
│ 7. PasswordResetService                         │
│    resetPassword(token, newPassword)            │
│                                                 │
│    - Valida token nuevamente                    │
│    - Actualiza password del usuario             │
│    - Marca token como usado                     │
└───────────────────┬─────────────────────────────┘
                    │
     ┌──────────────▼──────────────┐
     │ ✅ Contraseña actualizada    │
     │ Redirige a /login           │
     └─────────────────────────────┘
```

---

## 💡 PREGUNTAS FRECUENTES PARA SUSTENTACIÓN

### P: ¿Qué es Spring Security?
**R**: Es un framework que agrega seguridad a aplicaciones Spring Boot. Maneja autenticación (login), autorización (permisos) y protección contra ataques web como CSRF.

### P: ¿Cómo protege Spring Security el dashboard?
**R**: Uso `requestMatchers("/admin/**").hasRole("ADMIN")` en SecurityConfig. Esto significa que solo usuarios autenticados con rol ADMIN pueden acceder a rutas que empiecen con /admin/.

### P: ¿Qué pasa cuando un usuario hace login?
**R**:
1. Spring Security llama a `loadUserByUsername()` de CustomUserDetailsService
2. Busco el usuario en MySQL con UsuarioRepository
3. Comparo la contraseña con PasswordEncoder
4. Si coincide, Spring crea una sesión HTTP y devuelve una cookie JSESSIONID
5. El navegador guarda la cookie y la envía en cada petición
6. Spring valida la sesión en cada petición

### P: ¿Qué es CSRF y cómo lo maneja tu proyecto?
**R**: CSRF (Cross-Site Request Forgery) es un ataque donde un sitio malicioso envía peticiones en nombre del usuario. Spring Security genera automáticamente un token secreto por sesión y lo incluye en cada formulario. Si el token no coincide, rechaza la petición. Thymeleaf lo agrega automáticamente con `th:action="@{/ruta}"`.

### P: ¿Por qué usas NoOpPasswordEncoder si está deprecado?
**R**: Por simplicidad en desarrollo. NoOpPasswordEncoder NO encripta contraseñas, las guarda en texto plano. En un proyecto real usaría BCryptPasswordEncoder que hashea las contraseñas para mayor seguridad.

### P: ¿Qué es UserDetailsService?
**R**: Es una interfaz de Spring Security que conecta la autenticación con mi base de datos. Implemento `loadUserByUsername()` para buscar usuarios en MySQL y devolverlos en un formato que Spring Security entiende.

### P: ¿Cómo funciona la recuperación de contraseña?
**R**: Genero un token UUID único, lo guardo en la tabla `password_reset_tokens` con expiración de 24 horas, y simulo el envío por email (imprimiendo el link en consola). El usuario usa ese link para acceder a un formulario donde ingresa su nueva contraseña. Valido el token antes de actualizar la contraseña y lo marco como usado para evitar reutilización.

### P: ¿Qué diferencia hay entre autenticación y autorización?
**R**:
- **Autenticación**: Verificar QUIÉN eres (login con usuario/contraseña)
- **Autorización**: Verificar QUÉ puedes hacer (permisos/roles)

Ejemplo: Un usuario con rol USER puede autenticarse (hacer login), pero no está autorizado a acceder a /admin/dashboard.

---

## 🎓 CONCEPTOS CLAVE

### 1. Filtros de Spring Security

Spring Security usa **filtros** que interceptan TODAS las peticiones HTTP:

```
Petición HTTP → Filter1 → Filter2 → Filter3 → ... → Controlador
```

Algunos filtros importantes:
- **UsernamePasswordAuthenticationFilter**: Procesa el login
- **LogoutFilter**: Procesa el logout
- **CsrfFilter**: Valida tokens CSRF
- **SessionManagementFilter**: Gestiona sesiones

### 2. Roles vs Authorities

En Spring Security son similares, pero por convención:

- **Role**: Grupo de permisos (ej: ADMIN, USER)
- **Authority**: Permiso específico (ej: READ_USERS, WRITE_PRODUCTS)

En tu proyecto solo usas roles simples: ADMIN y USER.

### 3. Seguridad en capas

```
1. Network: HTTPS (cifrar tráfico)
2. Authentication: Spring Security (verificar identidad)
3. Authorization: Roles (verificar permisos)
4. Session Management: Tokens/Cookies (mantener estado)
5. CSRF Protection: Tokens CSRF (prevenir ataques)
6. Input Validation: Validar datos de entrada
```

Tu proyecto implementa las capas 2, 3, 4 y 5.

---

**Creado para sustentación SENA**
**Guía completa de Spring Security**
