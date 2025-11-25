# 🏢 ESTRUCTURA ORGANIZACIONAL
## Carpentry y Acabados SYC

---

## 📊 ORGANIGRAMA

El organigrama del proyecto se estructura en **3 niveles jerárquicos**:

### 🔵 Nivel Directivo (Estratégico)
- **Gerente General**: Toma decisiones estratégicas y supervisa todas las áreas

### 🟡 Nivel Táctico (Mandos Medios)
- **Jefe de Operaciones**: Coordina proyectos de carpintería
- **Jefe de Sistemas**: Administra tecnología y desarrollo web
- **Jefe Comercial**: Gestiona ventas y marketing

### 🟢 Nivel Operativo (Ejecución)
- **Personal técnico**: Maestros carpinteros y ayudantes
- **Personal de sistemas**: Desarrolladores y soporte TI
- **Personal comercial**: Vendedores y community managers

---

## 👥 DESCRIPCIÓN DE ROLES Y RESPONSABILIDADES

### 🔷 NIVEL DIRECTIVO

#### 👔 Gerente General - Sebastián Yepes Castro (SYC)
**Responsabilidades:**
- Definir la visión y estrategia de la empresa
- Supervisar todas las áreas de la organización
- Tomar decisiones de inversión y crecimiento
- Aprobar proyectos mayores
- Gestionar relaciones con clientes importantes

**Relación con el sistema:**
- Usuario con rol **ADMIN** en el sistema
- Acceso completo al dashboard
- Visualiza reportes y estadísticas

---

### 🔶 NIVEL TÁCTICO

#### 🔧 Jefe de Operaciones
**Responsabilidades:**
- Planificar y coordinar proyectos de carpintería
- Asignar personal a cada proyecto
- Supervisar calidad de trabajos
- Gestionar inventario de materiales
- Coordinar con proveedores

**Relación con el sistema:**
- Usuario con rol **ADMIN**
- Gestiona módulo de **Proyectos**
- Actualiza estados de proyectos (Activo, Completado, En Proceso)

---

#### 💻 Jefe de Sistemas / TI
**Responsabilidades:**
- Administrar el sitio web y sistema de gestión
- Coordinar mantenimiento y actualizaciones
- Garantizar seguridad de la información
- Gestionar hosting y dominio
- Supervisar backups de base de datos

**Relación con el sistema:**
- Usuario con rol **ADMIN**
- Acceso a gestión de **Usuarios**
- Administra infraestructura técnica
- Desarrolló/mantiene el sistema Spring Boot

---

#### 📈 Jefe Comercial
**Responsabilidades:**
- Definir estrategias de ventas
- Gestionar campañas de marketing
- Analizar mercado y competencia
- Establecer metas comerciales
- Supervisar atención al cliente

**Relación con el sistema:**
- Usuario con rol **ADMIN**
- Gestiona módulo de **Servicios**
- Revisa **Mensajes de Contacto**
- Analiza estadísticas del dashboard

---

### 🔷 NIVEL OPERATIVO

#### 👷 Maestro Carpintero
**Responsabilidades:**
- Ejecutar proyectos de carpintería
- Supervisar ayudantes
- Garantizar calidad del trabajo
- Reportar avances al Jefe de Operaciones
- Mantener herramientas y equipos

**Relación con el sistema:**
- Usuario con rol **USER** (opcional)
- Consulta detalles de proyectos asignados

---

#### 👷 Ayudante de Carpintería
**Responsabilidades:**
- Asistir al maestro carpintero
- Preparar materiales y herramientas
- Realizar tareas bajo supervisión
- Mantener orden en obra
- Aprender técnicas de carpintería

**Relación con el sistema:**
- Sin acceso al sistema (trabajo de campo)

---

#### 👨‍💻 Desarrollador Web
**Responsabilidades:**
- Desarrollar y mantener el sitio web
- Implementar nuevas funcionalidades
- Corregir errores (bugs)
- Optimizar rendimiento
- Documentar código

**Relación con el sistema:**
- **Creador del sistema**
- Acceso a código fuente
- Gestiona base de datos
- Implementó arquitectura Spring Boot + MySQL

**Proyecto desarrollado:**
- Framework: Spring Boot 3.5.3
- Base de datos: MySQL 8
- Frontend: Thymeleaf + Bootstrap 5
- Total: 14 clases Java, 22 plantillas HTML

---

#### 🖥️ Soporte TI
**Responsabilidades:**
- Resolver problemas técnicos
- Configurar equipos
- Hacer backups periódicos
- Monitorear servidores
- Asistir a usuarios del sistema

**Relación con el sistema:**
- Usuario con rol **ADMIN** (limitado)
- Realiza backups de base de datos
- Monitorea logs del servidor

---

#### 🤝 Asesor Comercial / Vendedor
**Responsabilidades:**
- Atender consultas de clientes
- Cotizar proyectos
- Realizar seguimiento de leads
- Cerrar ventas
- Mantener relación con clientes

**Relación con el sistema:**
- Usuario con rol **USER**
- Revisa **Mensajes de Contacto**
- Consulta catálogo de **Servicios**
- No puede modificar información

---

#### 📱 Community Manager
**Responsabilidades:**
- Gestionar redes sociales
- Crear contenido (fotos, videos)
- Responder comentarios y mensajes
- Promover servicios online
- Analizar métricas de engagement

**Relación con el sistema:**
- Usuario con rol **USER**
- Consulta **Proyectos** para publicar en redes
- Descarga imágenes de servicios
- Puede sugerir contenido nuevo

---

## 📋 RESUMEN DE ACCESOS AL SISTEMA

| Rol | Acceso Sistema | Permisos |
|-----|----------------|----------|
| **Gerente General** | ✅ ADMIN | Dashboard, Servicios, Proyectos, Usuarios, Mensajes |
| **Jefe de Operaciones** | ✅ ADMIN | Dashboard, Proyectos, Mensajes |
| **Jefe de Sistemas** | ✅ ADMIN | Todos los módulos + Configuración |
| **Jefe Comercial** | ✅ ADMIN | Dashboard, Servicios, Mensajes |
| **Desarrollador Web** | ✅ SUPER ADMIN | Código fuente + Base de datos |
| **Soporte TI** | ✅ ADMIN | Backups, Logs, Configuración |
| **Asesor Comercial** | ✅ USER | Solo lectura: Servicios, Mensajes |
| **Community Manager** | ✅ USER | Solo lectura: Servicios, Proyectos |
| **Maestro Carpintero** | ⚠️ Opcional | Solo lectura: Proyectos asignados |
| **Ayudante** | ❌ No | Trabajo de campo |

---

## 🔄 FLUJO DE TRABAJO EN LA ORGANIZACIÓN

### 1️⃣ Cliente envía mensaje de contacto
```
Cliente → Formulario Web → Sistema → Jefe Comercial
                                    ↓
                            Asesor Comercial
                                    ↓
                            Cotización y Seguimiento
```

### 2️⃣ Se acepta un proyecto
```
Jefe Comercial → Jefe de Operaciones → Maestro Carpintero
                       ↓
               Registra en Sistema
                  (Módulo Proyectos)
```

### 3️⃣ Se actualiza el sitio web
```
Jefe Comercial → Nuevo servicio → Desarrollador Web
                                          ↓
                                  Sube al Sistema
                                          ↓
                              Community Manager publica
```

### 4️⃣ Mantenimiento del sistema
```
Problema detectado → Jefe de Sistemas → Desarrollador Web
                                              ↓
                                       Corrige y actualiza
                                              ↓
                                    Soporte TI verifica
```

---

## 👨‍👩‍👧‍👦 TAMAÑO DE LA EMPRESA

**Total de empleados:** 10 personas

### Distribución:
- 🔵 Nivel Directivo: 1 persona (10%)
- 🟡 Nivel Táctico: 3 personas (30%)
- 🟢 Nivel Operativo: 6 personas (60%)

### Por área:
- **Operaciones (Carpintería):** 3 personas (30%)
- **Sistemas/TI:** 2 personas (20%)
- **Comercial:** 2 personas (20%)
- **Dirección:** 1 persona (10%)
- **Soporte general:** 2 personas (20%)

---

## 💼 PERFIL DE PUESTOS

### Requisitos para Desarrollador Web (Enfoque académico)

**Estudiante/Proyecto Final de:**
- Ingeniería de Sistemas
- Desarrollo de Software
- Tecnologías de la Información

**Conocimientos técnicos:**
- ✅ Java 17+
- ✅ Spring Boot
- ✅ Spring Data JPA
- ✅ MySQL
- ✅ HTML, CSS, JavaScript
- ✅ Bootstrap
- ✅ Git/GitHub
- ✅ Maven

**Proyecto realizado:**
- Sistema web fullstack
- Arquitectura MVC
- CRUD completo
- Panel administrativo
- Sitio web público responsivo

---

## 📊 ORGANIGRAMA ALTERNATIVO (Muy Simple)

Si prefieres un organigrama AÚN MÁS SIMPLE:

```
                    Gerente General
                          |
        +-----------------+-----------------+
        |                 |                 |
   Operaciones        Sistemas          Comercial
        |                 |                 |
    Carpinteros    Desarrollador       Vendedores
```

---

## 📁 ARCHIVOS GENERADOS

Tienes 2 versiones del organigrama:

1. **ORGANIGRAMA_SIMPLE.mmd** - Versión con 10 personas (detallado)
2. Este documento - Descripción completa de roles

---

## 🎨 CÓMO USAR EL ORGANIGRAMA

### Renderizar en Mermaid Live:
1. Ve a https://mermaid.live/
2. Copia el contenido de `ORGANIGRAMA_SIMPLE.mmd`
3. Verás el organigrama con colores
4. Exporta como PNG

### Colores en el diagrama:
- 🔵 **Azul** = Gerente General (Directivo)
- 🟠 **Naranja** = Jefe de Operaciones
- 🟣 **Morado** = Jefe de Sistemas
- 🟢 **Verde** = Jefe Comercial
- ⚪ **Blanco/Claro** = Nivel operativo

---

## 💡 CONSEJO PARA TU DOCUMENTO

En tu proyecto final, puedes incluir:

1. **El organigrama visual** (imagen exportada de Mermaid)
2. **La tabla de roles y responsabilidades**
3. **El cuadro de accesos al sistema**

Esto le da un contexto profesional a tu proyecto y muestra que pensaste en la organización real de una empresa.

---

**¿Necesitas modificar algo?**
- ¿Agregar/quitar roles?
- ¿Cambiar nombres?
- ¿Simplificar más?

¡Dime y lo ajusto! 🚀
