package be.abis.exercise.service;

import be.abis.exercise.exception.*;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Hobby;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CompanyJpaRepository;
import be.abis.exercise.repository.HobbyJpaRepository;
import be.abis.exercise.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbisPersonService implements PersonService {

    @Autowired
    PersonJpaRepository personRepository;

    @Autowired
    CompanyJpaRepository companyRepository;

    @Autowired
    HobbyJpaRepository hobbyRepo;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findPerson(int id) throws PersonNotFoundException {
        Person p = personRepository.findPersonByPersonId(id);
        if (p==null){
            throw new PersonNotFoundException("Person not found");
        } else{
            return p;
        }
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) throws PersonNotFoundException {
        Person p = personRepository.findPersonByEmailAddressAndPassword(emailAddress, passWord);
        if (p==null){
            throw new PersonNotFoundException("Person not found");
        } else{
            return p;
        }
    }

    @Override
    @Transactional
    public Person addPerson(Person p) throws PersonAlreadyExistsException{

        Person  p1 = null;
        try {
            p1 = this.findPerson(p.getEmailAddress(), p.getPassword());
        } catch (PersonNotFoundException ignore) {
        }

        if (p1 == null){
            if ( p.getCompany() != null){
                Company c = companyRepository.findByNameAndTown(p.getCompany().getName(), p.getCompany().getAddress().getTown());
                if (c != null) {
                    p.setCompany(c);
                }
            }
            return personRepository.save(p);
        } else {
            throw new PersonAlreadyExistsException("Person already exists. Log in?");
        }
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException, PersonNotFoundException {
        Person p1 = findPerson(id);
        try {
            personRepository.delete(p1);
        } catch (DataAccessException a){
            throw new PersonCanNotBeDeletedException(a.getCause().toString());
        }
    }

    @Override
    public Person changePassword(Person p, String newPswd) throws PersonNotFoundException {
        Person p1 = personRepository.findPersonByPersonId(p.getPersonId());
        if (p1 != null){
            p1.setPassword(newPswd);
            personRepository.save(p1);
        } else {
            System.out.println("Person does not exist");
        }
        return null;
    }

    @Override
    public List<Person> findPersonsByCompanyName(String compName) {
        return personRepository.findPersonsByCompanyName(compName);
    }

    @Override
    public long count() { return personRepository.count();}

    @Override
    public List<String> findHobbies(int personId) throws HobbyNotFoundException {

        List<String> foundH = hobbyRepo.findHobbies(personId).stream()
                .map(Hobby::getHobbyName).collect(Collectors.toList());
        if (!foundH.isEmpty()){
            return foundH;
        }else {
            throw new HobbyNotFoundException("No hobbies found");
        }
    }

    @Override
    @Transactional
    public void addHobby(int personId, String hobbyName) throws HobbyAlreadyExistsException {
        Hobby foundH = hobbyRepo.findHobby(personId, hobbyName);
        if (foundH == null) {
            int i = hobbyRepo.nrOfHobbies(personId);
            i = i == 0 ?  1 : i+1;
            hobbyRepo.addHobby(personId, i, hobbyName );
        } else {
            throw new HobbyAlreadyExistsException("hobby already exists.");
        }
    }
}
