package be.abis.exercise.service;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.mapper.EnrolmentMapper;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.Session;
import be.abis.exercise.repository.EnrolmentJpaRepository;
import be.abis.exercise.repository.SessionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbisTrainingService implements TrainingService {

    @Value("Welcome to the Abis Training Service")
    private String welcomeMessage;

    @Autowired
    private SessionJpaRepository sessionRepo;

    @Autowired
    private EnrolmentJpaRepository enrolmentRepo;

    @Autowired
    private PersonService personService;

    @Autowired
    private CourseService courseService;

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public CourseService getCourseService() {
        return courseService;
    }


    @Override
    @Transactional
    public void enrolForSession(Person person, int sessionId) throws EnrolException {
        Person foundP = null;
        try {
            foundP = personService.addPerson(person);
        } catch (PersonAlreadyExistsException  e) {
            try {
                foundP = personService.findPerson(person.getEmailAddress(), person.getPassword());
            } catch (PersonNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }

        try {
            System.out.println("am in second try block");
            foundP = personService.findPerson(person.getEmailAddress(), person.getPassword());
            System.out.println(foundP.getFirstName());
            double pricePerDay = sessionRepo.findById(sessionId).getCourse().getPricePerDay();

            Company c = foundP.getCompany();
            Integer i = c==null?null:c.getId();
            if (c!= null) {
                enrolmentRepo.insertEnrolment(sessionId,
                        enrolmentRepo.totalEnrolmentsPerSession(sessionId) + 1,
                        foundP.getPersonId(),
                        pricePerDay,
                        i
                );
            } else {
                enrolmentRepo.insertEnrolmentNoComp(sessionId,
                        enrolmentRepo.totalEnrolmentsPerSession(sessionId) + 1,
                        foundP.getPersonId(),
                        pricePerDay

                );
            }

        } catch (DataAccessException | PersonNotFoundException exc) {
            throw new EnrolException(exc.getMessage());
        }
    }

    @Override
    public List<Session> findSessionsForCourse(String courseTitle) {

        return sessionRepo.findSessionsByCourse(courseTitle);
    }

    @Transactional
    public void cancelSession(int id){
        sessionRepo.cancelSession(id);
    }



    @Override
    public List<EnrolmentDTO> findEnrolments(int personId) {

        return enrolmentRepo.findEnrolmentsNQ(personId)
                .stream()
                .map(EnrolmentMapper::toDto)
                .collect(Collectors.toList());
    }


}
