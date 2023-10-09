package dev.binaydungdung.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Entity(name = "product")
public class Product extends BaseModel {

	private String title;
	private String description;
	private String image;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private Price price;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;
}
