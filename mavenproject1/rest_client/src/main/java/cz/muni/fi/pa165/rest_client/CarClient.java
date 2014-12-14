package cz.muni.fi.pa165.rest_client;

/**
 *CLI client
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
        
    }
    
    
    
}
