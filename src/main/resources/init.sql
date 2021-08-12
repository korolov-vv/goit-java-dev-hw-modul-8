CREATE DATABASE "HW8Project"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Polish_Poland.1250'
    LC_CTYPE = 'Polish_Poland.1250'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE manufacturers
(
    manufacturer_id SERIAL PRIMARY KEY,
    manufacturer_name character varying(255) UNIQUE NOT NULL
);

CREATE TABLE products
(
    product_id SERIAL PRIMARY KEY,
    product_name varchar(255) UNIQUE NOT NULL,
    price NUMERIC(11,2) NOT NULL,
    manufacturer_id integer NOT NULL,
    FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(manufacturer_id)
);

CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    user_email varchar(255) UNIQUE NOT NULL,
    user_password varchar(255) NOT NULL,
    first_name varchar(255),
    last_name varchar(255),
    user_role varchar(50) NOT NULL,
    user_status varchar(50) NOT NULL
);