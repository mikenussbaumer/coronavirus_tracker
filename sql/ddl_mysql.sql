CREATE TABLE countries (
	id INT AUTO_INCREMENT,
    name VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE,
    PRIMARY KEY(id)
);

CREATE TABLE corona_data (
	id INT AUTO_INCREMENT,
    date DATE,
    country_id INT NOT NULL,
    confirmed_cases INT,
    PRIMARY KEY(id),
    FOREIGN KEY(country_id) REFERENCES countries(id)
);