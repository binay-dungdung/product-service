package dev.binaydungdung.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.binaydungdung.productservice.dtos.FakeStoreProductDto;
import dev.binaydungdung.productservice.dtos.GenericProductDto;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

	private RestTemplate restTemplate;
	
	private String getProductUrl = "https://fakestoreapi.com/products/{id}";
	
	@Autowired
	public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public GenericProductDto getProductById(Long id) {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class, id);
		FakeStoreProductDto fakeStoreProductDto = response.getBody();
		
		GenericProductDto product = new GenericProductDto();
		product.setTitle(fakeStoreProductDto.getTitle());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setImage(fakeStoreProductDto.getImage());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setCategory(fakeStoreProductDto.getCategory());
		return product;
	}

}
