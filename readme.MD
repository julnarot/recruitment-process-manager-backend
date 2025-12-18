# RPM Microservices Platform

Este repositorio contiene un ecosistema de microservicios basado en Spring Boot, Spring Cloud Gateway, Eureka Discovery Server y JWT, diseñado para ejecutarse tanto en entorno local como en Docker.


## Requisitos

### Ejecución local

- Java 17+
- Maven
- MySQL 8 (si no usas Docker para la DB)

### Ejecución en Docker

- Docker

- Docker Compose

## Levantar entorno local (Docker)

### 1. Construir y levantar todo el ecosistema

Desde la raíz del proyecto:

```bash
docker-compose up -d --build
```

> [Levantar entorno local `mvnw`](doc/runLocal.MD)


Servicios incluidos:

| Servicio         | URL                                                                |
| ---------------- | ------------------------------------------------------------------ |
| Gateway          | [http://localhost:8080](http://localhost:8080)                     |
| Auth Service     | [http://localhost:8080/auth](http://localhost:8080/auth)           |
| Customer Service | [http://localhost:8080/customers](http://localhost:8080/customers) |
| Discovery        | [http://localhost:9000](http://localhost:9000)                     |
| MySQL            | localhost:3306                                                     |




## Revisar las instancias de microservicios registrados en eureka
 use comando  curl para listar los servicios: `curl --location 'http://localhost:8761/eureka/apps'`

Ejemplo  visualizando nombre y estado de instancia en formato `json`
```bash
curl -H "Accept: application/json" http://localhost:8761/eureka/apps | jq '.applications.application[] | {name: .name, instances: [.instance[] | {id: .instanceId, status: .status}]}'

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  3006  100  3006    0     0   358k      0 --:--:-- --:--:-- --:--:--  366k
{
  "name": "CUSTOMER-SERVICE",
  "instances": [
    {
      "id": "6092468a39da:customer-service:80",
      "status": "UP"
    }
  ]
}
{
  "name": "RPM-GATEWAY",
  "instances": [
    {
      "id": "b7d245af9be0:rpm-gateway:8080",
      "status": "UP"
    }
  ]
}
{
  "name": "AUTH-SERVICE",
  "instances": [
    {
      "id": "a949e541aad2:auth-service:80",
      "status": "UP"
    }
  ]
}
```

> Para pruebas de funcionalidad completa revise la seccion [Pruebas de funcionalidad interna microservicios curl](doc/pruebas-endpoints-base.md)


### Postman Collection

Este repositorio incluye una colección de Postman con todos los endpoints configurados (Auth, Customers y Metrics).


Puedes encontrarla en: `doc/postman/PostmanCollection.zip`

Extraiga el contenido de PostmanCollection e importelos en su workspace Postman, active el entorno que deseas usar como `local`(preferentemente) o `production` no olvide empezar con endpoint de inicio de sesion `/auth/login` atravez de la peticion `login` y este  automaticamente fijara variable `JWT` requerido para acceder a los demas endpoints.
