[Volver](../readme.MD#revisar-las-instancias-de-microservicios-registrados-en-eureka)
# Pruebas de funcionalidad interna microservicios curl
Esta sección muestra cómo obtener un JWT desde el servicio de autenticación y cómo consumir los endpoints protegidos a través del API Gateway.


Diagrama de secuencia :


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
  "age": 12,
  "birthDate":"2013-06-12"
}'

```
### 4. Consultar métricas del servicio Customer
```bash
curl --location 'http://localhost:8080/customers/metrics' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NTc2NTE3NywiZXhwIjoxNzY1NzY4Nzc3fQ.MOGGe6vxME1XviF4-r4MmqDdeLwRRDHvFjurzInIBK4'

```

Si el token es inválido o no se envía, el Gateway responderá:
```bash
{
  "status": 401,
  "error": "UNAUTHORIZED",
  "message": "Invalid or missing JWT token",
  "path": "/customers/..."
}
```