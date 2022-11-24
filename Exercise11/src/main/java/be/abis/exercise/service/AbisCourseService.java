package be.abis.exercise.service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisCourseService implements CourseService {

    @Autowired
    CourseJpaRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourse(int id) {
        return courseRepository.findByCourseId(id);
    }

    @Override
    public Course findCourse(String shortTitle) {
        return courseRepository.findByShortTitle(shortTitle);
    }

    @Override
    public Course addCourse(Course c) {
        Course co = findCourse(c.getCourseId());
        if (co == null) {
           return  courseRepository.save(c);
        }
        else {
            System.out.println("Course already exists");
        }
        return null;
    }

    @Override
    public Course updateCourse(Course c) {
        Course co = findCourse(c.getCourseId());
        if (co != null) {
            courseRepository.save(c);
        }
        else {
            System.out.println("Course doesn't exists");
        }
        return c;
    }


    @Override
    public void deleteCourse(int id) {
        Course co = findCourse(id);
        if (co!=null){
            courseRepository.delete(co);
        } else {
            System.out.println("Course does not exist");
        }
    }

    @Override
    public long count() {
        return courseRepository.count();
    }


}
