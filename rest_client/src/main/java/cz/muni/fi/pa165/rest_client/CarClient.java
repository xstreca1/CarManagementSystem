/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest_client;

import cz.muni.fi.pa165.rest_messages.ReceiveActiveCarListMessage;
import cz.muni.fi.pa165.rest_messages.ReceiveCarMessage;
import cz.muni.fi.pa165.service.dto.CarDTO;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jozefpuchly
 */
public class CarClient {
    
    private static final String FIND_ALL_OPERATION = "findAll";
    private static final String ADD_OPERATION = "add";
    private static final String UPDATE_OPERATION = "update";
    private static final String DELETE_OPERATION = "delete";
    private static final String FIND_BY_ID_OPERATION = "findbyid";
    
    private static final String CREATE_CAR = "http://localhost:8080/pa165/rest/car/add";
    private static final String GET_CAR = "http://localhost:8080/pa165/rest/car/get?id=%d";
    private static final String UPDATE_CAR = "http://localhost:8080/pa165/rest/car/update";
    private static final String DELETE_CAR = "http://localhost:8080/pa165/rest/car/delete";
    private static final String GET_ALL_CARS = "http://localhost:8080/pa165/rest/car/getAll";
    
    public CarClient(String[] args) {
        
        if (args.length < 2) {
            Messages.badNumberOfArgsMessage(args.length);
            System.exit(1);
        }
        
        String operation = args[1];
        
        switch (operation) {

            case FIND_ALL_OPERATION:
                handleFindAllOperation();
                break;

            case ADD_OPERATION:
                handleAddOperation(args);
                break;

            case UPDATE_OPERATION:
                // update <id> <newRegistrationNumber> <newType> <newModel> <newVin> <newFuel> <newColor>
                handleUpdateOperation(args);
                break;

            case DELETE_OPERATION:
                handleDeleteOperation(args);
                break;

            // find <id>
            case FIND_BY_ID_OPERATION:
                handleFindById(args);
                break;


            default:
                Messages.unknownOperationMessage(operation);
                System.exit(1);
        }
        
    }
    
    /**
     * handles 'findAll' console command
     */
    private void handleFindAllOperation()
    {
        RestTemplate restTemplate = new RestTemplate();
        ReceiveActiveCarListMessage result = restTemplate.postForObject(GET_ALL_CARS, null, ReceiveActiveCarListMessage.class);
        
        
            for(CarDTO c : result.getList())
                System.out.println(c.toString());
      
    }

    /**
     * handles create car operation
     * console command is: car create <registrationNumber> <type> <model> <vin> <fuel> <color> 
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]              args[3]  args[4]  args[5] args[6]  args[7]
     *             car      create    registrationNumber   type     model    vin     fuel     color
     */
    private void handleAddOperation(String args[])
    {
        if (args.length < 8) {
            String requiredArgs = "<registrationNumber> <type> <model> <vin> <fuel> <color>";
            Messages.badNumberOfArgsMessage(args.length, ADD_OPERATION, requiredArgs);
            System.exit(1);
        }
        CarDTO car = new CarDTO();
        car.setRegistrationNumber(args[2]);
        car.setType(args[3]);
        car.setModel(args[4]);
        car.setVin(args[5]);
        car.setFuel(args[6]);
        car.setColor(args[7]);
        
        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.postForObject(CREATE_CAR, car, ReceiveCarMessage.class);
        
        if(result.isSuccess())
        {
            System.out.println("Car with RegistrationNumber: '" + args[2] + "', type: '" + args[3] + "', model: '" + args[4] +"', vin: '" + args[5] +"' was created");
            return;
        } else {
         Messages.serverError(result.getMessage());
        }
    }

    /**
     * find carits id
     *
     * @param args car
     */
    private void handleFindById(String[] args)
    {
         if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, FIND_BY_ID_OPERATION, requiredArgs);
            System.exit(1);
        }
        int id = -1;
        try{
        id = Integer.parseInt(args[2]);
        }catch (Exception e){
            Messages.serverError("ID - Wrong argument: " + args[2] + ", must be int");
            return;
        }
        
        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.getForObject(String.format(GET_CAR, id), ReceiveCarMessage.class);
        
            CarDTO car = result.getObject();
            System.out.println("Car with id "+args[2]+" is car "+car.getType()+" "+car.getModel()+" with registration number "+car.getRegistrationNumber()+" and color "+car.getColor());
    }
 
    /**
     * handles update carration
     * console command is: car update <id> <registrationNumber> <type> <model> <vin> <fuel> <color> 
     *
     * @param args command line arguments
     *             args[0]  args[1]  args[2]   args[3]              args[4]  args[5]  args[6] args[7]  args[8]
     *             car      create   id        registrationNumber   type     model    vin     fuel     color
     */
    private void handleUpdateOperation(String[] args)
    {
        if (args.length < 9) {
            String requiredArgs = "<id> <newRegistrationNumber> <newType> <newModel> <newVin> <newFuel> <newColor>";
            Messages.badNumberOfArgsMessage(args.length, UPDATE_OPERATION, requiredArgs);
            System.exit(1);
        }
        
        CarDTO car = new CarDTO();
        car.setId(Integer.parseInt(args[2]));
        car.setRegistrationNumber(args[3]);
        car.setType(args[4]);
        car.setModel(args[5]);
        car.setVin(args[6]);
        car.setFuel(args[7]);
        car.setColor(args[8]);
        
        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.postForObject(UPDATE_CAR, car, ReceiveCarMessage.class);
        
        if(result.isSuccess())
        {
               System.out.println("Car with id: '" + args[2] + "' was updated");
        }
        else System.out.println("Car with this ID does not exist");
    }

    /**
     * handles removal of car entity, command name 'delete'
     *
     * @param args command line arguments
     *             args[0]  args[1]   args[2]
     *             car      delete    id
     */
    private void handleDeleteOperation(String[] args)
    {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, DELETE_OPERATION, requiredArgs);
            System.exit(1);
        }
        
        int id =-1;
        
        try{
        id = Integer.parseInt(args[2]);
        }catch (Exception e){
            Messages.serverError("ID - Wrong argument: " + args[2] + ", must be int");
            return;
        }
        
        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.getForObject(String.format(GET_CAR, id), ReceiveCarMessage.class);
        
        if(!result.isSuccess())
        {
            System.out.println("Car with id "+id+" does not exist.");
            return;
        }
        
        ReceiveCarMessage result2 = restTemplate.postForObject(DELETE_CAR, result.getObject(), ReceiveCarMessage.class);
        
        if(result2.isSuccess())
        {
            System.out.println("Car with id "+id+" was deleted");
        }
        else { Messages.serverError(result.getMessage()); }
    }
    }
    
    

