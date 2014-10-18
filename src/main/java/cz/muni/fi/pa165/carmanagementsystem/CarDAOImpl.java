/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 *
 * @author Jozef Puchly
 */
public class CarDAOImpl implements CarDAO {

    @Override
    public void createCar(Car car) {
         // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        // create new EntityManager and save instance of Car to database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("createCar-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateCar(Car car, String vehicleRegPlate) {
        // start in memory database
        new AnnotationConfigApplicationContext(DaoContext.class);

        // create new EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("updateSC-unit");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List listAllAvailableCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getCarByCategory(int category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getCarBySeats(int seats) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getCarByBodyStyle(Car.bodyStyle bs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
