/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest_client;

/**
 *
 * @author jozefpuchly
 */
public class Messages {
    
    public static void printHelp() {
        System.out.println("usage: <entity> [command] [attributes]\n\n" +
                
                "entities you can handle: person, car\n\n" +

                "For <car> : \n\n" +
                "\t\'findAll\' print all cars\n" +
                "\t\'add <vehicleRegPlate> <brand> <typeName> <VIN> <yearOfManufacture> "
                + "<bodystyle> <numberOfSeats> <mileage> <color> <category> "
                + "<emissionStandard> <availability> <isActive>\' add new car\n" +
                "\t\'update <id>\' <mileage> <availability> <isActive> update car by given id\n" +
                "\t\'delete <id>\' delete car by given id\n" +
                "\t\'findbyid <id>\' find car by id\n" +
        
                "For <person> : \n\n" +
                "\t\'findAll\' print out all people\n" +
                "\t\'add <name> <IdentificationNumber> <sex> <nationality> <position>  <employmentStatus> <salary> <isActive>\' add new person\n" +
                "\t\'update <id>\' <name> <position> <nationality> <salary> <employmentStatus> <isActive>\' update person by given id\n" +
                "\t\'delete <id>\' delete person by given id\n" +
                "\t\'findbyname <id>\' find person by given id\n" +
                "\t\'findbyid <id>\' find person by given name\n");
    }

    /**
     * prints error message if case of some unknown operation
     *
     * @param operation string of operation
     */
    public static void unknownOperationMessage(String operation) {
        System.out.println("Unknown operation: " + operation + "\n");
        printHelp();
    }

    public static void badNumberOfArgsMessage(int inputedArgs, String operation, String requiredArgs) {
        inputedArgs -= 2;
        System.out.println("\nBad number of arguments. You entered only: " + inputedArgs + " argument(s)\n" +
                "Operation " + operation + " requires arguments: " + requiredArgs + "\n");
    }

    public static void badNumberOfArgsMessage(int inputedArgs) {
        System.out.println("Bad number of arguments. You entered " + inputedArgs + "\n");
        printHelp();
    }

    public static void badFirstArgumentMessage() {
        System.out.println("first argument has to be <person> or <car>");
    }
    
    public static void serverError(String message) {
        System.out.println("Error occured while processing your request: " + message);
    }
    
}
