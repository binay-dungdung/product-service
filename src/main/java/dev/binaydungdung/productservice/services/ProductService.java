package dev.binaydungdung.productservice.services;

import java.util.List;

import dev.binaydungdung.productservice.dtos.GenericProductDto;

public interface ProductService {

	List<GenericProductDto> getAllProducts();
	
	GenericProductDto getProductById(Long id);

	GenericProductDto createProduct(GenericProductDto genericProductDto);
	
}
