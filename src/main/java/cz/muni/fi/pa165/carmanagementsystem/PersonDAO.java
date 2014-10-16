/*
 * Data access object interface for entity Person.
 */
package cz.muni.fi.pa165.carmanagementsystem;

import java.util.List;

/**
 *
 * @author Jakub Rumanovsky
 */
public interface PersonDAO {

    void createPerson(Person person);

    void updatePerson();

    void deletePerson();

    Person getPersonByID(String personID);

    List<Person> getPeopleByName(String name);

    List<Person> getAllPeople();
}
