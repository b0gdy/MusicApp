CREATE TABLE users (

    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,

    PRIMARY  KEY (id)

);

CREATE TABLE addresses (

    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
--     user_id INT NOT NULL,

    PRIMARY KEY (id)
--     FOREIGN KEY (user_id) REFERENCES users(id)

);

CREATE TABLE users_addresses (

    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    address_id INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (address_id) REFERENCES addresses(id)

);

CREATE TABLE artists (

    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,

    PRIMARY  KEY (id)

);

CREATE TABLE songs (

    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    artist_id INT NOT NULL,

    PRIMARY  KEY (id),
    FOREIGN KEY (artist_id) REFERENCES artists(id)

);

CREATE TABLE users_songs (

    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    user_id INT NOT NULL,
    song_id INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (song_id) REFERENCES songs(id)

);
