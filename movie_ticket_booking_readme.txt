🎬 Movie Ticket Booking REST API

A scalable and secure RESTful API for booking movie tickets, built using Spring Boot. This application allows users to browse movies, select seats, and book tickets with proper validation and transactional integrity.

------------------------------------------------------------

🚀 FEATURES

- User Authentication & Authorization (JWT-based)
- A-tier database storage of encrypted password mechanism using highly cpu intensive bcrypt algorithm
- Movie Management (Admin)
- Seat Management (Admin)
- Ticket Booking System (Admin/Customer)
- Role-based Authorization (Admin / Customer)
- Transaction Management to prevent double booking and rollback incomplete service transaction
- Exception Handling & Validation
- RESTful API design with proper status codes

------------------------------------------------------------

🛠️ TECH STACK

Backend: Spring Boot, Spring Data JPA, Hibernate
Security: Spring Security, JWT
Database: MySQL
Build Tool: Maven
Language: Java

------------------------------------------------------------

📂 PROJECT STRUCTURE

java
 ├── dao
 ├── entity
 ├── rest
 ├── security
 ├── service

------------------------------------------------------------

⚙️ SETUP & INSTALLATION

1. Clone the repository
git clone https://github.com/your-username/movie-ticket-booking.git

2. Run the application
mvn spring-boot:run

------------------------------------------------------------

🔑 AUTHENTICATION

Use JWT Token in headers:
Authorization: Bearer <your_token>

------------------------------------------------------------

📌 API ENDPOINTS

Refer the api endpoints through the swagger api:  http://localhost:8080/swagger-ui/index.html

------------------------------------------------------------



------------------------------------------------------------

⚠️ VALIDATIONS

- Seat must exist before booking
- Movie must exist
- User must exist
- Unique constraint prevents double booking

------------------------------------------------------------

🔄 TRANSACTION HANDLING

- Ensures atomic ticket booking
- Uses @Transactional to rollback incomplete service transaction


------------------------------------------------------------

❗ ERROR HANDLING

- Global exception handler implemented
- Returns proper HTTP status codes (400, 404, 500)

------------------------------------------------------------

📌 FUTURE ENHANCEMENTS

- Payment integration
- Create scheduling system to delete expired tickets,etc
- Multithreading
- Concurrency when multiple users tries to book at the same time
- Event Driven Implementation using Kafka to push notification to user before start of movie for reminder of movie start time
- Cloud Deployment

------------------------------------------------------------

👨‍💻 AUTHOR

Saurav Kumar
Associate Software Development Engineer II (Publicis Sapient)

------------------------------------------------------------

