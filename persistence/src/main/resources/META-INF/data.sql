INSERT INTO PERSON (ID, EMPLOYMENTSTATUS, IDENTIFICATIONNUMBER, ISACTIVE, NAME, NATIONALITY, POSITION, SALARY, SEX) VALUES (1, 'INTERN', '8601121214', TRUE, 'Aurel Konár', 'SK', 'Quality Assurance', 23465, 'MALE');
INSERT INTO PERSON (ID, EMPLOYMENTSTATUS, IDENTIFICATIONNUMBER, ISACTIVE, NAME, NATIONALITY, POSITION, SALARY, SEX) VALUES (2, 'CEO', '7706123454', TRUE, 'Adalbert Galik', 'AT', 'CEO', 44333, 'MALE');
INSERT INTO PERSON (ID, EMPLOYMENTSTATUS, IDENTIFICATIONNUMBER, ISACTIVE, NAME, NATIONALITY, POSITION, SALARY, SEX) VALUES (3, 'SENIOR', '8754124334', FALSE, 'Nadežda Bulková', 'CZ', 'programmer', 30010, 'FEMALE');

INSERT INTO CAR (CARID, VIN, AVAILIBILITY, BODYSTYLE, BRAND, CATEGORY, COLOR, EMISSIONSTANDARD, ISACTIVE, MILEAGE, NUMBEROFSEATS, TYPENAME, VEHICLEREGPLATE, YEAROFMANUFACTURE) VALUES (1, '439543553498435', 'TRUE', 'CABRIOLET', 'Mazda', 'A', 'BLACK', 'EU3', TRUE, 20333, 2, 'Miata', 'TN434CE', 2004);
INSERT INTO CAR (CARID, VIN, AVAILIBILITY, BODYSTYLE, BRAND, CATEGORY, COLOR, EMISSIONSTANDARD, ISACTIVE, MILEAGE, NUMBEROFSEATS, TYPENAME, VEHICLEREGPLATE, YEAROFMANUFACTURE) VALUES (2, '349242894234234', 'TRUE', 'SEDAN', 'Ford', 'B', 'WHITE', 'EU5', FALSE, 13555, 5, 'Focus', 'BL435AY', 2014);
INSERT INTO CAR (CARID, VIN, AVAILIBILITY, BODYSTYLE, BRAND, CATEGORY, COLOR, EMISSIONSTANDARD, ISACTIVE, MILEAGE, NUMBEROFSEATS, TYPENAME, VEHICLEREGPLATE, YEAROFMANUFACTURE) VALUES (3, '234893454535345', 'TRUE', 'SEDAN', 'Hyundai', 'A', 'GREEN', 'EU3', TRUE, 121234, 5, 'i20', 'KE555FA', 2010);

INSERT INTO LEASE (LEASEID, DATEOFLEASE, DATEOFRETURN, CARMILEAGE, ISCLOSED, RETURNEDSTATUS, TRAVELREASON, CAR_CARID, PERSON_ID) VALUES (1, '2014-01-13', '2014-01-17', 0, NULL, NULL, 'PERSONAL', 1, 2);
INSERT INTO LEASE (LEASEID, DATEOFLEASE, DATEOFRETURN, CARMILEAGE, ISCLOSED, RETURNEDSTATUS, TRAVELREASON, CAR_CARID, PERSON_ID) VALUES (2, '2014-05-13', '2014-05-17', 605, TRUE, 'OK', 'PERSONAL', 2, 1);

INSERT INTO SERVICECHECK (SCID, DESCRIPTION, LASTCHECK, NAME, NEXTCHECK, SERVICEINTERVAL, CAR_CARID) VALUES (1,'','2014-11-22','TECHNICAL','2015-11-22', 12, 1);
INSERT INTO SERVICECHECK (SCID, DESCRIPTION, LASTCHECK, NAME, NEXTCHECK, SERVICEINTERVAL, CAR_CARID) VALUES (2,'','2014-11-22','EMISSION','2016-11-22', 24, 2);