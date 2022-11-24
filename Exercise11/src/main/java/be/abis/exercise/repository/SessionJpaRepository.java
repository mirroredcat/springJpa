package be.abis.exercise.repository;

import be.abis.exercise.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface SessionJpaRepository extends JpaRepository<Session, Integer> {

    //@Query("select s from Session s join s.course  where lower (s.course.shortTitle) like lower(concat( :courseName,'%')) and s.cancelled is null ")
    @Query(value="select * " +
                "from sessions s " +
                "join courses c on s.s_cid = c.cid " +
            "where c.cstitle like  :courseName%   and s.scancel is null",nativeQuery = true )
    List<Session> findSessionsByCourse(@Param("courseName")String courseName);


    @Modifying
    @Query(value="update sessions set scancel='C' where sno = :sessionId",
    nativeQuery = true)
    void cancelSession(@Param("sessionId") int sessionId);

    Session findById(int id);
}
