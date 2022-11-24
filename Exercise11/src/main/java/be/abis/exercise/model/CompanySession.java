package be.abis.exercise.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("c")
public class CompanySession extends Session{
}
