package be.abis.exercise.mapper;

import be.abis.exercise.dto.EnrolmentDTO;
import be.abis.exercise.model.Enrolment;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;

import java.time.LocalDate;

public class EnrolmentMapper {

    public static EnrolmentDTO toDto(Object[] objArray){
        EnrolmentDTO enr = new EnrolmentDTO();

        enr.setPersFirstName(objArray[0].toString());
        enr.setPersLastName(objArray[1].toString());
        enr.setPersCompanyName(objArray[2].toString());
        enr.setStartDate(objArray[3].toString());
        enr.setLocationCompanyName(objArray[4].toString());
        enr.setLocationCompanyTown(objArray[5].toString());
        enr.setCourseLongTitle(objArray[6].toString());

        return enr;
    }

}
