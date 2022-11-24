package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface PersonJpaRepository extends JpaRepository<Person, Integer> {

	    //List<Person> getAllPersons();
	    Person findPersonByPersonId(int id);
	    Person findPersonByEmailAddressAndPassword(String emailAddress, String passWord);
	    //Person addPerson(Person p) throws PersonAlreadyExistsException;
	    //void deletePerson(int id) throws PersonCanNotBeDeletedException;
	    //Person changePassword(Person p, String newPswd) ;
		@Query("select p from Person p where p.company.name like :compName% ")
	    List<Person> findPersonsByCompanyName(@Param("compName") String compName);
}
