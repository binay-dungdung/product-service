package dev.binaydungdung.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "category")
@NoArgsConstructor
public class Category extends BaseModel {

	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Product> products;

	public Category(String name) {
		this.name = name;
	}
}
