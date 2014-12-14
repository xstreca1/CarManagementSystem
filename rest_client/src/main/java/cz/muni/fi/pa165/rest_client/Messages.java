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
                "\tadd <regNumber> <type> <model> <vin> <fuel> <color>                           add new car\n" +
                "\tupdate <id> <newRegNumber> <newType> <newModel> <newVin> <newFuel> <newColor> update car by given id\n" +
                "\tdelete <id>                                                                   delete car by given id\n" +
                "\tfindbyid <id>                                                                 find car by id\n");
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
        System.out.println("first argument has to be <branch> or <car>");
    }
    
    public static void serverError(String message) {
        System.out.println("Error occured while processing your request: " + message.toString());
    }
    
}
