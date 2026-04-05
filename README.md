# Finance Data Processing and Access Control Backend

## 📌 Overview

This project is a backend system for managing financial data with role-based access control. It allows users to manage financial records and provides summary-level analytics for a dashboard.

The system is designed with clean architecture, proper data handling, and clear separation of concerns.

---

## 🛠 Tech Stack

* Java (Spring Boot)
* Spring Data JPA
* H2 In-Memory Database
* Maven

---

## 🚀 Features

### 👤 User Management

* Create users
* Assign roles (ADMIN, ANALYST, VIEWER)
* Manage user status (active/inactive)

---

### 💰 Financial Records Management

* Create financial records
* View all records
* Update records
* Delete records
* Delete all records
* Filter records by:

  * Type (INCOME / EXPENSE)
  * Category

---

### 📊 Dashboard APIs

* Total Income
* Total Expense
* Net Balance
* Category-wise summary

---

### 🔐 Access Control

Role-based permissions:

| Role    | Permissions                     |
| ------- | ------------------------------- |
| ADMIN   | Full access (CRUD + delete all) |
| ANALYST | View records + summary          |
| VIEWER  | View only                       |

---

### ⚠️ Validation & Error Handling

* Prevent invalid amounts (negative values)
* Restrict unauthorized actions
* Return meaningful error responses

---

## 📡 API Endpoints

### 🔹 User APIs

* `POST /users` → Create user
* `GET /users` → Get all users

---

### 🔹 Record APIs

* `POST /records` → Create record
* `GET /records` → Get all records
* `PUT /records/{id}` → Update record
* `DELETE /records/{id}` → Delete record
* `DELETE /records/delete-all` → Delete all records
* `GET /records/filter?type=INCOME` → Filter by type
* `GET /records/filter?category=Food` → Filter by category

---

### 🔹 Summary APIs

* `GET /summary/total-income`
* `GET /summary/total-expense`
* `GET /summary/net-balance`
* `GET /summary/category-wise`

---

## ▶️ How to Run

1. Clone the repository
2. Navigate to project folder
3. Run the application:

```
mvn spring-boot:run
```

4. Access APIs at:

```
http://localhost:8080
```

---

## 🧪 Sample Request

### Create Record

```
POST /records
Header: role = ADMIN
```

```
{
  "amount": 5000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-05",
  "userId": 1
}
```

---

## 📌 Assumptions

* Role is passed via request header
* No authentication (JWT/session) implemented
* H2 database used for simplicity (data resets on restart)

---

## 📈 Design Highlights

* Clean layered architecture (Controller → Service → Repository)
* Enum-based role and type management
* Separation of business logic from API layer
* Simple and maintainable code structure

---

