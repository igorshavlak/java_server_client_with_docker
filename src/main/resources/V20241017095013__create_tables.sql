CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       gender VARCHAR(10),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       phone VARCHAR(15),
                       cell VARCHAR(15),
                       nat VARCHAR(10)
);

CREATE TABLE Names (
                       name_id SERIAL PRIMARY KEY,
                       user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                       title VARCHAR(20),
                       first VARCHAR(50),
                       last VARCHAR(50)
);

CREATE TABLE Locations (
                           location_id SERIAL PRIMARY KEY,
                           user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                           street_number INT,
                           street_name VARCHAR(100),
                           city VARCHAR(50),
                           state VARCHAR(50),
                           country VARCHAR(50),
                           postcode VARCHAR(20),
                           latitude VARCHAR(20),
                           longitude VARCHAR(20),
                           timezone_offset VARCHAR(10),
                           timezone_description VARCHAR(100)
);

CREATE TABLE Logins (
                        login_id SERIAL PRIMARY KEY,
                        user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                        uuid VARCHAR(36),
                        username VARCHAR(50),
                        password VARCHAR(255),
                        salt VARCHAR(50),
                        md5 VARCHAR(255),
                        sha1 VARCHAR(255),
                        sha256 VARCHAR(255)
);

CREATE TABLE DateOfBirths (
                              dob_id SERIAL PRIMARY KEY,
                              user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                              birth_date DATE,
                              age INT
);

CREATE TABLE Registered (
                            registered_id SERIAL PRIMARY KEY,
                            user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                            registration_date DATE,
                            registration_age INT
);

CREATE TABLE IDs (
                     id_id SERIAL PRIMARY KEY,
                     user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                     name VARCHAR(50),
                     value VARCHAR(50)
);

CREATE TABLE Pictures (
                          picture_id SERIAL PRIMARY KEY,
                          user_id INT REFERENCES Users(user_id) ON DELETE CASCADE,
                          large bytea,
                          medium bytea,
                          thumbnail bytea
);
