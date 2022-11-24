package be.abis.exercise.repository;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyJpaRepository extends JpaRepository<Company, Integer> {

    @Query("select c from Company c join fetch c.employees where c.id = :id")
    public Company findById(int id);
    public Company findByNameStartsWith(String name);

    @Query("select c from Company c where c.name like :name% and c.address.town = :town")
    Company findByNameAndTown(@Param("name") String name, @Param("town") String town);



}
