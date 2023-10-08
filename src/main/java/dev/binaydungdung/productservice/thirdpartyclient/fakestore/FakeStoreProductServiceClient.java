package dev.binaydungdung.productservice.thirdpartyclient.fakestore;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.binaydungdung.productservice.dtos.GenericProductDto;
import dev.binaydungdung.productservice.exceptions.NotFoundException;

@Service
public class FakeStoreProductServiceClient {

	@Value("${fakeStoreProductService.baseUrl}")
	private String baseUrl;
	
	@Value("${fakeStoreProductService.productUrl}")
	private String productUrl;
	
	private RestTemplate restTemplate;

	public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public List<FakeStoreProductDto> getAllProducts() {
//		ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
//				productBaseUrl, FakeStoreProductDto[].class);
		
		ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(
				baseUrl, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<FakeStoreProductDto>>() {});
		
		return response.getBody();
	}

	public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
				productUrl, FakeStoreProductDto.class, id);
		return response.getBody();
	}

	public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
				baseUrl, genericProductDto, FakeStoreProductDto.class);
		
		return response.getBody();
	}
	
	public FakeStoreProductDto deleteProduct(Long id) throws NotFoundException {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
				productUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
		return response.getBody();
	}
	
	public FakeStoreProductDto updateProduct(Long id, GenericProductDto genericProductDto) {
		ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
				productUrl, HttpMethod.PUT, 
				new HttpEntity<GenericProductDto>(genericProductDto), 
				FakeStoreProductDto.class, id);
		
		return response.getBody();
	}
	
}
