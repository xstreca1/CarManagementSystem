/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest_client;

import java.net.URL;

/**
 *
 * @author jozefpuchly
 */
public class App {
    
    private static String entityArg = "";
    
    public static void main(String[] args) {
        
    
        try {
        
            new URL("http://localhost:8080/pa165/").openConnection().connect();

        
        if (args.length == 0) {
            // show help table when called with no args
            Messages.printHelp();
            System.exit(1);

        } else {
            // save first argument == entity name
            entityArg = args[0];
        }
        


        if (entityArg.equals("branch")) {
            // test if first entity argument is correct
            new PersonClient(args);

        } else if (entityArg.equals("car")) {
            new CarClient(args);

        } else {
            Messages.badFirstArgumentMessage();
            System.exit(1);
        }
        
    }
     catch (Exception e) {
            Messages.serverError(e.getMessage());
        }
    }
    
}
