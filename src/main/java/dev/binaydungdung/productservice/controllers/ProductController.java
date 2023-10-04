package dev.binaydungdung.productservice.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.binaydungdung.productservice.dtos.GenericProductDto;
import dev.binaydungdung.productservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	
	// TODO: Use @Qualifier("${productService.type}")
	public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public void getAllProducts() {
		
	}
	
	@GetMapping("{id}")
	public GenericProductDto getProductById(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}
	
	@PostMapping
	public String createProduct() {
		return "Product created with id: " + UUID.randomUUID();
	}
	
	@PutMapping("{id}")
	public void updateProductById(Long id) {
		
	}
	
	@DeleteMapping("{id}")
	public void deleteProductById(Long id) {
		
	}
}
