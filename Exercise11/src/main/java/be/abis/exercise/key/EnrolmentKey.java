package be.abis.exercise.key;

import be.abis.exercise.model.Enrolment;
import be.abis.exercise.model.Session;

import java.io.Serializable;
import java.util.Objects;

public class EnrolmentKey implements Serializable {

    private Session session;
    private int enrolmentInSession;

    public EnrolmentKey(){}

    public EnrolmentKey(Session session, int enrolmentInSession) {
        this.session = session;
        this.enrolmentInSession = enrolmentInSession;
    }

    @Override
    public boolean equals(Object o) {
        Enrolment e = (Enrolment)o;
        return this.session == e.getSession() && this.enrolmentInSession == e.getEnrolmentInSession();
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, enrolmentInSession);
    }
}
