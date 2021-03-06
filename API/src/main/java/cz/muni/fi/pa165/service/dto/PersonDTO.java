/*
 * Data transfer object for Person entity
 */
package cz.muni.fi.pa165.service.dto;


import cz.muni.fi.pa165.persistence.Entities.Person;

/**
 *
 * @author jrumanov
 */
public class PersonDTO {

    private Integer id;
  
    private String name;

    private Boolean isActive;
   
    private Person.EmploymentStatus employmentStatus;

    private String position;
   
    private Person.Sex sex;

    private String nationality;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    private Integer salary;
    
    private String IdentificationNumber;
    
    private String username;
    
    private String password;
    
    private Boolean isAdmin;

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