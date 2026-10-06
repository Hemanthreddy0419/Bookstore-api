# Bookstore API

A RESTful web service built with **Spring Boot** and **Spring Data JPA** for managing a bookstore inventory, including books and their respective authors.

## 🚀 Features
- **Book Management**: Full CRUD operations for books (ID, Title, ISBN, Price).
- **Author Management**: Manage authors and map them to books using a `@ManyToOne` relationship.
- **JSON Serialization**: Configured with `@JsonBackReference` to prevent infinite recursion loops during API calls.

## 🛠️ Tech Stack
- **Backend**: Java 17+ / Spring Boot
- **Database**: H2 (In-Memory) / MySQL / PostgreSQL *(Choose yours)*
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven

## 📋 Prerequisites
Before running this project, ensure you have installed:
- **Java Development Kit (JDK 17 or higher)**
- **Maven** (optional, wrapper `./mvnw` is included)

## ⚙️ Getting Started

### 1. Clone the repository
```bash
git clone https://github.com
cd bookstore-api
```

### 2. Configure the database
Open `src/main/resources/application.properties` and update your database credentials if you are not using an in-memory H2 database.

### 3. Run the application
Using the Maven wrapper included in the project:
```bash
# On Windows
mvnw.cmd spring-boot:run

# On Mac/Linux
./mvnw spring-boot:run
```
The server will start on port `8080` by default (`http://localhost:8080`).

## 🛑 API Endpoints (Examples)

| HTTP Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/books` | Get all books |
| **GET** | `/api/books/{id}` | Get a book by ID |
| **POST** | `/api/books` | Add a new book |
| **PUT** | `/api/books/{id}` | Update an existing book |
| **DELETE** | `/api/books/{id}` | Delete a book |
