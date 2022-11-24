package be.abis.exercise.repository;

import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseJpaRepository extends JpaRepository<Course, Integer> {


	public Course findByCourseId(int id);
	public Course findByShortTitle(String shortTitle);

		
}
