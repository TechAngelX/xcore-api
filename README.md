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
- Tested for scalability with simulated high load

---

## Technology Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL (local or cloud-hosted, e.g., Supabase, Render)
- JWT for stateless authentication
- BCrypt for password hashing
- Docker for deployment

---

## Installation and Setup

1. Clone the repository:

```bash
git clone https://github.com/TechAngelX/xcore-tasks-api.git
cd xcore-tasks-api
Configure the database connection:

Create a .env file or set environment variables:

DB_URL=jdbc:postgresql://<host>:5432/xcore_tasks
DB_USER=<username>
DB_PASS=<password>
Ensure application.yml reads from these environment variables.

Run the application:

./mvnw spring-boot:run
The API will start at http://localhost:8080.

API Endpoints
Authentication
POST /auth/register — Register a new user

POST /auth/login — Login and receive JWT

Tasks
GET /api/tasks — List tasks (paginated)

POST /api/tasks — Create a new task

PUT /api/tasks/{id} — Update a task

DELETE /api/tasks/{id} — Delete a task

Only admins can access tasks of other users. Users can only access their own tasks.

Project Purpose
This project demonstrates:

Professional backend design with layered architecture

Security best practices including password hashing, JWT authentication, and role-based access

Cloud-deployable architecture that can be tested by others

Ability to handle relational data and user-specific resource access

License
This project is open source under the MIT License.
