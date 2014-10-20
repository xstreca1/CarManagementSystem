package cz.muni.fi.pa165.carmanagementsystem.DAO;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Car;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Lease;
import cz.muni.fi.pa165.carmanagementsystem.Entities.Person;
import cz.muni.fi.pa165.carmanagementsystem.Entities.ServiceCheck;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Petr Potucek
 */
public class LeaseDAOImpl implements LeaseDAO {
    
        /**
         * This method create a lease in database
         * It gets a lease to be stored in database
         * It uses EntityManagerFactory
         * 
         * @param lease instance of lease to be stored
         */
        @Override
        public void createLease(Lease lease){     
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("createSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(lease);
        em.getTransaction().commit();
        em.close();
    }
    
        /**
         * This method update an existing lease
         * It search the right lease in the database bz leaseId, which 
         * has to bee given
         * 
         * @param lease lease with new data
         * @param leaseId lease to be updated, searched by ID
         */
        @Override
        public void updateLease(Lease lease, int leaseId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("updateSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String query = "SELECT * FROM Lease lease WHERE leaseId='" + leaseId + "'";
        Lease update = em.createQuery(query, Lease.class).getSingleResult();

     
        int carMileage = lease.getCarMileage();
        Date dateOfLease = lease.getDateOfLease();
        Date dateOfReturn = lease.getDateOfReturn();
        String vehicleRegPlate = lease.getVehicleRegPlate();
        Boolean isClosed = lease.getIsClosed();
        Car car = lease.getCar();
        Person person = lease.getPerson();


        // replace actual values with new values
        update.setCarMileage(carMileage);
        update.setDateOfLease(dateOfLease);
        update.setDateOfReturn(dateOfReturn);
        update.setVehicleRegPlate(vehicleRegPlate);
        update.setIsClosed(isClosed);
        update.setCar(car);
        update.setPerson(person);

        

        // save updated serviceCheck to database
        em.persist(update);
        em.getTransaction().commit();
        em.close();
    }
    
    
    @Override
    public void deleteLease(int leaseId){
 
        // create new EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("deleteSC-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // delete serviceCheck from database according to its ID
        String query = "DELETE * FROM Lease WHERE leaseId='" + leaseId + "'";
        em.createQuery(query).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * List all leases for one person, chosen bz persons Id
     * 
     * @param personId id of person go list the leases for
     * @return list of leasesfor the person
     */
    @Override
    public List getLeasesByPerson(String personId){
    
        // create new EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getSCforLease-unit");
        EntityManager em = emf.createEntityManager();

        String query = "SELECT * FROM Lease WHERE personId='" + personId + "'";//TODO
        List<ServiceCheck> leases = em.createQuery(query).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return leases;
    }
    
        /**
         * It shows all leases between two given dates.
         * 
         * @param from date from the leases should be
         * @param until date to which the leases were made
         * @return list of leases
         */
    
        @Override
        public List getAllLeases(Date from, Date until){
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getSCforLease-unit");
        EntityManager em = emf.createEntityManager();

        String query = "SELECT * FROM ServiceCheck WHERE date BETWEEN '"+ from +" ' AND '"+ until +" '";
        List<ServiceCheck> leases = em.createQuery(query).getResultList();

        // cloese EntityManager
        em.getTransaction().commit();
        em.close();

        return leases;
    }  
    
}