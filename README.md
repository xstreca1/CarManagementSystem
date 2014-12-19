				Car Management System

1.) running the web

	a) change directory to projectDir [cd ../CarManagementSystem/]

	b) build the project using maven [mvn install]

	c) run the pa165 database from Netbeans [username/password = pa165]

	d) change directory to web module [cd ../CarManagementSystem/web]

	e) run the web server using tomcat7 [mvn tomcat7:run]



2.) insert example data in the database

	a) change directory to persistence module [cd ../CarManagementSystem/persistence]

	b) insert prepared sql commands into database [mvn sql:execute]



3.) running the rest CLI client

	a) first, perform the step 1 and 2 mentioned above

	b) change directory to rest_client module [cd ../CarManagementSystem/rest_client]

	c) run the rest CLI client using maven [mvn exec:java -Dexec.args="arguments"]


3.1.) using the rest client
	
You can see help by executing mvn exec:java without arguments

================HELP============================================

entities: person, car

commands for [car] are: 

- "findAll"  print out all cars
- "add [vehicleRegPlate] [brand] [typeName] [VIN] [yearOfManufacture] [bodystyle] [numberOfSeats] [mileage] [color] [category] [emissionStandard] [availability] [isActive]" add new car
- "update [id] [mileage] [availability] [isActive]"  update car by given id
- "delete [id]"    delete car by given id (by deactivating it - it still remains in Database)
- "findbyid [id]" find car by id

ENUMS:

[color] = [BLACK, WHITE, BLUE, GREEN]

[body Style] = [SEDAN, HATCHBACK, CABRIOLET, SUV]

[category] = [A, B, C, D]

[emissionstandard] = [EU3, EU4, EU5, EU6]



commands for [person] are:
 
- "findAll" print out all people
- "add [name] [IdentificationNumber] [sex] [nationality] [position]  [employmentStatus] [salary] [isActive]" add new person
- "update [id] [name] [position] [nationality] [salary] [employmentStatus] [isActive]" update person by given id
- "delete [id]" delete person by given id
- "findbyid [id]" find person by given id
- "findbyname [name]" find people by given name

ENUMS

[employmentStatus] = [CEO, MANAGER, SENIOR, JUNIOR, INTERN, JOZO]

[sex] = [MALE, FEMALE, OTHER]

========================================================================

3.2.) Example of use

person - list all people in the DB
- mvn exec:java -Dexec.args="person findAll"

person - add new person
- mvn exec:java -Dexec.args="person add Jaroslav_Cimrman EC444555 MALE SK programmer INTERN 14000 TRUE"

-- before inserting in the DB the "_" character is replaced by " "

car
- mvn exec:java -Dexec.args="car findbyid 1" - in case it exists

car
- mvn exec:java -Dexec.args="car update 1 14000 TRUE TRUE" in case it exists
