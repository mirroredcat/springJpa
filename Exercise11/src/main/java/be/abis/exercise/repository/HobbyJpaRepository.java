package be.abis.exercise.repository;

import be.abis.exercise.key.HobbyKey;
import be.abis.exercise.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyJpaRepository extends JpaRepository<Hobby, HobbyKey> {

    @Query(value="select * from hobbies join persons on h_pno=pno where h_pno = :personId", nativeQuery = true)
    List<Hobby> findHobbies(@Param("personId")int personId);

    @Modifying
    @Query(value="insert into hobbies(h_pno, h_hno, h_hobby) " +
            "values(:personId, :hobbyNr, :hobbyName)", nativeQuery = true)
    void addHobby(@Param("personId") int personId, @Param("hobbyNr") int hobbyNr, @Param("hobbyName") String hobbyName);

    @Query(value="select * from hobbies where h_pno = :personId and h_hobby like :hobbyName%", nativeQuery = true)
    Hobby findHobby(@Param("personId") int personId, @Param("hobbyName") String hobbyName);

    @Query(value="select count(*) from hobbies where h_pno = :personId", nativeQuery = true)
    int nrOfHobbies(@Param("personId") int personId);
}
