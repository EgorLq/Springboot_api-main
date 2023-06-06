DROP TABLE IF EXISTS diary;

DROP TABLE IF EXISTS users;

CREATE TABLE diary (
                       id SERIAL PRIMARY KEY,
                       user_id INTEGER,
                       times TIME,
                       cases TEXT,
                       dates DATE

);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       fullname TEXT,
                       role TEXT,
                       login TEXT,
                       password TEXT
);