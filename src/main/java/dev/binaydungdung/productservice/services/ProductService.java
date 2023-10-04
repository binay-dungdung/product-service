package dev.binaydungdung.productservice.services;

import dev.binaydungdung.productservice.dtos.GenericProductDto;

public interface ProductService {

	GenericProductDto getProductById(Long id);
}
