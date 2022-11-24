package be.abis.exercise.mapper;

import be.abis.exercise.dto.PersonCreationDTO;
import be.abis.exercise.dto.PersonDTO;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

public class PersonMapper {

    public static PersonDTO toDto(Person p){
        PersonDTO npd = new PersonDTO();
        npd.setPersonId(p.getPersonId());
        npd.setFirstName(p.getFirstName().trim());
        npd.setLastName(p.getLastName().trim());
        npd.setBirthDate(p.getBirthDate());
        npd.setEmailAddress(p.getEmailAddress().trim());
        if (p.getCompany()!=null) {
            npd.setCompanyName(p.getCompany().getName().trim());
            npd.setCompanyTown(p.getCompany().getAddress().getTown().trim());
        }
        return npd;
    }

    public static Person toPerson(PersonCreationDTO persDTO){


        Person p = new Person();
        p.setFirstName(persDTO.getFirstName().trim());
        p.setLastName(persDTO.getLastName().trim());
        p.setBirthDate(persDTO.getBirthDate());
        p.setEmailAddress(persDTO.getEmailAddress().trim());
        p.setPassword(persDTO.getPassword().trim());
        p.setLanguage(persDTO.getLanguage().trim());
        if (persDTO.getCompanyName()!=null){
            Address a = new Address(persDTO.getCompanyAddressStreet().trim(), persDTO.getCompanyAddressStreetNr().trim(), persDTO.getCompanyAddressZipcode().trim(), persDTO.getCompanyAddressTown().trim(), persDTO.getCompanyAddressCountryCode().trim());
            Company c = new Company(persDTO.getCompanyName().trim(), persDTO.getCompanyTelephoneNumber().trim(), persDTO.getCompanyVatNr().trim(), a);

            p.setCompany(c);}

        return p;
    }
}
