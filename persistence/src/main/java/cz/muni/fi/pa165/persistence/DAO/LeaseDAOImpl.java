package cz.muni.fi.pa165.persistence.DAO;

import cz.muni.fi.pa165.persistence.Entities.Car;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Implementation of LeaseDAO interface Implements functionality of lease
 *
 * @author Petr Potucek
 * @since 2014-09
 */
@Repository("leaseDAO") //for transformation of exceptions to DataAccessException
public class LeaseDAOImpl implements LeaseDAO {

    @PersistenceContext(name = "carManagementSystem-unit")
    private EntityManager em;

    public LeaseDAOImpl() {
    }

    public LeaseDAOImpl(EntityManager entityMf) {
        if (entityMf == null) {
            throw new IllegalArgumentException("the argument must be set");
        }

        em = entityMf;
    }

    public EntityManager getEntityManagerFactory() {
        return em;
    }

    @Override
    public Lease createLease(Lease lease) {
        if (lease == null) {
            throw new IllegalArgumentException("Lease is null");
        }

        em.persist(lease);

        return lease;
    }

    @Override
    public void updateLease(Lease lease, int leaseId) {
        if (lease == null) {
            throw new IllegalArgumentException("lease is null");
        }
        if (leaseId < 0) {
            throw new IllegalArgumentException("Wrong input for id");
        }

        int distance = lease.getDistance();
       
        Boolean isClosed = lease.getIsClosed();
      
        ReturnedStatus status = lease.getReturnedStatus();

        Lease lease1 = (Lease) em.find(Lease.class, leaseId);

        lease1.setDistance(distance);
       
        lease1.setIsClosed(isClosed);
      
        lease1.setReturnedStatus(status);
    }

    @Override
    public List getLeasesByPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("person is null");
        }

        String query = "SELECT k FROM Lease k WHERE k.person= :person";//TODO
        List<Lease> leases = em.createQuery(query).setParameter("person", person).getResultList();

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

    @Override
    public List<Lease> findAllLeases(){
    String sql = "SELECT l FROM Lease l";
        List<Lease> leases = em.createQuery(sql,Lease.class).getResultList();

        return leases;
    }

}
