/*
 * Data transfer object for Person entity
 */
package cz.muni.fi.pa165.service.dto;


import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.NotEmpty;



/**
 *
 * @author jrumanov
 */
public class PersonDTO {

    private Integer id;
    
    @NotEmpty // can not be empty
    @Pattern(regexp = "[A-Za-z ]*") // can contain only letters and spaces   
    private String name;

    private Boolean isActive;
   
    private Person.EmploymentStatus employmentStatus;

    @NotEmpty // can not be empty
    @Pattern(regexp = "[A-Za-z ]*") // can contain only letters and spaces   
    private String position;
   
    private Person.Sex sex;

    @NotEmpty // can not be empty
    @Pattern(regexp = "[A-Z]*")
    private String nationality;

    //@Pattern(regexp = "[0-9]*") // can contain only numbers 0-9
    @NotNull
    @Digits(integer = 6, fraction = 0) // maximal 6 digits
    private Integer salary;
    
    private String IdentificationNumber;

    public PersonDTO(Integer id, String name, Boolean isActive,
            Person.EmploymentStatus employmentStatus,
            String position, Person.Sex sex, 
            String nationality, Integer salary, String identificationNumber) {

        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.employmentStatus = employmentStatus;        
        this.position = position;
        this.sex = sex;       
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return "" + name + "," + employmentStatus + ",ID=" + id;
    }

}