CREATE TABLE countries
(
    id        INT IDENTITY
        CONSTRAINT countries_pk
            PRIMARY KEY NONCLUSTERED,
    name      VARCHAR(255),
    latitude  NUMERIC,
    longitude NUMERIC
)

create table corona_data
(
	id INT IDENTITY
		constraint corona_data_pk
			PRIMARY KEY NONCLUSTERED,
	date DATE,
	country_id INT
		CONSTRAINT corona_data_countries_id_fk
			REFERENCES countries,
	confirmed_cases INT
)