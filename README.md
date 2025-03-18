# TaskManager
## Overview
The **Task Manager API** is a RESTful service that allows users to manage tasks efficiently. It includes features such as task creation, retrieval, filtering, sorting, and authentication.

## Technologies Used
- **Java Spring Boot**
- **Spring Security** (for authentication and authorization)
- **MySQL** (for database storage)
- **Redis** (for caching)
- **Spring Data JPA** (for ORM)
- **JWT Authentication**

## API Endpoints

### **Authentication Endpoints**

#### **1. User Signup**
- **URL:** `/api/v1/auth/signup`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "password": "password123",
    "username":"use"
  }
  ```
- **Response:**
  - `"Sign up successful"` if registration is successful.
  - `"Username already registered"` if the user is already registered.

#### **2. User Login**
- **URL:** `/api/v1/auth/login`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```
- **Response:**
  - JWT Token for authentication.

#### **3. Authentication Check (Ping Endpoint)**
- **URL:** `/api/v1/auth/ping`
- **Method:** `GET`
- **Response:**
  - Returns the authenticated username if the user is logged in.
  - `401 Unauthorized` if not authenticated.

---

### **Task Management Endpoints**

#### **1. Create a Task**
- **URL:** `/api/v1/tasks/`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "title": "Complete project",
    "description": "Finish the API implementation",
    "priority": "HIGH",
    "status": "PENDING",
    "dueDate": "2025-03-20T00:00:00"
  }
  ```
- **Response:**
  - Returns the created task.

#### **2. Get All Tasks (Pagination Supported)**
- **URL:** `/api/v1/tasks/`
- **Method:** `GET`
- **Query Parameters:**
  - `pageNum` (integer) - Page number for pagination
- **Response:**
  - Returns a paginated list of tasks.

#### **3. Get a Task by ID**
- **URL:** `/api/v1/tasks/{id}`
- **Method:** `GET`
- **Response:**
  - Returns the task details for the given ID.

#### **4. Update a Task**
- **URL:** `/api/v1/tasks/{id}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "title": "Updated Task Title",
    "description": "Updated Description",
    "priority": "LOW",
    "status": "COMPLETED",
    "dueDate": "2025-03-25T00:00:00"
  }
  ```
- **Response:**
  - Returns the updated task.

#### **5. Mark a Task as Completed**
- **URL:** `/api/v1/tasks/task-completed/{id}`
- **Method:** `PUT`
- **Response:**
  - `"task completed"` on success.

#### **6. Delete a Task**
- **URL:** `/api/v1/tasks/{id}`
- **Method:** `DELETE`
- **Response:**
  - `"task deleted successfully"` on success.

#### **7. Filter Tasks by Priority and Status**
- **URL:** `/api/v1/tasks/filter/`
- **Method:** `GET`
- **Query Parameters:**
  - `pageNum` (integer) - Page number for pagination
  - `priority` (string) - Priority filter (e.g., HIGH, MEDIUM, LOW)
  - `status` (string) - Status filter (e.g., PENDING, COMPLETED)
- **Response:**
  - Returns a filtered list of tasks.

#### **8. Get Sorted Tasks (Priority & Due Date Order)**
- **URL:** `/api/v1/tasks/sort`
- **Method:** `GET`
- **Response:**
  - Returns a sorted list of tasks (by priority and due date).

---

## **How to Run the Project**

### **1. Clone the Repository**
```sh
$ git clone https://github.com/mohitkmeena/TaskManager.git
$ cd TaskManager
```

### **2. Set Up MySQL Database**
- Create a database named `task_manager`.
- Update `application.properties`:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  spring.jpa.hibernate.ddl-auto=update
  ```

### **3. Run the Spring Boot Application**
```sh
$ mvn spring-boot:run
```

### **4. Test the API Using Postman or cURL**
You can use Postman to send API requests and test different endpoints.

---




