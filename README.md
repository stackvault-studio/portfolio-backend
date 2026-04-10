# portfolio-backend

[![dev](https://github.com/stackvault-studio/portfolio-backend/actions/workflows/docker-publish.yml/badge.svg?branch=dev)](https://github.com/stackvault-studio/portfolio-backend/actions/workflows/docker-publish.yml)
[![staging](https://github.com/stackvault-studio/portfolio-backend/actions/workflows/docker-publish.yml/badge.svg?branch=feature%2F*)](https://github.com/stackvault-studio/portfolio-backend/actions/workflows/docker-publish.yml)

## Project Description

Backend REST API for the portfolio application built with Spring Boot. Provides endpoints for managing portfolio data including work experience, technologies, certifications, and achievements.

## Tech Stack

- Java 21
- Spring Boot 4.0.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Prerequisites

- Java Development Kit (JDK) 21 or higher
- Maven 3.6+
- PostgreSQL 14+ (or use provided Docker setup)

## Installation & Run

1. Clone the repository
2. Configure database connection in `src/main/resources/application.yml`
3. Build the project:
```bash
./mvnw clean install
```
4. Run the application:
```bash
./mvnw spring-boot:run
```

## Environment Variables

Configure the following in `application.yml`:

- `spring.datasource.url` - PostgreSQL connection URL
- `spring.datasource.username` - Database username
- `spring.datasource.password` - Database password
- `server.port` - Application port (default: 8080)

## Related Repositories

- [portfolio-front](https://github.com/stackvault-studio/portfolio-front) - Frontend application
- [portfolio-model](https://github.com/stackvault-studio/portfolio-model) - Shared models
- [portfolio](https://github.com/stackvault-studio/portfolio) - Central hub
