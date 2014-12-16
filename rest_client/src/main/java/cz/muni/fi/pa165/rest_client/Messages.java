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
        System.out.println("usage: rest-client <entity> [commands]\n\n" +
                "objects are: person, car\n\n" +

                "commands for <car> are: \n" +
                "\tfindAll                                                                       print out all cars\n" +
                "\tadd <vehicleRegPlate> <brand> <typeName> <VIN> <yearOfManufacture> "
                    + "<color> <mileage> <bodystyle> <enginePower> <gasConsumption> <transmission> "
                    + "<emissionstandard> <numberOfSeats> <category> <isActive>                           add new car\n" +
                "\tupdate <id> <mileage> <availability> <isActive>                               update car by given id\n" +
                "\tdelete <id>                                                                   delete car by given id\n" +
                "\tfindbyid <id>                                                                 find car by id\n" +
        
                "commands for <person> are: \n" +
                "\tfindAll                                                                       print out all people\n" +
                "\tadd <name> <isActive> <employmentStatus> <dateOfBirth> <position> <sex> <nationality> <salary> <IdentificationNumber>                           add new person\n" +
                "\tupdate <id> <name> <position> <nationality> <salary> <employmentStatus> <isActive> update person by given id\n" +
                "\tdelete <id>                                                                   delete person by given id\n" +
                "\tfindbyname <id>                                                                  delete person by given id\n" +
                "\tfindbyid <id>");
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
        System.out.println("Error occured while processing your request: " + message.toString());
    }
    
}
