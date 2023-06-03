DROP TABLE IF EXISTS diary;

CREATE TABLE diary (
                       id SERIAL PRIMARY KEY,
                       user_id INTEGER,
                       times TIME,
                       cases TEXT,
                       dates DATE,
                       FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       fullname TEXT,
                       role TEXT,
                       login TEXT,
                       password TEXT
);