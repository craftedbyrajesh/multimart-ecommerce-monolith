# 🛒 MultiMart E-Commerce Platform — Monolithic

A backend-focused e-commerce application built with **Java and Spring Boot**, following a layered monolithic architecture.

This project is the first stage of a two-part architecture journey. The same business domain is later decomposed into independently deployable microservices.

🔗 **Microservices Version:** [MultiMart E-Commerce — Microservices](https://github.com/craftedbyrajesh/multimart-ecommerce-microservices)

---

## 📌 Overview

MultiMart is an e-commerce backend that provides REST APIs for:

- User management
- Address management
- Product management
- Shopping cart management
- Order processing

The application is implemented as a **single deployable Spring Boot application** using a layered architecture:

```
Client → Controller → Service → Repository → PostgreSQL
```

The project demonstrates how a traditional monolithic backend can be structured using separation of concerns and later evolved into a microservices architecture.

## ⚙️ Tech Stack

| Category | Technologies |
|---|---|
| Language | Java |
| Framework | Spring Boot, Spring MVC |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Early Development | H2 Database |
| Build Tool | Maven |
| Containerization | Docker, Docker Compose |
| Database Management | pgAdmin |
| Monitoring | Spring Boot Actuator |
| API Testing | Postman |
| Version Control | Git, GitHub |

## 🏗️ Architecture

```
                    Client
                      │
                      ▼
             ┌─────────────────┐
             │   Controller    │
             │    REST APIs    │
             └────────┬────────┘
                      │
                      ▼
             ┌─────────────────┐
             │     Service     │
             │ Business Logic  │
             └────────┬────────┘
                      │
                      ▼
             ┌─────────────────┐
             │   Repository    │
             │ Spring Data JPA │
             └────────┬────────┘
                      │
                      ▼
             ┌─────────────────┐
             │   PostgreSQL    │
             └─────────────────┘
```

**Controller Layer** — handles incoming HTTP requests, maps REST endpoints, returns HTTP responses
**Service Layer** — contains application business logic, workflows, and validation
**Repository Layer** — handles database operations using Spring Data JPA
**DTO Layer** — request/response DTOs separate the API contract from internal JPA entities

```
Request DTO → Controller → Service → Entity → Repository → Database
Database → Entity → Service → Response DTO → Controller → Client
```

## 🧩 Core Modules

**👤 User Management** — create, fetch, update users; manage addresses; manage roles

**📦 Product Management** — create, fetch, update, delete products

**🛒 Cart Management** — add/remove items, fetch a user's cart, manage quantities

**📋 Order Management** — place orders, store order items, track order status, clear cart after successful order

### 🔄 Business Flow
```
Create User → Browse Products → Add to Cart → View Cart → Place Order → Create Order → Clear Cart
```

## 📁 Project Structure

```
multimart-ecommerce-monolith/
├── src/
│   ├── main/
│   │   ├── java/com/app/ecom/
│   │   │   ├── controller/   (Cart, Order, Product, User controllers)
│   │   │   ├── dto/           (Request/response DTOs)
│   │   │   ├── model/         (Entities: User, Product, Cart, Order, OrderStatus, UserRole, etc.)
│   │   │   ├── repository/    (Spring Data JPA repositories)
│   │   │   ├── service/       (Business logic)
│   │   │   └── EcomApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
├── docker-compose.yml
├── pom.xml
├── mvnw / mvnw.cmd
├── .gitignore
├── .gitattributes
└── README.md
```

## 🗄️ Database

PostgreSQL is used for persistent data storage, with Spring Data JPA and Hibernate mapping Java entities to relational tables. H2 was used during early development before migrating to PostgreSQL.

## 🐳 Docker Environment

Docker Compose runs PostgreSQL and pgAdmin:

```
             Docker Environment
                    │
        ┌───────────┴───────────┐
        ▼                       ▼
   PostgreSQL                pgAdmin
     :5432                    :5050
```

## ▶️ Running the Application

**Prerequisites:** Java, Maven (optional — wrapper included), Docker Desktop, Git, Postman

```bash
# 1. Clone the repo
git clone https://github.com/craftedbyrajesh/multimart-ecommerce-monolith.git
cd multimart-ecommerce-monolith

# 2. Start PostgreSQL and pgAdmin
docker compose up -d
docker compose ps
# Should show: ecom_monolith_postgres, ecom_monolith_pgadmin

# 3. Start the Spring Boot application
./mvnw spring-boot:run        # Linux/macOS
.\mvnw.cmd spring-boot:run     # Windows
```

You can also run the application directly from IntelliJ IDEA.

## 🔌 Application Configuration

Database configuration is supplied through environment variables:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecomdb
    username: postgres
    password: ${DB_PASSWORD}
```

Sensitive local files (`.env`, `application-local.yml`) are excluded from Git.

## 📮 REST API Examples

```
User:      POST/GET /api/users, GET/PUT /api/users/{id}
Product:   POST/GET /api/products, GET/PUT/DELETE /api/products/{id}
Cart:      POST /api/cart/add, DELETE /api/cart/{id}, GET /api/cart
Order:     POST /api/orders
```
*API paths may vary depending on the current controller implementation.*

## 🧪 API Testing

Recommended Postman testing sequence:
```
Create User → Create Product → Add to Cart → Fetch Cart → Place Order → Verify Order
```

## 🔍 Application Monitoring

Spring Boot Actuator is configured with:
```
GET http://localhost:8080/actuator/health
GET http://localhost:8080/actuator/beans
```

## 🔐 Security & Secrets

Local credentials are never committed. `.gitignore` excludes `.env`, `.env.*`, `application-local.yml`, `application-local.properties`, `*.log`. Database passwords are supplied via environment variables (`${DB_PASSWORD}`) — never commit real passwords, API keys, or tokens to GitHub.

## 🧠 Key Concepts Demonstrated

Java · OOP · Spring Boot · Spring MVC · REST API Development · Dependency Injection · Layered Architecture · Spring Data JPA · Hibernate · Entity Relationships · DTO Pattern · PostgreSQL · Docker · Docker Compose · pgAdmin · Spring Boot Actuator · Maven · Postman · Git/GitHub

## 🔄 Monolith → Microservices

This project was intentionally developed as a monolith first, then decomposed into independently deployable services once the core business logic was established.

```
Monolith                          Microservices
  Client                             Client
    │                                  │
    ▼                                  ▼
Spring Boot App                  API Gateway
(User/Product/Cart/Order)              │
    │                      ┌───────────┼───────────┐
    ▼                      ▼           ▼           ▼
PostgreSQL              User        Product       Order
                        Service     Service      Service
                            │           │           │
                            ▼           ▼           ▼
                         Database   Database    Database
```

The microservices version introduces independent services, an API Gateway, service-to-service communication, and independent deployment.

🔗 **Microservices Repository:** [multimart-ecommerce-microservices](https://github.com/craftedbyrajesh/multimart-ecommerce-microservices)

## 📚 What I Learned

- Designing RESTful APIs and building layered Spring Boot applications
- Implementing CRUD operations with JPA/Hibernate, modeling entity relationships
- Using DTOs for clean API contracts
- Connecting Spring Boot to PostgreSQL, running it via Docker/Docker Compose, managing it with pgAdmin
- Testing APIs with Postman and monitoring with Spring Boot Actuator
- Understanding monolithic architecture and the motivation behind microservices
- Decomposing a monolithic application into independent services

## 🚀 Future Improvements

- JWT authentication and authorization
- Global exception handling
- Bean Validation
- Pagination and sorting
- Swagger/OpenAPI documentation
- Automated unit and integration testing
- CI/CD pipeline
- Centralized logging
- Production deployment
- Payment integration

## 👨‍💻 Author

**Rajesh C S**
🔗 [GitHub](https://github.com/craftedbyrajesh) · 🔗 [LinkedIn](https://linkedin.com/in/rajesh-c-s)
