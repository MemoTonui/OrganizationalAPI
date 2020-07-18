SET MODE PostgreSQL;

CREATE DATABASE organization;
\c organization;

CREATE TABLE IF NOT EXISTS users(
id serial PRIMARY KEY,
name VARCHAR,
email VARCHAR,
pos VARCHAR,
role VARCHAR,
department VARCHAR
);
CREATE TABLE IF NOT EXISTS departments(
id serial PRIMARY KEY,
departmentName VARCHAR,
description VARCHAR,
noOfEmployees INTEGER,
);

CREATE DATABASE organization_test WITH TEMPLATE organization;
