package dev.binaydungdung.productservice.services;

import org.springframework.stereotype.Service;

import dev.binaydungdung.productservice.dtos.GenericProductDto;

@Service("selfStoreProductService")
public class SelfStoreProductService implements ProductService {

	@Override
	public GenericProductDto getProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
