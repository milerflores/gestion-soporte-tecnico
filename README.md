# API de Gestión de Soporte Técnico

API RESTful desarrollada con Spring Boot para registrar, consultar, actualizar y eliminar solicitudes de soporte técnico, clientes y técnicos.

---

## Descripción

Este proyecto permite gestionar un sistema de soporte técnico con tres entidades principales:
- **Cliente** → persona que realiza la solicitud de soporte
- **Técnico** → profesional que atiende la solicitud
- **Solicitud** → registro del requerimiento de soporte, vincula a un cliente con un técnico

---
## Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17+ | Lenguaje principal |
| Spring Boot | 3.x | Framework principal |
| Spring Web | — | Creación de API REST |
| Lombok | — | Reducción de código boilerplate |
| Jakarta Validation | — | Validación de datos de entrada |
| SpringDoc OpenAPI (Swagger) | — | Documentación interactiva |

---

## Arquitectura

El proyecto sigue una **arquitectura en capas**:

```
Controller  →  Service  →  Repository  →  Almacenamiento en memoria (HashMap)
```

| Capa | Responsabilidad |
|---|---|
| **Controller** | Recibe peticiones HTTP y devuelve respuestas |
| **Service** | Contiene la lógica de negocio |
| **Repository** | Gestiona el almacenamiento y recuperación de datos |
| **Model** | Representa las entidades del dominio |
| **DTO** | Transporta y valida los datos de entrada |

> ⚠️ Los datos se almacenan en memoria (HashMap). Se pierden al reiniciar la aplicación.

---

## Endpoints Disponibles

### Clientes — `/v1/clientes`

| Método | Ruta | Descripción | Código Éxito | Código Error |
|---|---|---|---|---|
| POST | `/v1/clientes` | Crear cliente | 201 Created | 400 Bad Request |
| GET | `/v1/clientes` | Listar todos los clientes | 200 OK | — |
| GET | `/v1/clientes/{id}` | Buscar cliente por ID | 200 OK | 404 Not Found |
| PUT | `/v1/clientes/{id}` | Actualizar cliente | 200 OK | 404 / 400 |
| DELETE | `/v1/clientes/{id}` | Eliminar cliente | 200 OK | 404 Not Found |

### Técnicos — `/v1/tecnicos`

| Método | Ruta | Descripción | Código Éxito | Código Error |
|---|---|---|---|---|
| POST | `/v1/tecnicos` | Crear técnico | 201 Created | 400 Bad Request |
| GET | `/v1/tecnicos` | Listar todos los técnicos | 200 OK | — |
| GET | `/v1/tecnicos/{id}` | Buscar técnico por ID | 200 OK | 404 Not Found |
| PUT | `/v1/tecnicos/{id}` | Actualizar técnico | 200 OK | 404 / 400 |
| DELETE | `/v1/tecnicos/{id}` | Eliminar técnico | 200 OK | 404 Not Found |

### Solicitudes — `/v1/solicitudes`

| Método | Ruta | Descripción | Código Éxito | Código Error |
|---|---|---|---|---|
| POST | `/v1/solicitudes` | Crear solicitud | 201 Created | 400 Bad Request |
| GET | `/v1/solicitudes` | Listar todas las solicitudes | 200 OK | — |
| GET | `/v1/solicitudes/{id}` | Buscar solicitud por ID | 200 OK | 404 Not Found |
| PUT | `/v1/solicitudes/{id}` | Actualizar solicitud | 200 OK | 404 / 400 |
| DELETE | `/v1/solicitudes/{id}` | Eliminar solicitud | 200 OK | 404 Not Found |

---
## Ejemplos de Uso (Postman)

### Crear Cliente
```http
POST /v1/clientes
Content-Type: application/json

{
  "dni": "12345678",
  "nombres": "Juan",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "López",
  "edad": 25
}
```

**Respuesta 201:**
```json
{
  "idCliente": 1,
  "dni": "12345678",
  "nombres": "Juan",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "López",
  "fechaRegistro": "2025-01-01T10:00:00.000+00:00",
  "edad": 25
}
```

---

### Crear Técnico
```http
POST /v1/tecnicos
Content-Type: application/json

{
  "codigo": "TEC-001",
  "nombres": "Carlos",
  "apellidos": "García",
  "especialidad": "Redes",
  "email": "carlos@idat.pe",
  "edad": 30
}
```

---

### Crear Solicitud
```http
POST /v1/solicitudes
Content-Type: application/json

{
  "titulo": "Fallo en impresora",
  "descripcion": "La impresora no responde al enviar documentos",
  "estado": "PENDIENTE",
  "prioridad": "ALTA",
  "idTecnico": 1,
  "idCliente": 1
}
```

> ⚠️ El cliente y el técnico deben existir previamente antes de crear una solicitud.

---

## Validaciones

### ClienteDto

| Campo | Reglas |
|---|---|
| `dni` | Obligatorio, exactamente 8 dígitos numéricos |
| `nombres` | Obligatorio, entre 2 y 50 caracteres |
| `apellidoPaterno` | Obligatorio, entre 2 y 50 caracteres |
| `apellidoMaterno` | Obligatorio, entre 2 y 50 caracteres |
| `edad` | Entre 18 y 100 años |

### TecnicoDto

| Campo | Reglas |
|---|---|
| `codigo` | Obligatorio, formato `TEC-001` |
| `nombres` | Obligatorio, entre 2 y 50 caracteres |
| `apellidos` | Obligatorio, entre 2 y 50 caracteres |
| `especialidad` | Obligatorio, entre 2 y 50 caracteres |
| `email` | Obligatorio, formato válido de email |
| `edad` | Entre 18 y 65 años |

### SolicitudDto

| Campo | Reglas |
|---|---|
| `titulo` | Obligatorio, entre 5 y 100 caracteres |
| `descripcion` | Obligatorio, entre 10 y 500 caracteres |
| `estado` | Solo: `PENDIENTE`, `EN_PROCESO`, `RESUELTO` |
| `prioridad` | Solo: `BAJA`, `MEDIA`, `ALTA` |
| `idTecnico` | Obligatorio, número positivo |
| `idCliente` | Obligatorio, número positivo |

### Respuesta de error (400 Bad Request)
```json
{
  "fechaHora": "2025-01-01T10:00:00",
  "status": 400,
  "error": "Operación fallida",
  "mensaje": "dni: El DNI solo debe contener números | edad: La edad mínima es 18 años",
  "ruta": "uri=/v1/clientes"
}
```

---

## Documentación Swagger

Una vez levantada la aplicación, accede a la documentación interactiva en:

```
http://localhost:8080/swagger-ui/index.html
```

Desde Swagger puedes ver y probar todos los endpoints directamente desde el navegador.

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio
2. Abrir el proyecto en IntelliJ IDEA
3. Ejecutar `GestionApplication.java`
4. La API estará disponible en `http://localhost:8080`
