package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Implementation of LeaseDAO interface Implements functionality of lease
 *
 * @author Petr Potucek
 * @since 2014-09
 */
public class LeaseDAOImpl implements LeaseDAO {

    private EntityManagerFactory emf;

    public LeaseDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @Override
    public void createLease(Lease lease) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(lease);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateLease(Lease lease, int leaseId) {
        EntityManager em = emf.createEntityManager();
        

        int carMileage = lease.getCarMileage();
        Date dateOfLease = new Date (lease.getDateOfLease().getTime());
        Date dateOfReturn = new Date (lease.getDateOfReturn().getTime());
        Boolean isClosed = lease.getIsClosed();
        Car car = lease.getCar();
        Person person = lease.getPerson();
        
        em.getTransaction().begin();
        
        Lease lease1 = (Lease)em.find(Lease.class ,leaseId);

        lease1.setCarMileage(carMileage);
        lease1.setDateOfLease(dateOfLease);
        lease1.setDateOfReturn(dateOfReturn);
        lease1.setIsClosed(isClosed);
        lease1.setCar(car);
        lease1.setPerson(person);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteLease(int leaseId) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Lease lease = (Lease)em.find(Lease.class ,leaseId);
        
        em.remove(lease);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List getLeasesByPerson(Integer personId) {

        EntityManager em = emf.createEntityManager();

        String query = "SELECT k FROM Lease k WHERE k.personId='" + personId + "'";//TODO
        List<Lease> leases = em.createQuery(query).getResultList();

        em.getTransaction().commit();
        em.close();

        return leases;
    }

    @Override
    public List getAllLeases(Date from, Date until) {

        EntityManager em = emf.createEntityManager();

        String query = "SELECT * FROM Lease WHERE date BETWEEN '" + from + " ' AND '" + until + " '";
        List<Lease> leases = em.createQuery(query).getResultList();

        em.getTransaction().commit();
        em.close();

        return leases;
    }
    

    @Override
    public Lease getLeaseByID(Integer ID) {
         //create Entity Manager
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementSystem-unit");
        EntityManager em = emf.createEntityManager();

        //begin of a transaction
        em.getTransaction().begin();

        //actual query
        String sql = "SELECT l FROM Lease l WHERE l.id=:leaseID";
        Lease lease = em.createQuery(sql, Lease.class)
                .setParameter("leaseID", ID).getResultList().get(0);

        //commiting changes and closing entity manager
        em.getTransaction().commit();
        em.close();

        return lease;
    }

}
