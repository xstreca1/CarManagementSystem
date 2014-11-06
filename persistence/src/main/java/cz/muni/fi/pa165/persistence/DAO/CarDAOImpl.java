/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jozef Puchly
 */
public class CarDAOImpl implements CarDAO {

    @PersistenceContext(name = "carManagementSystem-unit")
    private EntityManager em;

    //no parameter constructor

    public CarDAOImpl() {

    }

    public CarDAOImpl(EntityManager entityManager) {
        if (entityManager == null) {
            throw new IllegalArgumentException("argument 'em' must be set");
        }
        em = entityManager;
    }

    @Override
    public Car createCar(Car car) {

        // save car to DB if it null throw exception
        if (car == null) {
            throw new IllegalArgumentException("unset argument car");
        }
        em.persist(car);

        return car;
    }

    @Override
    public void updateCar(Car car, Integer carID) {

        //get all updatable attributes from the updated car entity instance
        Integer mileage = car.getMileage();
        Boolean availibility = car.isAvailibility();
        Boolean isActive = car.isIsActive();

        //find car to be updated in DB
        Car car1 = (Car) em.find(Car.class, carID);

        //change attributes of persisted entity in DB
        car1.setMileage(mileage);
        car1.setAvailibility(availibility);
        car1.setIsActive(isActive);

    }

    @Override
    public void deleteCar(Integer carID) {
        if (carID == null) {
            throw new IllegalArgumentException("unset argument carID'");
        }

        Car car = em.find(Car.class, carID);

        em.remove(car);

    }

    @Override
    public List listAllAvailableCars() {

        // get all cars that are available. save them to list
        String query = "SELECT c FROM Car c WHERE c.availibility = :available";
        List<Car> cars = em.createQuery(query, Car.class).
                setParameter("available", true).getResultList();

        return cars;
    }

    @Override
    public List listAllCars(boolean alsoInactive) {

        String query = "SELECT c FROM Car c WHERE c.isActive = :alsoInactive";
        List<Car> cars = em.createQuery(query, Car.class).
                setParameter("alsoInactive", alsoInactive).getResultList();

        return cars;
    }

    @Override
    public List getCarByCategory(int category) {

        // get all cars that have the chosen category. save them to list
        String query = "SELECT c FROM Car c where category=:car.Category";
        List<Car> cars = em.createQuery(query, Car.class).
                setParameter("car.Category", category).getResultList();

        return cars;
    }

    @Override
    public List getCarBySeats(int seats) {

        // get all cars that have the chosen number of seats. save them to list
        String query = "SELECT c FROM Car c where numberOfSeats=:carNumberOfSeats";
        List<Car> cars = em.createQuery(query, Car.class).
                setParameter("carNumberOfSeats", seats).getResultList();

        return cars;
    }

    @Override
    public List getCarByBodyStyle(Car.bodyStyle bs) {

        // get all cars that have the chosen body style. save them to list
        String query = "SELECT c FROM Car c where c.bodyStyle=:carBodyStyle";
        List<Car> cars = em.createQuery(query, Car.class).
                setParameter("carBodyStyle", bs).getResultList();

        return cars;
    }
    
    @Override
    public Car getCarByID(Integer CarID) {

        //actual query
        String sql = "SELECT c FROM Car c WHERE c.id=:carID";
        Car car = em.createQuery(sql, Car.class)
                .setParameter("carID", CarID).getResultList().get(0);

        return car;
    }
}
