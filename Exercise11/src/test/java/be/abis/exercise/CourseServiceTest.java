package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseServiceTest {
	
	@Autowired
    CourseService courseService;

	@Test
	public void course7900isWorkshopSQL() {
		Course c = courseService.findCourse(7900);
		assertEquals("SQLWS",c.getShortTitle().trim().toUpperCase());
	}
	
	@Test
	public void thereAre24Courses(){
		assertEquals(24, courseService.count());
	}

	@Test
	public void courseISPFHasId7890(){
		Course co = courseService.findCourse("ISPF");
		assertEquals(7890, co.getCourseId());
	}

	@Test
	@Transactional
	public void newCourseIsAddedTest(){
		Course c = new Course("Test", "TestCourse", 3, 500.50);
		courseService.addCourse(c);
		assertEquals("TestCourse", courseService.findCourse("Test").getLongTitle());
	}

	@Test
	@Transactional
	public void courseTestUpdateTitleToWeird(){
		Course c = courseService.findCourse(8056);
		c.setShortTitle("Weird");
		courseService.updateCourse(c);
		assertEquals("Training Autocad", courseService.findCourse("Weird").getLongTitle());

	}

	@Test
	@Transactional
	public void courseWeirdIsDeleted(){
		courseService.deleteCourse(8056);
		assertEquals(23, courseService.count());
	}
	

}
