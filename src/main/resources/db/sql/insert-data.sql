insert into COUNTRY (name, population, countrycode) values ('Macedonia', '2000000', 'MKD');
insert into COUNTRY (name, population, countrycode) values ('Bulgaria', '7000000', 'BGR');
insert into COUNTRY (name, population, countrycode) values ('Serbia', '7000000', 'SRB');
insert into COUNTRY (name, population, countrycode) values ('Albania', '2500000', 'ALB');
insert into COUNTRY (name, population, countrycode) values ('Greece', '11000000', 'GRE');
insert into COUNTRY (name, population, countrycode) values ('Kosovo', '1800000', 'KOS');
insert into COUNTRY (name, population, countrycode) values ('Croatia', '4000000', 'KOS');
insert into COUNTRY (name, population, countrycode) values ('Austria', '8000000', 'AUT');
insert into COUNTRY (name, population, countrycode) values ('Czechia', '10000000', 'CZE');

insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Skopje', 'Скопје', '700000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Negotino', 'Неготино', '15000', '1440', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Kavadartsi', 'Кавадарци', '30000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Ohrid', 'Охрид','70000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Veles', 'Велес','70000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Tetovo', 'Тетово','100000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Kumanovo', 'Куманово','70000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Kochani', 'Кочани','70000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Bitola', 'Битола','100000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Prilep', 'Прилеп','70000', '1000', '1');
insert into PLACE (name, name_cyrilic, population, postalcode, countryid) values ('Strumitsa', 'Струмица','70000', '1000', '1');

insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Makedonija Soobracaj', 'Македонија Сообраќај', '1');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Rule Turs', 'Руле Турс', '1');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Vardar Express', 'Вардар Експрес', '1');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Mlaz Bogdanci', 'Млаз Богданци', '1');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Antigona Trans', 'Антигона Транс', '2');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Dajo Turs', 'Дајо Турс', '2');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Pamela', 'Памела', '2');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Sam-Vel', 'Сам-Вел', '3');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Ekstra bus', 'Екстра бус', '3');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Kam Smark DOO', 'Кам Смарк ДОО', '3');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Galeb', 'Галеб', '4');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Delfina Turs', 'Делфина Турс', '4');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Klasik kompani', 'Класик компани', '4');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Transkop', '', '9');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Pelagonija Turs', 'Пелагонија Турс', '10');
insert into CARRIER (name, name_cyrilic, carrierplaceid) values ('Strumica express', 'Струмица екпрес', '11');

insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '08:30', '09:55', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '10:00', '11:25', '100', '1h30m', '1,2,3,4,5', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '11:00', '12:30', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '12:00', '13:30', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '13:00', '14:30', '100', '1h30m', '1,2,3,4,5,6', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '14:00', '15:30', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '15:00', '16:30', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '16:00', '17:30', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '18:00', '19:30', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '18:30', '20:00', '100', '1h30m', '1,2,3,4,5', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '19:30', '21:00', '100', '1h30m', '1,2,3,4,5,6,7', '240', '370', '1', '2', '2');
insert into BUSLINE (name, departuretime, arrivaltime, distance, jurneytime, operationdays, price, priceReturn, departureplaceid, destinationplaceid, carrierid) 
values ('Skopje-Negotino', '20:30', '22:00', '100', '1h30m', '7', '240', '370', '1', '2', '2');