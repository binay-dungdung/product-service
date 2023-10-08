package dev.binaydungdung.productservice.inheritance.demo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_ta")
public class TA extends User {
    private double avgRating;
}
