/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.rest_client;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.rest_messages.ReceiveActiveCarListMessage;
import cz.muni.fi.pa165.rest_messages.ReceiveCarMessage;
import cz.muni.fi.pa165.service.dto.CarDTO;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jozefpuchly
 * @author jrumanov
 */
public class CarClient {

    private static final String FIND_ALL_OPERATION = "findAll";
    private static final String ADD_OPERATION = "add";
    private static final String UPDATE_OPERATION = "update";
    private static final String DELETE_OPERATION = "delete";
    private static final String FIND_BY_ID_OPERATION = "findbyid";

    private static final String CREATE_CAR = "http://localhost:8080/pa165/rest/car/add";
    private static final String GET_CAR = "http://localhost:8080/pa165/rest/car/get?id=%d";
    //how come the update and delete does not need id?
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
    private void handleFindAllOperation() {
        RestTemplate restTemplate = new RestTemplate();
        ReceiveActiveCarListMessage result = restTemplate.postForObject(GET_ALL_CARS, null, ReceiveActiveCarListMessage.class);

        for (CarDTO c : result.getList()) {
            System.out.println(c.toString());
        }

    }

    /**
     * handles create car operation 
     * console command is: 
     * car create [vehicleRegPlate] [brand] [typeName] [VIN] [yearOfManufacture] [bodystyle]
     * [numberOfSeats] [mileage] [color] [category] [emissionstandard]
     * [availibility] [isActive]
     *
     * @param args command line arguments args[0] args[1] args[2] args[3]
     * args[4] args[5] args[6] args[7] args[8] args[9] args[10] args[11]
     * args[12] args[13]
     *
     */
    private void handleAddOperation(String args[]) {
        if (args.length < 15) {
            String requiredArgs = "[vehicleRegPlate] [brand] [typeName] "
                    + "[VIN] [yearOfManufacture] [bodystyle] [numberOfSeats] "
                    + "[mileage] [color] [category] [emissionstandard]"
                    + "[availibility] [isActive]";

            Messages.badNumberOfArgsMessage(args.length, ADD_OPERATION, requiredArgs);
            System.exit(1);
        }

        CarDTO car = new CarDTO();
        car.setVehicleRegPlate(args[2]);
        car.setBrand(args[3]);
        car.setTypeName(args[4]);
        car.setVIN(args[5]);
        car.setYearOfManufacture(Integer.parseInt(args[6]));
        car.setBodystyle(Car.bodyStyle.valueOf(args[7]));
        car.setNumberOfSeats(Integer.parseInt(args[8]));
        car.setMileage(Integer.parseInt(args[9]));
        car.setColor(Car.Color.valueOf(args[10]));
        car.setCategory(Car.Category.valueOf(args[11]));
        car.setEmissionstandard(Car.emissionStandard.valueOf(args[12]));
        car.setAvailibility(Boolean.parseBoolean(args[13]));
        car.setIsActive(Boolean.parseBoolean(args[14]));

        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.postForObject(CREATE_CAR, car, ReceiveCarMessage.class);

        if (result.isSuccess()) {
            System.out.println("Car with registration plate: '" + args[2] + "', brand: '" + args[3] + "', model: '" + args[4] + "', vin: '" + args[5] + "' was created");
            return;
        } else {
            Messages.serverError(result.getMessage());
        }
    }

    /**
     * find car by its id
     * command is: car findbyid [id]
     *
     * @param args car
     */
    private void handleFindById(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
            Messages.badNumberOfArgsMessage(args.length, FIND_BY_ID_OPERATION, requiredArgs);
            System.exit(1);
        }
        int id = -1;
        try {
            id = Integer.parseInt(args[2]);
        } catch (Exception e) {
            Messages.serverError("ID - Wrong argument: " + args[2] + ", must be int");
            return;
        }

        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.getForObject(String.format(GET_CAR, id), ReceiveCarMessage.class);

        CarDTO car = result.getObject();
        System.out.println("Car with id " + args[2] + " is car " + car.getBrand() + " " + car.getTypeName() + " with registration number " + car.getVehicleRegPlate());
    }

    /**
     * handles update car operation 
     * console command is: car update [carID] [mileage] [availability] [isActive]
     *
     * @param args command line arguments args[0] args[1] args[2] args[3]
     * args[4] args[5] 
     * car create id mileage availability isActive
     */
    private void handleUpdateOperation(String[] args) {
        if (args.length < 6) {
            String requiredArgs = "[id] [mileage] [availability] [isActive]";
            Messages.badNumberOfArgsMessage(args.length, UPDATE_OPERATION, requiredArgs);
            System.exit(1);
        }

        CarDTO car = new CarDTO();
        car.setCarID(Integer.parseInt(args[2]));
        car.setMileage(Integer.parseInt(args[3]));
        car.setAvailibility(Boolean.parseBoolean(args[4]));
        car.setIsActive(Boolean.parseBoolean(args[5]));

        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.postForObject(UPDATE_CAR, car, ReceiveCarMessage.class);

        if (result.isSuccess()) {
            System.out.println("Car with id: '" + args[2] + "' was updated");
        } else {
            System.out.println("Car with this ID does not exist");
        }
    }

    /**
     * handles removal of car entity
     * console command is 'car delete id'
     * it only deactivates car
     *
     * @param args command line arguments args[0] args[1] args[2] car delete id
     */
    private void handleDeleteOperation(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "[id]";
            Messages.badNumberOfArgsMessage(args.length, DELETE_OPERATION, requiredArgs);
            System.exit(1);
        }

        int id = -1;

        try {
            id = Integer.parseInt(args[2]);
        } catch (Exception e) {
            Messages.serverError("ID - Wrong argument: " + args[2] + ", must be int");
            return;
        }

        RestTemplate restTemplate = new RestTemplate();
        ReceiveCarMessage result = restTemplate.getForObject(String.format(GET_CAR, id), ReceiveCarMessage.class);

        if (!result.isSuccess()) {
            System.out.println("Car with id " + id + " does not exist.");
            return;
        }

        ReceiveCarMessage result2 = restTemplate.postForObject(DELETE_CAR, result.getObject(), ReceiveCarMessage.class);

        if (result2.isSuccess()) {
            System.out.println("Car with id " + id + " was deleted");
        } else {
            Messages.serverError(result.getMessage());
        }
    }
}
