insert into COUNTRY (name, population, countrycode) values ('Macedonia', '2000000', 'MKD');
insert into COUNTRY (name, population, countrycode) values ('Bulgaria', '7000000', 'BGR');
insert into COUNTRY (name, population, countrycode) values ('Serbia', '7000000', 'SRB');
insert into COUNTRY (name, population, countrycode) values ('Albania', '2500000', 'ALB');
insert into COUNTRY (name, population, countrycode) values ('Greece', '11000000', 'GRE');
insert into COUNTRY (name, population, countrycode) values ('Kosovo', '1800000', 'KOS');
insert into COUNTRY (name, population, countrycode) values ('Croatia', '4000000', 'KOS');
insert into COUNTRY (name, population, countrycode) values ('Austria', '8000000', 'AUT');
insert into COUNTRY (name, population, countrycode) values ('Czechia', '10000000', 'CZE');

insert into PLACE (name, population, postalcode, countryid) values ('Skopje', '700000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Negotino', '15000', '1440', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Kavadarci', '30000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Ohrid', '70000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Veles', '70000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Tetovo', '100000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Kumanovo', '70000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Kocani', '70000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Bitola', '100000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Prilep', '70000', '1000', '1');
insert into PLACE (name, population, postalcode, countryid) values ('Strumica', '70000', '1000', '1');

insert into CARRIER (name, carrierplaceid) values ('DajoTurs', '2');
insert into CARRIER (name, carrierplaceid) values ('RuleTurs', '1');
insert into CARRIER (name, carrierplaceid) values ('Pamela', '2');
insert into CARRIER (name, carrierplaceid) values ('SamVel', '3');
insert into CARRIER (name, carrierplaceid) values ('DajoTurs', '2');
insert into CARRIER (name, carrierplaceid) values ('Strumica express', '11');
insert into CARRIER (name, carrierplaceid) values ('Makedonija Soobracaj', '1');
insert into CARRIER (name, carrierplaceid) values ('Vardar Express', '1');
insert into CARRIER (name, carrierplaceid) values ('Pelagonija Turs', '9');
insert into CARRIER (name, carrierplaceid) values ('Antigona Trans', '2');
insert into CARRIER (name, carrierplaceid) values ('Mlaz Bogdanci', '1');
insert into CARRIER (name, carrierplaceid) values ('Galeb', '4');

insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '210', '1', '2', '2');

insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Kavadarci-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '50', '3', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Kavadarci-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '50', '3', '2', '1');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Kavadarci-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '50', '3', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Kavadarci-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '50', '3', '2', '1');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, departureplaceid, destinationplaceid, carrierid) 
values ('Kavadarci-Negotino', '06:00', '07:30', '100', '1h30m', '1,2,3,4,5,6,7,p', '50', '3', '2', '2');
