CREATE TABLE COUNTRY ( 
    id integer NOT NULL generated always as identity (start with 1, increment by 1), 
    name varchar(100), 
    population integer,
    countrycode varchar(5),
    constraint primary_key primary key (id))
) ;

CREATE TABLE PLACE ( 
    id integer NOT NULL generated always as identity (start with 1, increment by 1), 
    name varchar(100), 
    population integer,
    postalcode varchar(10),
    countryid integer,
    constraint primary_key primary key (id))
) ;

CREATE TABLE CARRIER ( 
    id integer NOT NULL generated always as identity (start with 1, increment by 1), 
    name varchar(100),
    carrierplaceid integer,
    constraint primary_key primary key (id))
) ;

CREATE TABLE BUSLINE ( 
    id integer NOT NULL generated always as identity (start with 1, increment by 1), 
    name varchar(100),
    departuretime varchar(5),
    arrivaltime varchar(5),
    distance integer,
    jurneytime  varchar(10),
    operationdays varchar(30),
    price varchar(20),
    departureplaceid integer,
    destinationplaceid integer,
    carrierid integer,
    constraint primary_key primary key (id))
) ;