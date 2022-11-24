package be.abis.exercise;

import be.abis.exercise.exception.EnrolException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.EnrolmentJpaRepository;
import be.abis.exercise.repository.SessionJpaRepository;
import be.abis.exercise.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrainingServiceTest {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private SessionJpaRepository sessRepo;

    @Autowired
    private EnrolmentJpaRepository enrolmentRepo;

    @Mock
    Company c;

    @Mock
    Address a;

    @Test
    public void findSessionsOfCourseTest(){
        int i = trainingService.findSessionsForCourse("DB2BAS").size();
        assertEquals(1, i);
    }

    @Test
    public void person25Has3Enrolments() {
        assertEquals(5, trainingService.findEnrolments(25).size());
    }

    @Test
    @Transactional
    public void session2IsCancelled () {
        trainingService.cancelSession(2);
        assertEquals(true, sessRepo.findById(2).isCancelled());
    }

    @Test
    @Transactional
    public void existingPersonNoCompanyEnrolsForSession() throws EnrolException {
        Person p = new Person("Test", "Test", LocalDate.of(1889, 2,21), "koen.loose@gmail.com","kl123","nl", null );
        trainingService.enrolForSession(p,1);
        assertEquals(6, enrolmentRepo.totalEnrolmentsPerSession(1));
    }

    @Test
    @Transactional
    public void existingPersonWithCompanyEnrolsForSession() throws EnrolException {
        when(c.getId()).thenReturn(3);
        Person p = new Person("Test", "Test", LocalDate.of(1889, 2,21), "jan.smiths@abis.be","js123","nl", c );
        trainingService.enrolForSession(p,1);
        assertEquals(6, enrolmentRepo.totalEnrolmentsPerSession(1));
    }

    @Test
    @Transactional
    public void newPersonWithCompanyEnrolsForSession() throws EnrolException {
        Address a = new Address("TestStreet","21","3021","Aalst","B");
        Company c = new Company("TestComp","016/1234454","BE123425678",a);
        Person p = new Person("Test", "Test", LocalDate.of(1889, 2,21), "test@abis.be","js123","nl",c );
        trainingService.enrolForSession(p,1);
        assertEquals(6, enrolmentRepo.totalEnrolmentsPerSession(1));
    }

    @Test
    @Transactional
    public void newPersonWithoutCompanyEnrolsForSession() throws EnrolException {
        Person p = new Person("Test", "Test", LocalDate.of(1889, 2,21), "test@abis.be","js123","nl",null );
        trainingService.enrolForSession(p,1);
        assertEquals(6, enrolmentRepo.totalEnrolmentsPerSession(1));
    }

    @Test
    public void session1IsPublic(){
        assertEquals("PublicSession", sessRepo.findById(1).getClass().getSimpleName());
    }

}
