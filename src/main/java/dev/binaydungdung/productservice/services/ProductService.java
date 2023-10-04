package dev.binaydungdung.productservice.services;

import java.util.List;

import dev.binaydungdung.productservice.dtos.GenericProductDto;
import dev.binaydungdung.productservice.exceptions.NotFoundException;

public interface ProductService {

	List<GenericProductDto> getAllProducts();
	
	GenericProductDto getProductById(Long id) throws NotFoundException;

	GenericProductDto createProduct(GenericProductDto genericProductDto);

	GenericProductDto deleteProduct(Long id) throws NotFoundException;

	GenericProductDto updateProduct(Long id, GenericProductDto genericProductDto);
	
}
