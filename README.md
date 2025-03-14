# Public Administration Management System

## Overview

This is a **Spring Boot** application designed for managing public administration processes. The system provides functionalities for citizens, officials, and administrators to manage and process various public documents efficiently.

## Features

- **User Authentication & Authorization** (JWT-based security)
- **Document Management** (Upload, View, and Process PDFs)
- **Role-Based Access Control** (Citizen, Official, Administrator)
- **Request Processing** (Track and handle public service requests)
- **RESTful API** for frontend integration with Angular

## Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, Hibernate, JPA
- **Database:** MySQL
- **Frontend:** Angular
- **Authentication:** JWT (JSON Web Tokens)

## Installation

### Prerequisites

Ensure you have the following installed:

- Java 17+
- Maven
- MySQL

### Steps to Run Backend

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/public-administration-app.git
   cd public-administration-app/backend
   ```
2. Configure the database connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/public_administration_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

### User

- `POST /user/login` → Login and get JWT
- `POST /user/register` → Register a new user
- `GET /user/me` → Get User from JWT

### Document Management

- `POST /document/create` → Upload a document
- `GET /document/get/{documentId}` → Get document details
- `GET /document/get-all` → Get all documents
- `GET /document/getall/{userId}` → Get all documents for a user

### Document Patterns Management

- `POST /document/create` → Upload a document pattern
- `GET /document-pattern/get/{id}` → Get document pattern details
- `GET /document-pattern/getall` → Get all document patterns

### Request Management

- `PUT /request/update-status/{requestId}` → Update a request 
status
- `GET /request/get/{documentId}` → Fetch the request for a document
- `GET /request/get-for-user/{userId}` → Fetch all the requests for a document
- `GET /request/get-all-processing` → Fetch all processing requests
- `POST /request/create` → Create a new request

## Security

- JWT-based authentication for secure access

## Future Enhancements

- **Automated Notification System** for request status updates
- **Audit Logging** for better tracking
- **Cloud Deployment** on AWS/GCP

## Contributors

- **Dan Alexandru**
