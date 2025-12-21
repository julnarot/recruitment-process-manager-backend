# Customer microservice

## Architecture

This service follows a Hexagonal Architecture (Ports & Adapters) approach, applied in a pragmatic way using Spring Boot.

The main goal is to isolate business logic from infrastructure and frameworks, improving testability, maintainability, and long-term evolution.

Architecture diagram (basic)

```bash
              ┌─────────────────────┐
              │        Web          │
              │  (REST Controllers) │
              └─────────┬───────────┘
                        │
                        ▼
              ┌─────────────────────┐
              │     Application     │
              │  - Services         │
              │  - DTOs             │
              │  - Validation       │
              │  - Ports (OUT)      │
              └─────────┬───────────┘
                        │
          ┌─────────────┴─────────────┐
          ▼                           ▼
┌───────────────────┐     ┌───────────────────┐
│   Infrastructure  │     │   Infrastructure  │
│   JPA Adapters    │     │   Other Adapters  │
│   (Persistence)   │     │   (future)        │
└───────────────────┘     └───────────────────┘
                        ▲
                        │
              ┌─────────────────────┐
              │        Domain       │
              │  - Entities         │
              │  - Core model       │
              └─────────────────────┘
```

## Package structure and responsibilities

### domain

Represents the core business model.

- Contains entities and core concepts

- No dependencies on Spring or infrastructure

- Pure Java

- This layer is the most stable part of the system.

### application

Coordinates use cases and business workflows.

- service: application services (transaction boundaries)

- dto: input/output contracts

- port.out: interfaces used to access infrastructure

- mapper: DTO ↔ domain transformations

- validation: input validation rules

> This layer orchestrates the domain without knowing technical details.

### infrastructure

Contains technical implementations of application ports.

- persistence: JPA adapters, entities, repositories

- config: framework and tooling configuration (OpenAPI, etc.)

- Infrastructure depends on the application, never the opposite.

### web

Delivery mechanism for HTTP.

- REST controllers

- Global exception handling

- Maps HTTP requests/responses to application DTOs

> This layer translates the outside world into application input.

## Ports & Adapters (example)

- Port (OUT): CustomerRepositoryPort

- Adapter: CustomerJpaAdapter

> This allows replacing persistence mechanisms without affecting business logic.
