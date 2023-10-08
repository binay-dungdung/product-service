package dev.binaydungdung.productservice.inheritance.demo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_student")
@DiscriminatorValue("4")
public class Student extends User {
    private double psp;
    private double attendance;
}
