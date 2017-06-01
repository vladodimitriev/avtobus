USE avtobusi;
DROP TABLE COUNTRY;
DROP TABLE PLACE;
DROP TABLE CARRIER;
DROP TABLE BUSLINE;
DROP DATABASE avtobusi;
COMMIT;

CREATE DATABASE avtobusi;
COMMIT;

USE avtobusi;
CREATE TABLE COUNTRY ( 
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT, 
	name varchar(100) NOT NULL, 
	population int,
	countrycode varchar(5)
) ;

CREATE TABLE PLACE ( 
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT, 
	name varchar(100) NOT NULL, 
	population int,
	postalcode varchar(10),
	countryid int
) ;

CREATE TABLE CARRIER ( 
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT, 
	name varchar(100) NOT NULL,
	carrierplaceid int
) ;

CREATE TABLE BUSLINE ( 
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT, 
	name varchar(100),
	departuretime varchar(5),
	arrivaltime varchar(5),
	distance int,
	jurneytime  varchar(10),
	operationdays varchar(30),
	price varchar(20),
	departureplaceid int,
    destinationplaceid int,
    carrierid int
) ;
COMMIT;

ALTER TABLE PLACE
ADD CONSTRAINT FK_COUNTRY
FOREIGN KEY (countryid) REFERENCES COUNTRY(id);

ALTER TABLE CARRIER
ADD CONSTRAINT FK_PLACE
FOREIGN KEY (carrierplaceid) REFERENCES PLACE(id);

ALTER TABLE BUSLINE
ADD CONSTRAINT FK_DEPARTUREPLACE
FOREIGN KEY (departureplaceid) REFERENCES PLACE(id);

ALTER TABLE BUSLINE
ADD CONSTRAINT FK_DESTINATIONPLACE
FOREIGN KEY (destinationplaceid) REFERENCES PLACE(id);

ALTER TABLE BUSLINE
ADD CONSTRAINT FK_CARRIER
FOREIGN KEY (carrierid) REFERENCES CARRIER(id);
COMMIT;
