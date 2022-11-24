package be.abis.exercise;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CompanyJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    CompanyJpaRepository cjr;

    @Test
    public void company3isABIS() {
        Company c = cjr.findById(3);
        assertEquals("ABIS N.V.",c.getName().trim().toUpperCase());
    }

    @Test
    public void companyAbisHasId3(){
        Company c = cjr.findByNameStartsWith("ABIS");
        assertEquals(3, c.getId());
    }

    @Test
    public void companyAbisFromLeuvenHasId3() {
        Company c = cjr.findByNameAndTown("ABIS", "LEUVEN");
        assertEquals(3, c.getId());
    }

    @Test
    @Transactional
    public void addNewObject(){
        Address a = new Address ("Potato", "21", "123456", "Leuven", "BE" );
        Company  c = new Company("Test", "1234567", "345-345-345", a);
        cjr.save(c);
        assertEquals(25, cjr.count());
    }

    @Test
    @Transactional
    public void updateCompanyTitle() {
        Company c = cjr.findById(3);
        c.setName("Test");
        assertEquals("Test", cjr.findById(3).getName());
    }

    @Test
    @Transactional
    public void deleteTest(){
        Address a = new Address ("Potato", "21", "123456", "Leuven", "BE" );
        Company  c = new Company("Test", "1234567", "345-345-345", a);
        cjr.save(c);
        cjr.deleteById(c.getId());
        assertEquals(24, cjr.count());

    }

    @Test
    public void abisHas3Employees(){
        int i = cjr.findById(3).getEmployees().size();
        assertEquals(3, i);
    }
}
