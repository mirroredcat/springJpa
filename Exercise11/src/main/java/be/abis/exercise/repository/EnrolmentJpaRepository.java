package be.abis.exercise.repository;

import be.abis.exercise.key.EnrolmentKey;
import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolmentJpaRepository extends JpaRepository<Enrolment, EnrolmentKey> {

    List<Object[]> findEnrolmentsNQ(@Param("personId")int personId);

    @Modifying
    @Query(value="insert into enrolments(e_sno, eno, e_pno, epay, e_cono)" +
            "values(:session, :enrolmentInSession, :enrolee, :pricePerDayPayed, :enrolmentCompany)", nativeQuery = true)
    void insertEnrolment(@Param("session") int session, @Param("enrolmentInSession") int enrolmentInSession, @Param("enrolee") int enrolee,
                         @Param("pricePerDayPayed") double pricePerDayPayed, @Param("enrolmentCompany") Integer enrolmentCompany);


    @Modifying
    @Query(value="insert into enrolments(e_sno, eno, e_pno, epay)" +
            "values(:session, :enrolmentInSession, :enrolee, :pricePerDayPayed)", nativeQuery = true)
    void insertEnrolmentNoComp(@Param("session") int session, @Param("enrolmentInSession") int enrolmentInSession, @Param("enrolee") int enrolee,
                         @Param("pricePerDayPayed") double pricePerDayPayed);




    @Query(value="select max(eno) from enrolments where e_sno = :sessionId", nativeQuery = true)
    int totalEnrolmentsPerSession(@Param("sessionId")int sessionId);

}

