package dev.binaydungdung.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

	private String title;
	private String description;
	private String image;
	private double price;
	private String category;
}
