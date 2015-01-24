/*
 * Person entity class
 */
package cz.muni.fi.pa165.persistence.Entities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jakub Rumanovsky
 */
@Entity
public class Person implements Serializable {

    //--------------------enums--------------------------
    public enum EmploymentStatus {

        CEO,
        MANAGER,
        SENIOR,
        JUNIOR,
        INTERN,
        JOZO
    }

    public enum Sex {

        FEMALE,
        MALE,
        OTHER
    }

    //-----------------attributes------------------------
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column //(nullable = false)
    private String identificationNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @Column(nullable = false)
    private String position;

    @Enumerated(EnumType.STRING)
    private Sex sex;    

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private Integer salary;
    
    @Column(unique = true)
    private String username;
    
    @Column
    private String password;
    
    @Column
    private Boolean isAdmin;

    //--------------relationships------------------------
    @OneToMany(mappedBy = "person")
    private List<Lease> lease;

    //------------getters and setters--------------------
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    

    public void setPassword(String password) {
     //   this.password = get_SHA_384_SecurePassword(password, "fucek");
        this.password = password;
    }
    

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<Lease> getLease() {
        return lease;
    }

    public void setLease(List<Lease> lease) {
        this.lease = lease;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    //----------------constructors-----------------------
    public Person() {
    }

    //-------------mandatory methods---------------------
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificationNumber != null ? identificationNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.identificationNumber == null && other.identificationNumber != null)
                || (this.identificationNumber != null
                && !this.identificationNumber.equals(other.identificationNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.pa165.carmanagementsystem.Person[ id=" + id + " ]";
    }
    
    private static String get_SHA_384_SecurePassword(String passwordToHash, String salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
    //Add salt
    private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

}
