package dev.binaydungdung.productservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.binaydungdung.productservice.dtos.FakeStoreProductDto;
import dev.binaydungdung.productservice.dtos.GenericProductDto;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

	private RestTemplate restTemplate;
	
	private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
	private String productBaseUrl = "https://fakestoreapi.com/products";
	
	@Autowired
	public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Override
	public List<GenericProductDto> getAllProducts() {
//		ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
//				productBaseUrl, FakeStoreProductDto[].class);
		
		ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
				productBaseUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<FakeStoreProductDto>>() {});
		
		List<GenericProductDto> genericProducts = new ArrayList<>();
		for (FakeStoreProductDto fakeStoreProductDto: response.getBody()) {
			GenericProductDto product = new GenericProductDto();
			product.setId(fakeStoreProductDto.getId());
			product.setTitle(fakeStoreProductDto.getTitle());
			product.setDescription(fakeStoreProductDto.getDescription());
			product.setImage(fakeStoreProductDto.getImage());
			product.setPrice(fakeStoreProductDto.getPrice());
			product.setCategory(fakeStoreProductDto.getCategory());
			
			genericProducts.add(product);
		}
		return genericProducts;
	}

	@Override
	public GenericProductDto getProductById(Long id) {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
				specificProductUrl, FakeStoreProductDto.class, id);
		FakeStoreProductDto fakeStoreProductDto = response.getBody();
		
		GenericProductDto product = new GenericProductDto();
		product.setId(fakeStoreProductDto.getId());
		product.setTitle(fakeStoreProductDto.getTitle());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setImage(fakeStoreProductDto.getImage());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setCategory(fakeStoreProductDto.getCategory());
		return product;
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto genericProductDto) {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
				productBaseUrl, genericProductDto, FakeStoreProductDto.class);
		FakeStoreProductDto fakeStoreProductDto = response.getBody();
		
		GenericProductDto product = new GenericProductDto();
		product.setId(fakeStoreProductDto.getId());
		product.setTitle(fakeStoreProductDto.getTitle());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setImage(fakeStoreProductDto.getImage());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setCategory(fakeStoreProductDto.getCategory());
		return product;
	}
	
	@Override
	public GenericProductDto deleteProduct(Long id) {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
				specificProductUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
		FakeStoreProductDto fakeStoreProductDto = response.getBody();
		
		GenericProductDto product = new GenericProductDto();
		product.setId(fakeStoreProductDto.getId());
		product.setTitle(fakeStoreProductDto.getTitle());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setImage(fakeStoreProductDto.getImage());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setCategory(fakeStoreProductDto.getCategory());
		return product;
	}
}
