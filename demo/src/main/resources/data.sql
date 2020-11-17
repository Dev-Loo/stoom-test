CREATE TABLE IF NOT EXISTS Adress(
	id LONG NOT NULL IDENTITY(1,1) PRIMARY KEY,
	street_name varchar(1024),
	number int,
	complement varchar(1024),
	neighbourhood varchar(1024),
	city varchar(255),
	state varchar(255),
	country varchar(255),
	zipcode int,
	latitude float,
	longitude float
);