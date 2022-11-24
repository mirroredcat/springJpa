package be.abis.exercise.dto;

import java.time.LocalDate;

public class EnrolmentDTO {

    private String persFirstName;
    private String persLastName;
    private String persCompanyName;
    private String startDate;
    private String locationCompanyName;
    private String locationCompanyTown;
    private String courseLongTitle;


    public String getPersFirstName() {
        return persFirstName;
    }

    public void setPersFirstName(String persFirstName) {
        this.persFirstName = persFirstName;
    }

    public String getPersLastName() {
        return persLastName;
    }

    public void setPersLastName(String persLastName) {
        this.persLastName = persLastName;
    }

    public String getPersCompanyName() {
        return persCompanyName;
    }

    public void setPersCompanyName(String persCompanyName) {
        this.persCompanyName = persCompanyName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLocationCompanyName() {
        return locationCompanyName;
    }

    public void setLocationCompanyName(String locationCompanyName) {
        this.locationCompanyName = locationCompanyName;
    }

    public String getLocationCompanyTown() {
        return locationCompanyTown;
    }

    public void setLocationCompanyTown(String locationCompanyTown) {
        this.locationCompanyTown = locationCompanyTown;
    }

    public String getCourseLongTitle() {
        return courseLongTitle;
    }

    public void setCourseLongTitle(String courseLongTitle) {
        this.courseLongTitle = courseLongTitle;
    }
}
