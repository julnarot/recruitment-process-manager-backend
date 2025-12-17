[Volver](../readme.MD#ejecución-en-docker)

## Ejecución en localhost (sin Docker)

### 1. Discovery Server

cd rpm-discovery

```bash
./mvnw spring-boot:run
```


Por defecto:

URL: http://localhost:8761

Eureka UI: http://localhost:8761

### 2. Gateway Service
cd rpm-gateway
```bash
./mvnw spring-boot:run
```


Variables de entorno recomendadas:
```bash
export SERVER_PORT=8080
export EUREKA_URL=http://localhost:8761/eureka
export JWT_SECRET=my-super-secret-key

```
Gateway:

URL: http://localhost:8080

### 3. Auth Service
cd rpm-auth-ms
```bash
./mvnw spring-boot:run
```


Variables de entorno:
```bash
export SERVER_PORT=8081
export EUREKA_URL=http://localhost:8761/eureka
export JWT_SECRET=my-super-secret-key

```
4. Customer Service
cd rpm-customer-ms
```bash
./mvnw spring-boot:run
```


Variables de entorno:
```bash
export SERVER_PORT=8082
export EUREKA_URL=http://localhost:8761/eureka
export DB_URL=jdbc:mysql://localhost:3306/rpm_customer
export DB_USER=appuser
export DB_PASSWORD=apppass

```
### 5. Verificación rápida

Eureka Dashboard
http://localhost:8761

Gateway health
http://localhost:8080/actuator/health

Swagger (vía Gateway)
http://localhost:8080/customers/swagger-ui/index.html


optional command for change to java 17

```bash

export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```
