package be.abis.exercise.key;

import be.abis.exercise.model.Person;

import java.io.Serializable;
import java.util.Objects;

public class HobbyKey implements Serializable {

    private Person person;
    private int hobbyNo;

    public HobbyKey(Person person, int hobbyNo) {
        this.person = person;
        this.hobbyNo = hobbyNo;
    }

    public HobbyKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyKey hobbyKey = (HobbyKey) o;
        return hobbyNo == hobbyKey.hobbyNo && Objects.equals(person, hobbyKey.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, hobbyNo);
    }
}
