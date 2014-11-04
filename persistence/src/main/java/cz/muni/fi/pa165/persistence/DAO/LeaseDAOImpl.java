package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of LeaseDAO interface Implements functionality of lease
 *
 * @author Petr Potucek
 * @since 2014-09
 */
public class LeaseDAOImpl implements LeaseDAO {

    @PersistenceContext(name="carManagementSystem-unit")
    private EntityManager em;

    public LeaseDAOImpl(EntityManager entityMf) {
        if (entityMf == null) throw new IllegalArgumentException("the argument must be set");

        em = entityMf;
    }

    public EntityManager getEntityManagerFactory() {
        return em;
    }

    @Override
    public void createLease(Lease lease) {
        if (lease == null) throw new IllegalArgumentException("Lease is null");
 
        em.persist(lease); 
    }

    @Override
    public void updateLease(Lease lease, int leaseId) {  
        if (lease == null) throw new IllegalArgumentException("lease is null");
        if (leaseId < 0 ) throw new IllegalArgumentException("Wrong input for id");
        
        int carMileage = lease.getCarMileage();
        Date dateOfLease = lease.getDateOfLease();
        Date dateOfReturn = lease.getDateOfReturn();
        Boolean isClosed = lease.getIsClosed();
        Car car = lease.getCar();
        Person person = lease.getPerson();
           
        Lease lease1 = (Lease)em.find(Lease.class ,leaseId);

        lease1.setCarMileage(carMileage);
        lease1.setDateOfLease(dateOfLease);
        lease1.setDateOfReturn(dateOfReturn);
        lease1.setIsClosed(isClosed);
        lease1.setCar(car);
        lease1.setPerson(person);
    }

    @Override
    public void deleteLease(int leaseId) {
        if (leaseId < 0) throw new IllegalArgumentException("Wrong input for id");
        
        Lease lease = (Lease)em.find(Lease.class ,leaseId);  
        em.remove(lease);
    }
    
    @Override
    public List getLeasesByPerson(Person person) {
        if (person == null) throw new IllegalArgumentException("person is null");
        
        String query = "SELECT k FROM Lease k WHERE k.person= :person";//TODO
        List<Lease> leases = em.createQuery(query).setParameter("person", person).getResultList();

        return leases;
    }

    @Override
    public List getAllLeases(Date from, Date until) {
        if (from == null) throw new IllegalArgumentException("Wrong input for date from");
        if (until == null) throw new IllegalArgumentException("Wrong input for date until");

        String query = "SELECT k FROM Lease k WHERE k.dateOfLease BETWEEN :from AND :until";
        
        List<Lease> leases = em.createQuery(query, Lease.class).
                setParameter("from", from).setParameter("until", until)
                .getResultList();

        return leases;
    }
    

    @Override
    public Lease getLeaseByID(Integer ID) {
        //actual query
        String sql = "SELECT l FROM Lease l WHERE l.id=:leaseID";
        Lease lease = em.createQuery(sql, Lease.class)
                .setParameter("leaseID", ID).getResultList().get(0);

        return lease;
    }

}
