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

    private static final String CREATE_PERSON = "http://localhost:8080/pa165/rest/person/add";
    private static final String GET_PERSON = "http://localhost:8080/pa165/rest/person/get?id=%d";
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

            default:
                Messages.unknownOperationMessage(operation);
                System.exit(1);
        }

    }

}
