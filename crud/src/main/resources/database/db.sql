create database crud_jpa_user;

CREATE TABLE users(
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(50),
     email VARCHAR(50),
     gender BIT,
     role INT
);

INSERT INTO users(name, email,gender,role) VALUES('Kate','kate@gmail.com',0,1);

SELECT * FROM USERS