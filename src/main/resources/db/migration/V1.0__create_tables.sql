CREATE TABLE album (
    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    release_year INT NOT NULL,
    artist_id INT NOT NULL,
    genre VARCHAR(30),
    description VARCHAR(255)
);
ALTER TABLE album ADD CONSTRAINT album_pk PRIMARY KEY (id);

CREATE TABLE artist (
    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    start_year INT NOT NULL,
    end_year INT,
    description VARCHAR(255)
);
ALTER TABLE artist ADD CONSTRAINT artist_pk PRIMARY KEY (id);

