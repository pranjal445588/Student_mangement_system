CREATE DATABASE IF NOT EXISTS sms;
USE sms;

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'student') NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    admission_number VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15),
    course_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    admission_number VARCHAR(20),
    exam_type VARCHAR(20), -- e.g., 'internal', 'final', 'lab'
    subject VARCHAR(100),
    marks INT,
    FOREIGN KEY (admission_number) REFERENCES students(admission_number)
);

CREATE TABLE IF NOT EXISTS attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    admission_number VARCHAR(20),
    month VARCHAR(20),
    attendance_percentage FLOAT,
    FOREIGN KEY (admission_number) REFERENCES students(admission_number)
);

CREATE TABLE IF NOT EXISTS fees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    admission_number VARCHAR(20),
    amount_paid DECIMAL(10,2),
    due DECIMAL(10,2),
    FOREIGN KEY (admission_number) REFERENCES students(admission_number)
);

CREATE TABLE IF NOT EXISTS notices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100),
    message TEXT,
    date_posted TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);