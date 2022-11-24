package be.abis.exercise.controller;

import be.abis.exercise.dto.PersonCreationDTO;
import be.abis.exercise.dto.PersonDTO;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.mapper.PersonMapper;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Validated
@RestController
@RequestMapping("persons")
public class PersonApiController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonService ps;

    @GetMapping("")
    public List<PersonDTO> getAllPersons(){
        return ps.getAllPersons()
                .stream()
                .map(p -> PersonMapper.toDto(p))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public PersonDTO findPerson(@PathVariable("id") int id) throws PersonNotFoundException {
       return PersonMapper.toDto(ps.findPerson(id));
    }

    @PostMapping("/login")
    public PersonDTO findPersonByMailAndPwd(@RequestBody Login login) throws PersonNotFoundException {
        return PersonMapper.toDto(ps.findPerson(login.getEmailAddress(), login.getPassword()));
    }

    @GetMapping(path="/compquery")
    public List<PersonDTO> findPersonsByCompanyName(@RequestParam("compname") String compName) {
        return ps.findPersonsByCompanyName(compName)
                .stream()
                .map(p -> PersonMapper.toDto(p))
                .collect(Collectors.toList());
    }

    @PostMapping(path="")
    public PersonDTO addPerson (@Valid @RequestBody PersonCreationDTO p) throws PersonAlreadyExistsException {
        Person pers = PersonMapper.toPerson(p);
        ps.addPerson(pers);
        return PersonMapper.toDto(pers);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable("id") int id) throws PersonCanNotBeDeletedException, PersonNotFoundException {
        ps.deletePerson(id);
    }

    @PutMapping("{id}")
    public PersonDTO changePassword(@PathVariable("id") int id, @RequestBody PersonCreationDTO person) throws PersonNotFoundException {
        Person pers = PersonMapper.toPerson(person);
        System.out.println("changing password to newpswd= " + pers.getPassword());
        ps.changePassword(pers, pers.getPassword());
        return PersonMapper.toDto(pers);
    }





}
