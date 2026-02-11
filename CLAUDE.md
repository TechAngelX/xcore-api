# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

XCore Tasks API — a Spring Boot 4.0.2 REST backend for task management with JWT authentication, role-based access control (USER/ADMIN), and PostgreSQL persistence.

## Build & Run Commands

```bash
./mvnw spring-boot:run          # Run the application (default port 8080)
./mvnw test                     # Run all tests
./mvnw test -Dtest=ClassName    # Run a single test class
./mvnw test -Dtest=ClassName#methodName  # Run a single test method
./mvnw clean package            # Build JAR artifact
./mvnw clean install            # Full build with dependency resolution
```

## Environment Setup

Requires a `.env` file (or environment variables) with: `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`. The app reads these in `application.yml`. Hibernate `ddl-auto: update` handles schema creation automatically.

## Architecture

Layered architecture under `com.techangelx.xcore`:

- **`entity/`** — JPA entities (`User`, `Task`) with UUID primary keys, Lombok annotations, and `@CreationTimestamp` auditing. `Task` has a `@ManyToOne(LAZY)` relationship to `User`.
- **`dto/`** — Request/response objects with Bean Validation annotations. Separate from entities.
- **`repository/`** — Spring Data JPA interfaces (e.g., `UserRepository` with custom queries by email).
- **`security/`** — `JwtUtil` handles HS256 token generation/validation using jjwt. Tokens carry email (subject) and role claims with 24h expiration.
- **`config/`** — Spring `@Configuration` beans (e.g., `PasswordEncoderConfig` for BCrypt).
- **`controller/`** — REST endpoints (planned: `/auth/register`, `/auth/login`, `/api/tasks/**`).
- **`service/`** — Business logic layer (planned).
- **`exception/`** — Global exception handling (planned).

## Key Conventions

- Java 17+ with Lombok for boilerplate reduction (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`)
- Entities use `UUID` IDs (`GenerationType.UUID`) and enum columns stored as `EnumType.STRING`
- DTOs use `@Valid` / Bean Validation (`@NotBlank`, `@Email`, `@Size`) for input validation
- JWT secret and DB credentials are externalized via environment variables — never hardcoded
- Maven compiler plugin configured with Lombok annotation processor path

## API Design

- Auth endpoints: `POST /auth/register`, `POST /auth/login`
- Task CRUD: `GET/POST /api/tasks`, `PUT/DELETE /api/tasks/{id}`
- Users access only their own tasks; admins can access any user's tasks
