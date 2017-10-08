drop table busline;
drop table carrier;
drop table place;
drop table country;

CREATE TABLE COUNTRY (
    id SERIAL PRIMARY KEY,
    name varchar(100),
    namecyrillic varchar(100),
    population int,
    countrycode varchar(5)
);

CREATE TABLE PLACE (
    id SERIAL PRIMARY KEY,
    name varchar(100),
    namecyrillic varchar(100),
    population integer,
    postalcode varchar(10),
    importance integer,
    countryid integer REFERENCES COUNTRY (id)
 );

CREATE TABLE CARRIER (
    id SERIAL PRIMARY KEY,
    name varchar(100),
    namecyrillic varchar(100),
    carrierplaceid integer REFERENCES PLACE (id)
) ;

CREATE TABLE BUSLINE (
    id SERIAL PRIMARY KEY,
    name varchar(100),
    departuretime varchar(20),
    arrivaltime varchar(20),
    distance integer,
    jurneytime  varchar(20),
    operationdays varchar(30),
    operationperiod varchar(50),
    operationmonth varchar(50),
    price varchar(20),
    pricereturn varchar(20),
    comment varchar(500),
    departureplaceid integer REFERENCES PLACE (id),
    destinationplaceid integer REFERENCES PLACE (id),
    carrierid integer REFERENCES CARRIER (id)
) ;