# 🛒 Order Management System (OMS) Backend

A **production-style e-commerce backend API** built using **Spring Boot**, implementing authentication, cart management, order processing, and admin controls.

This project demonstrates **real-world backend architecture and business logic** similar to platforms like Amazon or Flipkart (simplified).

---

## 🚀 Features

### 🔐 Authentication & Security

* JWT-based authentication
* User registration & login
* Role-based access control (ADMIN, CUSTOMER)

### 🛍️ Product Management

* View all products (public)
* View product by ID
* Create, update, delete products (ADMIN only)

### 🛒 Cart System

* Add products to cart
* View user cart
* Remove items from cart

### 🧾 Order System

* Create order from cart
* Automatic total price calculation
* Order items mapping
* Cart cleared after order creation

### 👑 Admin Features

* View all orders
* Update order status (CREATED, SHIPPED, DELIVERED, CANCELLED)

---

## 🏗️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA

### Database

* MySQL

### Dev Tools

* Docker
* Swagger (OpenAPI)

---

## 📂 Project Structure

```
Controller → Handles HTTP Requests
Service    → Business Logic
Repository → Database Access
Entity     → Database Models
DTO        → Data Transfer Objects
```

---

## 🔗 API Base URL

```
/api/v1
```

---

## 📘 API Documentation (Swagger)

After running the application:

```
http://localhost:8081/swagger-ui/index.html
```

---

## 🔑 Authentication APIs

| Method | Endpoint              | Description   |
| ------ | --------------------- | ------------- |
| POST   | /api/v1/auth/register | Register user |
| POST   | /api/v1/auth/login    | Login user    |

---

## 🛍️ Product APIs

| Method | Endpoint              | Access |
| ------ | --------------------- | ------ |
| GET    | /api/v1/products      | Public |
| GET    | /api/v1/products/{id} | Public |
| POST   | /api/v1/products      | ADMIN  |
| PUT    | /api/v1/products/{id} | ADMIN  |
| DELETE | /api/v1/products/{id} | ADMIN  |

---

## 🛒 Cart APIs

| Method | Endpoint                 |
| ------ | ------------------------ |
| POST   | /api/v1/cart/add         |
| GET    | /api/v1/cart             |
| DELETE | /api/v1/cart/{productId} |

---

## 🧾 Order APIs

| Method | Endpoint            |
| ------ | ------------------- |
| POST   | /api/v1/orders      |
| GET    | /api/v1/orders      |
| GET    | /api/v1/orders/{id} |

---

## 👑 Admin APIs

| Method | Endpoint                         |
| ------ | -------------------------------- |
| GET    | /api/v1/admin/orders             |
| PUT    | /api/v1/admin/orders/{id}/status |

---

## 🐳 Docker Support

### Build Image

```bash
docker build -t oms-backend .
```

### Run Container

```bash
docker run -p 8081:8081 oms-backend
```

---

## ⚙️ Environment Variables

```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
```

---

## 🧠 Key Concepts Implemented

* Layered Architecture (Controller → Service → Repository)
* Transaction Management
* DTO Pattern
* Role-based Authorization
* RESTful API Design
* API Versioning (/api/v1)

---

## 📈 Future Enhancements

* Payment Integration
* Order tracking system
* Email notifications
* Microservices architecture (Spring Cloud)

---

## 👨‍💻 Author

**Aameen Hussain**
Backend Developer (Java + Spring Boot)

---

## ⭐ Project Status

✅ Production-style backend complete
🚀 Ready for deployment & frontend integration

---
