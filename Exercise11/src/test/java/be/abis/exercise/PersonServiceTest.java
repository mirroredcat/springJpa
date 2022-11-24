package be.abis.exercise;

import be.abis.exercise.exception.*;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.HobbyJpaRepository;
import be.abis.exercise.service.PersonService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonServiceTest {
	
	@Autowired
    PersonService personService;

	@Autowired
	HobbyJpaRepository hobbyRepo;

	@Test
	public void person1ShouldBeCalledJohn() throws PersonNotFoundException {
		String firstName = personService.findPerson(1).getFirstName().toLowerCase();
		assertEquals("jan",firstName);
	}

	@Test
	public void person78DoesNotExist () {
		assertThrows(PersonNotFoundException.class, () -> personService.findPerson(78));
	}

	@Test
	public void personWithEmailXandPassYDoesNotExist () {
		assertThrows(PersonNotFoundException.class, () -> personService.findPerson("x", "y"));
	}

	@Test
	public void thereShouldBe45PersonsInTheDB(){
		long nrOfPersons = personService.count();
		assertEquals(45,nrOfPersons);
	}

	@Test
	@Transactional
	public void addNewPersonForExistingCompany() throws PersonAlreadyExistsException, PersonNotFoundException {
		Address a = new Address("Diestsevest","32","3000","Leuven","B");
		Company c = new Company("Abis","016/455610","BE12345678",a);
		Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123","nl",c);
		Person t = personService.addPerson(p);
		assertEquals("Sandy", personService.findPerson(t.getPersonId()).getFirstName());
	}

	@Test
	@Transactional
	public void addNewPersonWithNewCompany() throws PersonAlreadyExistsException, PersonNotFoundException {
		Address a = new Address("TestStreet","21","3021","Aalst","B");
		Company c = new Company("TestComp","016/1234454","BE123425678",a);
		Person p = new Person("Test","TestPerson", LocalDate.of(1989,04,10),"test.person@gmail.be","lalala","nl",c);
		Person t = personService.addPerson(p);
		assertEquals("Test", personService.findPerson(t.getPersonId()).getFirstName());
	}

	@Test
	@Transactional
	public void addNewPersonWithoutACompany() throws PersonAlreadyExistsException, PersonNotFoundException {
		Person p = new Person("Test","TestPerson", LocalDate.of(1989,04,10),"test.person@gmail.be","lalala","nl",null);
		Person t = personService.addPerson(p);
		assertEquals("Test", personService.findPerson(t.getPersonId()).getFirstName());
	}

	@Test
	public void addingAnExistingPersonRaisesError() {
		Person p = new Person("Test", "Test", LocalDate.of(1889, 2,21), "jan.smiths@abis.be","js123","nl", null );
		assertThrows(PersonAlreadyExistsException.class, () -> personService.addPerson(p));
	}



	@Test
	@Transactional
	public void changePassWordOfAddedPerson() throws PersonNotFoundException {
		Person p = personService.findPerson("jan.smiths@abis.be","js123");
		personService.changePassword(p,"blabla");
		assertEquals("blabla", personService.findPerson(p.getPersonId()).getPassword());
	}

	@Test
	@Transactional
	public void deleteAddedPerson() throws PersonCanNotBeDeletedException, PersonNotFoundException, PersonAlreadyExistsException {
		Person p = new Person("Test","TestPerson", LocalDate.of(1989,04,10),"test.person@gmail.be","lalala","nl",null);
		Person t = personService.addPerson(p);
		long a = personService.count();
		personService.deletePerson(t.getPersonId());
		long b = personService.count();
		assertEquals(a, b+1);
	}

	@Test
	public void person25CannotBeDeleted() {
		assertThrows(PersonCanNotBeDeletedException.class, () -> personService.deletePerson(25));
	}

	
	@Test
	public void person3Has3Hobbies() throws HobbyNotFoundException {
		assertEquals(3, personService.findHobbies(3).size());
	}

	@Test
	@Transactional
	public void addNewHobbyForPersonWithHobbies() throws HobbyAlreadyExistsException {
		personService.addHobby(1,"SKIING");
		assertEquals(3, hobbyRepo.nrOfHobbies(1));
	}

	@Test
	@Transactional
	public void addHobbyToPersWithNoHobbies() throws HobbyAlreadyExistsException {
		personService.addHobby(2,"SKIING");
		assertEquals(1, hobbyRepo.nrOfHobbies(2));
	}
}
