SET MODE PostgresSQL;

CREATE DATABASE organization;
\c organization

CREATE TABLE IF NOT EXISTS users(
id serial PRIMARY KEY,
name VARCHAR,
email VARCHAR,
pos VARCHAR,
role VARCHAR
);

CREATE TABLE IF NOT EXISTS departments(
id serial PRIMARY KEY,
departmentName VARCHAR,
description VARCHAR,
size int
);

CREATE TABLE IF NOT EXISTS news (
id serial PRIMARY KEY,
news_type VARCHAR,
department_id INT,
user_id INT,
title VARCHAR,
description VARCHAR
);


CREATE TABLE IF NOT EXISTS users_departments (
id SERIAL PRIMARY KEY,
user_id INT,
department_id INT
);

CREATE DATABASE organization_test WITH TEMPLATE organization;





