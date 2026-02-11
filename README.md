# XCore Tasks API

XCore Tasks API is a secure, production-style RESTful backend service built with Spring Boot.
It demonstrates modern backend development practices, including JWT authentication, role-based access control, and relational data modeling with PostgreSQL.

This project is designed as a demonstration of professional backend engineering skills, showcasing clean architecture, secure authentication, and deployment-ready configuration.

---

## Features

- User registration and login with JWT authentication
- Role-based access control (USER and ADMIN)
- CRUD operations for tasks, scoped per user
- PostgreSQL database integration for persistent storage
- Input validation using Spring's `@Valid` annotations
- Global exception handling for standardized API responses
- Pagination support for task listings
- Docker-ready configuration for consistent deployment

---

## Technology Stack

- Java 17+
- Spring Boot 4.0.2
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL (local or cloud-hosted, e.g., Supabase, Render)
- JWT (jjwt) for stateless authentication
- BCrypt for password hashing
- Docker for deployment

---

## Prerequisites

- Java 17 or higher
- Maven 3.9+ (or use the included Maven wrapper `./mvnw`)
- A running PostgreSQL database (local or cloud-hosted)

---

## Installation and Setup

### 1. Clone the repository

```bash
git clone https://github.com/TechAngelX/xcore-tasks-api.git
cd xcore-tasks-api
```

### 2. Configure environment variables

Create a `.env` file in the project root with the following variables:

```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=xcore_tasks
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET=your-secret-key-at-least-256-bits-long
PORT=8080
```

The application loads this file automatically via Spring Boot's `spring.config.import`.

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The API will start at **http://localhost:8080**.

### Other useful commands

```bash
./mvnw test                     # Run all tests
./mvnw test -Dtest=ClassName    # Run a single test class
./mvnw clean package            # Build JAR artifact
```

---

## Running with Docker

### Build and run the Docker image

```bash
docker build -t xcore .
docker run -p 8080:8080 \
  -e DB_HOST=your_db_host \
  -e DB_PORT=5432 \
  -e DB_NAME=xcore_tasks \
  -e DB_USERNAME=your_username \
  -e DB_PASSWORD=your_password \
  -e JWT_SECRET=your-secret-key \
  xcore
```

Or using an env file:

```bash
docker run -p 8080:8080 --env-file .env xcore
```

---

## API Endpoints

### Authentication

| Method | Endpoint         | Description                  |
|--------|------------------|------------------------------|
| POST   | `/auth/register` | Register a new user          |
| POST   | `/auth/login`    | Login and receive a JWT      |

### Tasks

| Method | Endpoint           | Description          |
|--------|--------------------|----------------------|
| GET    | `/api/tasks`       | List tasks (paginated) |
| POST   | `/api/tasks`       | Create a new task    |
| PUT    | `/api/tasks/{id}`  | Update a task        |
| DELETE | `/api/tasks/{id}`  | Delete a task        |

Users can only access their own tasks. Admins can access any user's tasks.

---

## Project Purpose

This project demonstrates:

- Professional backend design with layered architecture
- Security best practices including password hashing, JWT authentication, and role-based access
- Cloud-deployable architecture that can be tested by others
- Ability to handle relational data and user-specific resource access

---


## License
Creative Commons:
© Ricki Angel 2026 | TechAngelX

This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.

## Disclaimer

This project is for personal or educational use only and comes without any warranty.
***
<h2 align="center">Support</h2>
<div align="center">
  For issues or questions, feel free to reach out to me on GitHub.
  <br /><br />
  <a href="https://techangelx.com" target="_blank" rel="noopener noreferrer">
    <img src="./readme_images/logo.png" alt="Tech Angel X Logo" width="70" height="70" style="border-radius: 50%; border: 4px solid #ffffff; box-shadow: 0 4px 10px rgba(0,0,0,0.2);">
  </a>
  <br /><br />
  <b>Built by Ricki Angel</b> • <a href="https://techangelx.com" target="_blank" rel="noopener noreferrer">Tech Angel X</a>
</div>




