package dev.binaydungdung.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.binaydungdung.productservice.dtos.GenericProductDto;

@Service("selfStoreProductService")
public class SelfStoreProductService implements ProductService {

	@Override
	public List<GenericProductDto> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericProductDto getProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto genericProductDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericProductDto deleteProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
