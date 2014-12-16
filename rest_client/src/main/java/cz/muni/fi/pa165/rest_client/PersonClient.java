/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 args
 id name isActive employmentStatus dateOfBirth position sex
 address nationality salary identificationNumber
 *
 */
package cz.muni.fi.pa165.rest_client;

import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.rest_messages.ReceiveActivePeopleListMessage;
import cz.muni.fi.pa165.rest_messages.ReceivePersonMessage;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jozefpuchly
 * @author jrumanov
 */
public class PersonClient {

    private static final String FIND_ALL_OPERATION = "findAll";
    private static final String ADD_OPERATION = "add";
    private static final String UPDATE_OPERATION = "update";
    private static final String DELETE_OPERATION = "delete";
    private static final String FIND_BY_ID_OPERATION = "findbyid";
    private static final String FIND_BY_NAME_OPERATION = "findbyname";

    private static final String CREATE_PERSON = "http://localhost:8080/pa165/rest/person/add";
    private static final String GET_PERSON = "http://localhost:8080/pa165/rest/person/get?id=%d";
    private static final String GET_PERSON_NAME = "http://localhost:8080/pa165/rest/person/get?id=%s";
    private static final String UPDATE_PERSON = "http://localhost:8080/pa165/rest/person/update";
    private static final String DELETE_PERSON = "http://localhost:8080/pa165/rest/person/delete";
    private static final String GET_ALL_PEOPLE = "http://localhost:8080/pa165/rest/person/getAll";

    public PersonClient(String[] args) {

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

            case FIND_BY_NAME_OPERATION:
                handleFindByName(args);
                break;

            default:
                Messages.unknownOperationMessage(operation);
                System.exit(1);
        }

    }

    private void handleFindAllOperation() {
        RestTemplate restTemplate = new RestTemplate();
        ReceiveActivePeopleListMessage result = restTemplate.postForObject(GET_ALL_PEOPLE, null, ReceiveActivePeopleListMessage.class);

        for (PersonDTO p : result.getList()) {
            System.out.println(p.toString());
        }
    }

    private void handleAddOperation(String[] args) {
        if (args.length < 11) {
            String requiredArgs = "<name> <isActive> <employmentStatus> <dateOfBirth> <position> "
                    + "<sex> <nationality> <salary> <IdentificationNumber>";
            Messages.badNumberOfArgsMessage(args.length, ADD_OPERATION, requiredArgs);
            System.exit(1);
        }

        PersonDTO person = new PersonDTO();
        person.setName(args[2]);
        person.setIsActive(Boolean.valueOf(args[3]));
        person.setEmploymentStatus(Person.EmploymentStatus.valueOf(args[4]));
        //convert date to string?
        //person.setDateOfBirth(args[5]);
        person.setPosition(args[6]);
        person.setSex(Person.Sex.valueOf(args[7]));
        person.setNationality(args[8]);
        person.setSalary(Integer.parseInt(args[9]));
        person.setIdentificationNumber(args[10]);

        RestTemplate restTemplate = new RestTemplate();
        ReceivePersonMessage result = restTemplate.postForObject(CREATE_PERSON, person, ReceivePersonMessage.class);

        if (result.isSuccess()) {
            System.out.println("Person with Identification number: '" + args[10] + "'and name: '" + args[2] + "' was created");
            return;
        } else {
            Messages.serverError(result.getMessage());
        }
    }

    private void handleUpdateOperation(String[] args) {
        if (args.length < 7) {
            String requiredArgs = "<id> <name> <position> <nationality> <salary> <employmentStatus> <isActive>";
            Messages.badNumberOfArgsMessage(args.length, UPDATE_OPERATION, requiredArgs);
            System.exit(1);
        }

        PersonDTO person = new PersonDTO();
        person.setName(args[2]);
        person.setPosition(args[3]);
        person.setNationality(args[4]);
        person.setSalary(Integer.parseInt(args[5]));
        person.setEmploymentStatus(Person.EmploymentStatus.valueOf(args[6]));
        person.setIsActive(Boolean.valueOf(args[7]));

        RestTemplate restTemplate = new RestTemplate();
        ReceivePersonMessage result = restTemplate.postForObject(UPDATE_PERSON, person, ReceivePersonMessage.class);

        if (result.isSuccess()) {
            System.out.println("Person with id: '" + args[1] + "' was updated");
        } else {
            System.out.println("Person with this ID does not exist");
        }
    }

    private void handleDeleteOperation(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<id>";
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
        ReceivePersonMessage result = restTemplate.getForObject(String.format(GET_PERSON, id), ReceivePersonMessage.class);

        if (!result.isSuccess()) {
            System.out.println("Person with id " + id + " does not exist.");
            return;
        }

        ReceivePersonMessage result2 = restTemplate.postForObject(DELETE_PERSON, result.getObject(), ReceivePersonMessage.class);

        if (result2.isSuccess()) {
            System.out.println("Person with id " + id + " was deleted");
        } else {
            Messages.serverError(result.getMessage());
        }
    }

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
        ReceivePersonMessage result = restTemplate.getForObject(String.format(GET_PERSON, id), ReceivePersonMessage.class);

        PersonDTO person = result.getObject();
        System.out.println("Person with id " + args[2] + " is person " + person.getName() + " with identification number " + person.getIdentificationNumber());
    }

    private void handleFindByName(String[] args) {
        if (args.length < 3) {
            String requiredArgs = "<name>";
            Messages.badNumberOfArgsMessage(args.length, FIND_BY_NAME_OPERATION, requiredArgs);
            System.exit(1);
        }
        String name = args[2];

        RestTemplate restTemplate = new RestTemplate();
        ReceiveActivePeopleListMessage result = restTemplate.getForObject(String.format(GET_PERSON_NAME, name), ReceiveActivePeopleListMessage.class);

        for (PersonDTO p : result.getList()) {
            System.out.println(p.toString());
        }
    }
}