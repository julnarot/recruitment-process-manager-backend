# RPM Microservices Platform

Este repositorio contiene un ecosistema de microservicios basado en Spring Boot, Spring Cloud Gateway, Eureka Discovery Server y JWT, diseñado para ejecutarse tanto en entorno local como en Docker.

## Arquitectura

```mermaid
sequenceDiagram
    participant Client
    participant Gateway
    participant AuthService
    participant CustomerService

    Client->>Gateway: POST /auth/login
    Gateway->>AuthService: Forward login request
    AuthService-->>Gateway: JWT Token
    Gateway-->>Client: JWT Token

    Client->>Gateway: GET /customers/**
    Gateway->>Gateway: Validate JWT
    Gateway->>CustomerService: Forward request with headers
    CustomerService-->>Gateway: Response
    Gateway-->>Client: Response
```

Servicios incluidos:

| Servicio         | URL                                                                |
| ---------------- | ------------------------------------------------------------------ |
| Gateway          | [http://localhost:8080](http://localhost:8080)                     |
| Auth Service     | [http://localhost:8080/auth](http://localhost:8080/auth)           |
| Customer Service | [http://localhost:8080/customers](http://localhost:8080/customers) |
| Discovery        | [http://localhost:9000](http://localhost:9000)                     |
| MySQL            | localhost:3306                                                     |

## Requisitos

### Para ejecución local

- Java 17+
- Maven
- MySQL 8 (si no usas Docker para la DB)

### Para Docker

- Docker

- Docker Compose

## Ejecución con Docker

### 1. Construir y levantar todo el ecosistema

Desde la raíz del proyecto:

```bash
docker-compose up -d --build
```

### 3. Apagar el entorno

```bash
docker-compose down
```

Para eliminar volúmenes:

```bash
docker-compose down -v

```

> [local run](runLocal.MD)




## Pruebas rápidas con curl

Esta sección muestra cómo obtener un JWT desde el servicio de autenticación y cómo consumir los endpoints protegidos a través del API Gateway.

### 1. Obtener token JWT

Realiza el login contra el Gateway (que enruta al Auth Service):
```bash
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
  "username": "admin",
  "password": "admin123"
}'
```

Respuesta esperada:
```bash
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NTc2NTE3NywiZXhwIjoxNzY1NzY4Nzc3fQ.MOGGe6vxME1XviF4-r4MmqDdeLwRRDHvFjurzInIBK4"
}
```


Guarda el valor del campo token, ya que será utilizado en las siguientes peticiones.

### 2. Obtener lista de clientes (GET)
```bash
curl --location 'http://localhost:8080/customers' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NTc2NTE3NywiZXhwIjoxNzY1NzY4Nzc3fQ.MOGGe6vxME1XviF4-r4MmqDdeLwRRDHvFjurzInIBK4'
```

### 3. Crear un cliente (POST)
```bash
curl --location 'http://localhost:8080/customers' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NTc2NTE3NywiZXhwIjoxNzY1NzY4Nzc3fQ.MOGGe6vxME1XviF4-r4MmqDdeLwRRDHvFjurzInIBK4' \
--data '{
  "firstName": "example",
  "lastName": "test last name",
  "age": 34,
  "birthDate": "1991-07-19"
}'

```
### 4. Consultar métricas del servicio Customer
```bash
curl --location 'http://localhost:8080/customers/metrics' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NTc2NTE3NywiZXhwIjoxNzY1NzY4Nzc3fQ.MOGGe6vxME1XviF4-r4MmqDdeLwRRDHvFjurzInIBK4'

```
⚠️ Notas importantes

Todas las peticiones deben pasar por el Gateway (localhost:8080)

Los microservicios no están expuestos directamente para consumo externo

Si el token es inválido o no se envía, el Gateway responderá:
```bash
{
  "status": 401,
  "error": "UNAUTHORIZED",
  "message": "Invalid or missing JWT token",
  "path": "/customers/..."
}
```

### Postman Collection

Este repositorio incluye una colección de Postman con todos los endpoints configurados (Auth, Customers y Metrics).

Puedes encontrarla en: `rpm-microservices.postman_collection.json`

Solo impórtala en Postman y activa el entorno que deseas usar como `local` o `production` no olvide llaamr a al request `/auth/login` atravez de la peticion `login` y este  automaticamente fijara variable {{JWT}} con para usar los demas endpoints.

