package be.abis.exercise.service;


import be.abis.exercise.exception.*;
import be.abis.exercise.model.Hobby;
import be.abis.exercise.model.Person;

import java.util.ArrayList;
import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();
    Person findPerson(int id) throws PersonNotFoundException;
    Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException;
    Person addPerson(Person p) throws PersonAlreadyExistsException;
    void deletePerson(int id) throws PersonCanNotBeDeletedException, PersonNotFoundException;
    Person changePassword(Person p, String newPswd) throws PersonNotFoundException;
    List<Person> findPersonsByCompanyName(String compName);
    long count();
    public List<String> findHobbies(int personId) throws HobbyNotFoundException;
    public void addHobby(int personId, String hobbyName) throws HobbyAlreadyExistsException;
}
