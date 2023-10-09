package dev.binaydungdung.productservice.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "price")
public class Price extends BaseModel {

    private String currency;
    private double price;
}
