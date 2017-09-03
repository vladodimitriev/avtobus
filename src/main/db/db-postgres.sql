drop table country;
drop table place;
drop table carrier;
drop table busline;

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
    departuretime varchar(5),
    arrivaltime varchar(5),
    distance integer,
    jurneytime  varchar(10),
    operationdays varchar(30),
    operationperiod varchar(50),
    operationmonth varchar(50),
    price varchar(20),
    pricereturn varchar(20),
    comment varchar(200),
    departureplaceid integer REFERENCES PLACE (id),
    destinationplaceid integer REFERENCES PLACE (id),
    carrierid integer REFERENCES CARRIER (id)
) ;