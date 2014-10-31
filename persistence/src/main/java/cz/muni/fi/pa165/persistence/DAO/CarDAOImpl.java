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

/**
 *
 * @author Jozef Puchly
 */
public class CarDAOImpl implements CarDAO {

    private EntityManagerFactory emf;

    public CarDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @Override
    public void createCar(Car car) {

        // create new EntityManager and save instance of Car to database
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateCar(Car car, Integer carID) {

       //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();    

        //get all updatable attributes from the updated car entity instance
        Integer mileage = car.getMileage();
        Boolean availibility = car.isAvailibility();
        
        //begin of a transaction
        em.getTransaction().begin(); 
        
        //find car to be updated in DB
        Car car1 = (Car)em.find(Car.class ,carID);
        
        //change attributes of persisted entity in DB
        car1.setMileage(mileage);
        car1.setAvailibility(availibility);

        //commiting changes and closing entity manager
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteCar(Integer carID) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        
        Car car = em.find(Car.class, carID);
        
        em.getTransaction().begin();       
        
        em.remove(car);
        
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public List listAllAvailableCars() {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        
        //begin of a transaction
        em.getTransaction().begin();

        // get all cars that are available. save them to list
        String query = "SELECT c FROM Car c WHERE c.availibility = :available";
        List<Car> cars = em.createQuery(query,Car.class).
                setParameter("available", true).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    @Override
    public List getCarByCategory(int category) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        
        //begin of a transaction
        em.getTransaction().begin();

        // get all cars that have the chosen category. save them to list
        String query = "SELECT c FROM Car c where category=:car.Category";
        List<Car> cars = em.createQuery(query, Car.class).
                setParameter("car.Category", category).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    @Override
    public List getCarBySeats(int seats) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        
         //begin of a transaction
        em.getTransaction().begin();

        // get all cars that have the chosen number of seats. save them to list
        String query = "SELECT c FROM Car c where numberOfSeats=:carNumberOfSeats";
        List<Car> cars = em.createQuery(query, Car.class).
                setParameter("carNumberOfSeats", seats).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    @Override
    public List getCarByBodyStyle(Car.bodyStyle bs) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        
        //begin of a transaction
        em.getTransaction().begin();

        // get all cars that have the chosen body style. save them to list
        String query = "SELECT c FROM Car c where c.bodyStyle=:carBodyStyle";
         List<Car> cars = em.createQuery(query, Car.class).
                setParameter("carBodyStyle", bs).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    public Car getCarByID(Integer CarID) {

        //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT c FROM Car c WHERE c.id=:carID";
        Car car = em.createQuery(sql, Car.class)
                .setParameter("carID", CarID).getResultList().get(0);

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();

        return car;
    }
}
