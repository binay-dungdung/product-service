package dev.binaydungdung.productservice.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	@GetMapping
	public void getAllProducts() {
		
	}
	
	@GetMapping("{id}")
	public String getProductById(@PathVariable("id") Long id) {
		return "Here is the product with id: " + id;
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
