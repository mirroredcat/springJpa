package be.abis.exercise.model;

import be.abis.exercise.key.HobbyKey;

import javax.persistence.*;

@Entity
@Table(name="hobbies")
@IdClass(HobbyKey.class)
public class Hobby {

    @ManyToOne
    @JoinColumn(name="h_pno")
    @Id
    private Person person;
    @Column(name = "h_hno")
    @Id
    private int hobbyNo;
    @Column(name="h_hobby")
    private String hobbyName;


    public Hobby(Person person, int hobbyNo, String hobbyName) {
        this.person = person;
        this.hobbyNo = hobbyNo;
        this.hobbyName = hobbyName;
    }

    public Hobby() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getHobbyNo() {
        return hobbyNo;
    }

    public void setHobbyNo(int hobbyNo) {
        this.hobbyNo = hobbyNo;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }
}
