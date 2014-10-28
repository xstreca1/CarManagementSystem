/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem.DAO;

import cz.muni.fi.pa165.carmanagementsystem.Entities.Car;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Person;
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
    public void updateCar(Car car, String vehicleRegPlate) {

        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // get instance of Car according to its vehicle registration plate. Save this instance to variable "update"
        String query = "SELECT * FROM Car car WHERE vehicleRegPlate='" + vehicleRegPlate + "'";
        Car update = em.createQuery(query, Car.class).getSingleResult();

        // get new values of attributes
        int mileage = car.getMileage();
        boolean availibility = car.isAvailibility();

        // replace actual values with new values
        update.setMileage(mileage);
        update.setAvailibility(availibility);

        // save updated serviceCheck to database
        em.persist(update);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteCar(String vehicleRegPlate) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // delete serviceCheck from database according to its ID
        String query = "DELETE * FROM Car WHERE vehicleRegPlate='" + vehicleRegPlate + "'";
        em.createQuery(query).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List listAllAvailableCars() {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();

        // get all cars that are available. save them to list
        String query = "SELECT * FROM Car where isAvailibility=true";
        List<Car> cars = em.createQuery(query).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    @Override
    public List getCarByCategory(int category) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();

        // get all cars that have the chosen category. save them to list
        String query = "SELECT * FROM Car where category='" + category + "'";
        List<Car> cars = em.createQuery(query).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    @Override
    public List getCarBySeats(int seats) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();

        // get all cars that have the chosen number of seats. save them to list
        String query = "SELECT * FROM Car where numberOfSeats='" + seats + "'";
        List<Car> cars = em.createQuery(query).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    @Override
    public List getCarByBodyStyle(Car.bodyStyle bs) {
        // create new EntityManager
        EntityManager em = emf.createEntityManager();

        // get all cars that have the chosen body style. save them to list
        String query = "SELECT * FROM Car where bodyStyle='" + bs + "'";
        List<Car> cars = em.createQuery(query).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return cars;
    }

    public Person getCarByID(Integer CarID) {

        //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT c FROM Car c WHERE c.id=:carID";
        Person person = em.createQuery(sql, Person.class)
                .setParameter("carID", CarID).getResultList().get(0);

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();

        return person;
    }
}
