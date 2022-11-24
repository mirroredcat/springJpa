package be.abis.exercise.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("p")
public class PublicSession extends Session{


}
