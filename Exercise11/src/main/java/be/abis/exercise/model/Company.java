package be.abis.exercise.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="companies")
public class Company{

	@SequenceGenerator(name = "mySeqGen", sequenceName = "companies_cono_seq")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
	@Column(name="cono")
	private Integer id;
	@Column(name="coname")
	private String name;
	@Column(name="cotel")
	private String telephoneNumber;
	@Column(name="covat")
	private String vatNr;
	@Embedded
	private Address address;
	@OneToMany(targetEntity = Person.class, mappedBy = "company", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Person> employees;

	public Company(){}
	public Company(String name, String telephoneNumber, String vatNr, Address address) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.vatNr = vatNr;
		this.address = address;
	}


	public Integer getId() {return id;}
	public void setId(int id) {	this.id = id;}

	public List<Person> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Person> employees) {
		this.employees = employees;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getVatNr() {
		return vatNr;
	}
	public void setVatNr(String vatNr) {
		this.vatNr = vatNr;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	//public String toString(){
	//	return name + " in " + address.getTown();
	//}
	

	// add and remove to the list

}
