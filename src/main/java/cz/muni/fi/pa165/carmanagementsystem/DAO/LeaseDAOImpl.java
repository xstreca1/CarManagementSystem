package cz.muni.fi.pa165.carmanagementsystem.DAO;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Car;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Lease;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Implementation of LeaseDAO interface
 * Implements functionality of lease
 * 
 * @author Petr Potucek
 * @since       2014-09   
 */
public class LeaseDAOImpl implements LeaseDAO {
     
    @Override
    public void createLease(Lease lease){     
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("createSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(lease);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void updateLease(Lease lease, int leaseId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("updateSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String query = "SELECT * FROM Lease WHERE leaseId='" + leaseId + "'";
        Lease update = em.createQuery(query, Lease.class).getSingleResult();
     
        int carMileage = lease.getCarMileage();
        Date dateOfLease = lease.getDateOfLease();
        Date dateOfReturn = lease.getDateOfReturn();
        String vehicleRegPlate = lease.getVehicleRegPlate();
        Boolean isClosed = lease.getIsClosed();
        Car car = lease.getCar();
        Person person = lease.getPerson();

        update.setCarMileage(carMileage);
        update.setDateOfLease(dateOfLease);
        update.setDateOfReturn(dateOfReturn);
        update.setVehicleRegPlate(vehicleRegPlate);
        update.setIsClosed(isClosed);
        update.setCar(car);
        update.setPerson(person);
            
        em.persist(update);
        em.getTransaction().commit();
        em.close();
    }
    
    
    @Override
    public void deleteLease(int leaseId){
 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("deleteSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String query = "DELETE * FROM Lease WHERE leaseId='" + leaseId + "'";
        em.createQuery(query).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List getLeasesByPerson(String personId){
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getSCforLease-unit");
        EntityManager em = emf.createEntityManager();

        String query = "SELECT * FROM Lease WHERE personId='" + personId + "'";//TODO
        List<Lease> leases = em.createQuery(query).getResultList();

        em.getTransaction().commit();
        em.close();

        return leases;
    }
    
    @Override
    public List getAllLeases(Date from, Date until){
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getSCforLease-unit");
        EntityManager em = emf.createEntityManager();

        String query = "SELECT * FROM Lease WHERE date BETWEEN '"+ from +" ' AND '"+ until +" '";
        List<Lease> leases = em.createQuery(query).getResultList();

        em.getTransaction().commit();
        em.close();

        return leases;
    }  
    
}