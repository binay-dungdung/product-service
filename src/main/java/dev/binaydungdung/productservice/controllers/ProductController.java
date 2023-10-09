package dev.binaydungdung.productservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.binaydungdung.productservice.dtos.GenericProductDto;
import dev.binaydungdung.productservice.exceptions.NotFoundException;
import dev.binaydungdung.productservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	
	// TODO: Use @Qualifier("${productService.type}")
	public ProductController(@Qualifier("selfStoreProductService") ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<GenericProductDto> getAllProducts() throws NotFoundException {
		return productService.getAllProducts();
	}
	
	@GetMapping("{id}")
	public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
		return productService.getProductById(id);
	}
	
	@PostMapping
	public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
		return productService.createProduct(genericProductDto);
	}
	
	@PutMapping("{id}")
	public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto)
			throws NotFoundException {
		return productService.updateProduct(id, genericProductDto);
	}
	
	@DeleteMapping("{id}")
	public GenericProductDto deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
		return productService.deleteProduct(id);
	}
}
