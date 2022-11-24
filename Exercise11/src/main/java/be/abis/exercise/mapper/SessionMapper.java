package be.abis.exercise.mapper;

import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.model.Session;

public class SessionMapper {

    public static SessionDTO toDto(Session s){
        SessionDTO ses = new SessionDTO();

        ses.setSessionId(s.getSessionId());
        ses.setStartDate(s.getStartDate());
        ses.setInstructorFirstName(s.getInstructor().getFirstName());
        ses.setInstructorLastName(s.getInstructor().getLastName());
        ses.setLocationCompanyName(s.getLocation().getName());
        ses.setLocationTown(s.getLocation().getAddress().getTown());
        ses.setKind(s.getKind());
        ses.setCancelled(s.isCancelled());
        ses.setLongCourseTitle(s.getCourse().getLongTitle());

        return ses;
    }

}
