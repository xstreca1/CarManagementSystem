/*
 * Data transfer object for Person entity
 */
package cz.muni.fi.pa165.api;

import cz.muni.fi.pa165.persistence.Entities.Address;
import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;

/**
 *
 * @author jrumanov
 */
public class PersonDTO {

    private Integer id;

    private String name;

    private Boolean isActive;

    private Person.EmploymentStatus employmentStatus;

    private Date dateOfBirth;

    private String position;

    private Person.Sex sex;

    private Address address;

    private String nationality;

    private int salary;
    
    private String IdentificationNumber;

    public PersonDTO(Integer id, String name, Boolean isActive,
            Person.EmploymentStatus employmentStatus, Date dateOfBirth,
            String position, Person.Sex sex, Address address,
            String nationality, int salary, String identificationNumber) {

        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.employmentStatus = employmentStatus;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
        this.sex = sex;
        this.address = address;
        this.nationality = nationality;
        this.salary = salary;
        this.IdentificationNumber = identificationNumber;

    }

    public PersonDTO() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getIdentificationNumber() {
        return IdentificationNumber;
    }

    public void setIdentificationNumber(String IdentificationNumber) {
        this.IdentificationNumber = IdentificationNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Person.EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Person.EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Person.Sex getSex() {
        return sex;
    }

    public void setSex(Person.Sex sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}