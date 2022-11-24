package be.abis.exercise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class SessionDTO {

    private int sessionId;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate startDate;
    private String instructorFirstName;
    private String instructorLastName;
    private String locationCompanyName;
    private String locationTown;
    private String kind;
    private boolean cancelled;
    private String longCourseTitle;


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getInstructorFirstName() {
        return instructorFirstName;
    }

    public void setInstructorFirstName(String instructorFirstName) {
        this.instructorFirstName = instructorFirstName;
    }

    public String getInstructorLastName() {
        return instructorLastName;
    }

    public void setInstructorLastName(String instructorLastName) {
        this.instructorLastName = instructorLastName;
    }

    public String getLocationCompanyName() {
        return locationCompanyName;
    }

    public void setLocationCompanyName(String locationCompanyName) {
        this.locationCompanyName = locationCompanyName;
    }

    public String getLocationTown() {
        return locationTown;
    }

    public void setLocationTown(String locationTown) {
        this.locationTown = locationTown;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getLongCourseTitle() {
        return longCourseTitle;
    }

    public void setLongCourseTitle(String longCourseTitle) {
        this.longCourseTitle = longCourseTitle;
    }
}
