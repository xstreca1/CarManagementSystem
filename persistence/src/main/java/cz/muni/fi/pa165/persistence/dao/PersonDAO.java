/*
 * Data access object interface for entity Person.
 */
package cz.muni.fi.pa165.persistence.dao;

import cz.muni.fi.pa165.persistence.Entities.Person;
import java.util.List;

/**
 *
 * @author Jakub Rumanovsky
 */
public interface PersonDAO {

    /**
     * Insert person stored in Person entity instance to DB
     *
     * @param person instance of a class Person
     */
    void insertPerson(Person person);

    /**
     * update person, search for person by his/her ID in DB and then update it
     * with the attributes given in Person instance
     *
     * @param person person, who needs to be updated
     * @param personID
     */
    void updatePerson(Person person, Integer personID);

    /**
     * delete Person using his/her ID as a parameter you cannot delete person,
     * who has at least one lease
     *
     * @param personID string ID of a person (f.e. ID "number")
     */
    void deletePerson(Integer PersonID);

    /**
     * returns person by his/her ID, there is always only one person returned
     *
     * @param personID string ID of a person (f.e. ID "number")
     * @return
     */
    Person getPersonByID(Integer personID);

    /**
     * returns Person or list of people with a given name there can be more
     * people with the same name
     *
     * @param name name of a person searched for
     * @return list of people, with desired name
     */
    List<Person> getPeopleByName(String name);

    /**
     * get all people from Database
     *
     * @return list of all people
     */
    List<Person> getAllPeople();
}