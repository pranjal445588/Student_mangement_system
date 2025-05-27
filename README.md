# School Management System (JavaFX + MySQL)

A desktop-based School Management System featuring a modern, easy-to-use dashboard UI for managing students, staff, exams, library, events, inventory, and notices.

![Dashboard Screenshot](image1)

## Features

- Modern dashboard inspired by classic school management UI
- Student Management (add, view, delete students)
- Modular structure: add more management modules easily
- MySQL database connectivity with JDBC
- JavaFX GUI

## Technologies Used

- Java 8+ (JavaFX)
- MySQL
- JDBC

## Setup Instructions

### 1. Database Setup

- Make sure MySQL is installed and running.
- Create the database and table by running:

    ```sql
    CREATE DATABASE IF NOT EXISTS student_management;
    USE student_management;

    CREATE TABLE IF NOT EXISTS students (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE,
        grade VARCHAR(10)
    );
    ```

- Update `Database.java` with your MySQL username and password.

### 2. Add MySQL JDBC Driver

- Download [mysql-connector-java.jar](https://dev.mysql.com/downloads/connector/j/).
- Place it in the `lib/` directory.
- Add it to your project's classpath.

### 3. Build and Run

- Compile all `.java` files in the `src/` directory.
- Run `Main.java`:

    ```sh
    javac -cp "lib/mysql-connector-java.jar;." src/*.java
    java -cp "lib/mysql-connector-java.jar;src;." Main
    ```

### 4. Usage

- The dashboard window will open.
- Click on "Student Management" to add, view, or delete students.
- Additional modules can be implemented similarly.

## File Structure

```
student-management-system/
│
├── src/
│   ├── Main.java
│   ├── Database.java
│   ├── DashboardController.java
│   ├── StudentManagementController.java
│   ├── Student.java
│   └── resources/
│       ├── dashboard.fxml
│       └── student_management.fxml
│
└── lib/
    └── mysql-connector-java.jar
```

## Screenshots

- Dashboard

  ![Dashboard Screenshot](image1)

## Extending

To add more modules (Staff, Exams, etc.), create new FXML layouts and controllers, following the pattern in `StudentManagementController.java`.

---

## License

This project is for educational use.
