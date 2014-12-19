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
                handleUpdateOperation(args);
                break;

            case DELETE_OPERATION:
                handleDeleteOperation(args);
                break;

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

    /**
     * handles 'findAll' console command
     */
    private void handleFindAllOperation() {
        RestTemplate restTemplate = new RestTemplate();
        ReceiveActivePeopleListMessage result = restTemplate.postForObject(GET_ALL_PEOPLE, null, ReceiveActivePeopleListMessage.class);

        for (PersonDTO p : result.getList()) {
            System.out.println(p.toString());
        }
    }

    /**
     * handles add person operation console command is: person add [name]
     * [IdentificationNumber] [sex] [nationality] [position] [employmentStatus]
     * [salary] [isActive]
     *
     * @param args command line arguments args[0] args[1] args[2] args[3]
     * args[4] args[5] args[6] args[7] args[8] args[9]
     *
     */
    private void handleAddOperation(String[] args) {
        if (args.length < 10) {
            String requiredArgs = "<name> <IdentificationNumber> <sex> <nationality>"
                    + "<position> <employmentStatus> <salary> <isActive> ";
            Messages.badNumberOfArgsMessage(args.length, ADD_OPERATION, requiredArgs);
            System.exit(1);
        }

        PersonDTO person = new PersonDTO();
        person.setName(args[2]);
        person.setIdentificationNumber(args[3]);
        person.setSex(Person.Sex.valueOf(args[4]));
        person.setNationality(args[5]);
        person.setPosition(args[6]);
        person.setEmploymentStatus(Person.EmploymentStatus.valueOf(args[7]));
        person.setSalary(Integer.parseInt(args[8]));
        person.setIsActive(Boolean.valueOf(args[9]));

        RestTemplate restTemplate = new RestTemplate();
        ReceivePersonMessage result = restTemplate.postForObject(CREATE_PERSON, person, ReceivePersonMessage.class);

        if (result.isSuccess()) {
            System.out.println("Person with Identification number: '" + args[10] + "'and name: '" + args[2] + "' was created");
            return;
        } else {
            Messages.serverError(result.getMessage());
        }
    }

    /**
     * handles update person operation console command is: person update [id]
     * [name] [position] [nationality] [salary] [employmentStatus] [isActive]
     *
     * @param args command line arguments args[0] args[1] args[2] args[3]
     * args[4] args[5] args[6] args[7] person update id name position
     * nationality salary employmentStatus isActive
     */
    private void handleUpdateOperation(String[] args) {
        if (args.length < 8) {
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

    /**
     * handles removal of person entity console command is 'person delete id' it
     * only deactivates person
     *
     * @param args command line arguments args[0] args[1] args[2] person delete
     * id
     */
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

    /**
     * find car by its id command is: person findbyid [id]
     *
     * @param args List<person>
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
        ReceivePersonMessage result = restTemplate.getForObject(String.format(GET_PERSON, id), ReceivePersonMessage.class);

        PersonDTO person = result.getObject();
        System.out.println("Person with id " + args[2] + " is person " + person.getName() + " with identification number " + person.getIdentificationNumber());
    }

    /**
     * find person by name 
     * command is: person findbyname [name]
     *
     * @param args person
     */
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
