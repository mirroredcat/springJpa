package be.abis.exercise.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="persons")
public class Person {

	@Id
	@SequenceGenerator(name = "person_seq", sequenceName = "persons_pno_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
	@Column(name="pno")
	private int personId;
	@Column(name = "pfname")
	private String firstName;
	@Column(name = "plname")
	private String lastName;
	@Column(name = "pbirthdate")
	private LocalDate birthDate;
	@Column(name = "pemail")
	private String emailAddress;
	@Column(name = "ppass")
	@Size(min=5, max=12, message="password must be between 5 an 12 chars")
	private String password;
	@Column(name = "plang")
	private String language;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="pa_cono")
	private Company company;

	@ElementCollection
	@CollectionTable(name="hobbies", joinColumns=@JoinColumn(name="h_pno"))
	@OrderColumn(name="h_hno")
	private List<String> hobbies;

	public Person(){}
	public Person( String firstName, String lastName, LocalDate birthDate, String emailAddress, String password, String language, Company company) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.emailAddress = emailAddress;
		this.password = password;
		this.language = language;
		this.company = company;
	}

	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Person with id " + personId + ", " + firstName + " "+ lastName + ", works for " +company.getName() + " in " + company.getAddress().getTown();
	}

	

}
