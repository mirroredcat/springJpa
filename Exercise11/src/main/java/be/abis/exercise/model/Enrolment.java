package be.abis.exercise.model;

import be.abis.exercise.key.EnrolmentKey;

import javax.persistence.*;
@NamedNativeQuery(name="Enrolment.findEnrolmentsNQ",
            query="select p.pfname, p.plname, c1.coname as perscomp, se.sdate, c2.coname as loccomp, c2.cotown, co.cltitle   " +
                    "from enrolments e " +
                    "join persons p on e.e_pno = p.pno " +
                    "join companies c1 on p.pa_cono = c1.cono " +
                    "join sessions se on e.e_sno = se.sno " +
                    "join companies c2 on se.sloc_cono=c2.cono " +
                    "join courses co on se.s_cid = co.cid " +
                    "where p.pno = :personId")
@Entity
@IdClass(EnrolmentKey.class)
public class Enrolment {

    @ManyToOne
    @JoinColumn(name="e_sno")
    @Id
    private Session session;
    @Column(name="eno")
    @Id
    private int enrolmentInSession;
    @ManyToOne
    @JoinColumn(name="e_pno")
    private Person enrollee;
    @Column(name="epay")
    private double pricePerDayPayed;
    @ManyToOne
    @JoinColumn(name="e_cono")
    private Company enrolmentCompany;
    @Column(name="ecancel")
    private boolean cancelled;


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getEnrolmentInSession() {
        return enrolmentInSession;
    }

    public void setEnrolmentInSession(int enrolmentInSession) {
        this.enrolmentInSession = enrolmentInSession;
    }

    public Person getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Person enrollee) {
        this.enrollee = enrollee;
    }

    public double getPricePerDayPayed() {
        return pricePerDayPayed;
    }

    public void setPricePerDayPayed(double pricePerDayPayed) {
        this.pricePerDayPayed = pricePerDayPayed;
    }

    public Company getEnrolmentCompany() {
        return enrolmentCompany;
    }

    public void setEnrolmentCompany(Company enrolmentCompany) {
        this.enrolmentCompany = enrolmentCompany;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }


}
