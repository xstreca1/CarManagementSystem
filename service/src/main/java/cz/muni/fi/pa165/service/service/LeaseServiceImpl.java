package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.persistence.DAO.LeaseDAO;
import cz.muni.fi.pa165.persistence.Entities.Lease;
import cz.muni.fi.pa165.persistence.Entities.Lease.ReturnedStatus;
import cz.muni.fi.pa165.persistence.Entities.Person;
import cz.muni.fi.pa165.service.dto.LeaseDTO;
import cz.muni.fi.pa165.service.dto.PersonDTO;
import cz.muni.fi.pa165.service.service.LeaseServiceInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Petr Potucek
 */
@Service("leaseService")
@Repository //for transformation of exceptions to DataAccessException
@Transactional //to handle transactions
public class LeaseServiceImpl implements LeaseServiceInterface {

    @Autowired
    private LeaseDAO leaseDAO;

    private List<String> list = new ArrayList<String>();

    @PersistenceContext
    private EntityManager em;

    /**
     * setter for lease
     *
     * @param leaseDao lease to be setted
     */
    public void setLeaseDao(LeaseDAO leaseDAO) {
        if (leaseDAO == null) {
            throw new NullPointerException("null object");
        }

        this.leaseDAO = leaseDAO;
    }

    @Override
    public void createLease(LeaseDTO leaseDTO) {

        Lease leaseEntity = new Lease();

        List<String> list2 = new ArrayList<String>();

        list2.add("dozerMapping.xml");

        Mapper mapper = new DozerBeanMapper(list2);
        mapper.map(leaseDTO, leaseEntity, "lease");

        leaseDAO.createLease(leaseEntity);

    }

    public void setReturnedStatus(int id, ReturnedStatus status) {
        if (id < 0) {
            throw new IllegalArgumentException("wrong type of id");
        }
        if (status == null) {
            throw new NullPointerException("null object");
        }

        (leaseDAO.getLeaseByID(id)).setReturnedStatus(status);
    }

    public List<LeaseDTO> getLeaseByPerson(PersonDTO personDTO) {
        if (personDTO == null) {
            throw new NullPointerException("personDTO is null");
        }
        Person personEntity = new Person();

        LeaseDTO leaseDTO = new LeaseDTO();

        List<String> list = new ArrayList<String>();

        List<LeaseDTO> leaseListDTO = new ArrayList<LeaseDTO>();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(personDTO, personEntity, "person");

        List<Lease> leaseList = leaseDAO.getLeasesByPerson(personEntity);;
        for (Lease l : leaseList) {
            mapper.map(l, leaseDTO, "lease");
            leaseListDTO.add(leaseDTO);
        }

        return leaseListDTO;
    }

    public List<LeaseDTO> getAllLeases(Date from, Date to) {
        if (from == null) {
            throw new NullPointerException("Date from is null");
        }
        if (to == null) {
            throw new NullPointerException("Date to is null");
        }

        List<Lease> allLeases = leaseDAO.getAllLeases(from, to);
        Mapper mapper = new DozerBeanMapper(list);
        List<LeaseDTO> leasesDTO = new ArrayList(allLeases.size());
        for (Lease lease : allLeases) {
            leasesDTO.add(mapper.map(lease, LeaseDTO.class));
        }
        return leasesDTO;
    }

    @Override
    @Transactional
    public List<LeaseDTO> findAllLeases() {
        //create empty list
        List<String> list = new ArrayList<String>();

        List<LeaseDTO> leasesDTO = new ArrayList<LeaseDTO>();

        LeaseDTO leaseDTO = new LeaseDTO();

        // map DTO object on Entity
        list.add("dozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        List<Lease> leases = leaseDAO.findAllLeases();

        for (Lease l : leases) {
            mapper.map(l, leaseDTO, "lease");
            leasesDTO.add(leaseDTO);
        }

        return leasesDTO;
    }

    public void deleteLease(LeaseDTO leaseDto) {

        Lease leaseEntity = new Lease();

        List<String> list = new ArrayList<String>();
// Add the mapping configuration
        list.add("dozerMapping.xml");
// Add to DozerMapper
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(leaseDto, leaseEntity, "lease");
        leaseDAO.deleteLease(leaseEntity.getId());

    }

    public List<LeaseDTO> getTravelStatistics(PersonDTO person, Date from, Date to) {
        if (person == null) {
            throw new NullPointerException("person is null");
        }
        if (from == null) {
            throw new NullPointerException("Date from is null");
        }
        if (to == null) {
            throw new NullPointerException("Date to is null");
        }

        List<Lease> allLeases = leaseDAO.getAllLeases(from, to);
        List<LeaseDTO> leasesByPersonDTO = new ArrayList();
        Mapper mapper = new DozerBeanMapper(list);
        for (Lease lease : allLeases) {
            if ((lease.getPerson()).equals(person)) {
                leasesByPersonDTO.add(mapper.map(lease, LeaseDTO.class));
            }
        }

        return leasesByPersonDTO;
    }

    @Override
    public LeaseDTO getLeaseByID(Integer id) {

        Lease leaseEntity = new Lease();
        LeaseDTO leaseDto = new LeaseDTO();

        List<String> list = new ArrayList<String>();

        list.add("dozerMapping.xml");

        leaseEntity = leaseDAO.getLeaseByID(id);

        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(leaseEntity, leaseDto, "lease");

        return leaseDto;
    }

    

}
