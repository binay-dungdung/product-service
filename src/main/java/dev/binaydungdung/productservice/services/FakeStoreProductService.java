package dev.binaydungdung.productservice.services;

import dev.binaydungdung.productservice.dtos.GenericProductDto;
import dev.binaydungdung.productservice.exceptions.NotFoundException;
import dev.binaydungdung.productservice.thirdpartyclient.fakestore.FakeStoreProductDto;
import dev.binaydungdung.productservice.thirdpartyclient.fakestore.FakeStoreProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

	private final FakeStoreProductServiceClient fakeStoreProductServiceClient;
	
	public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
		this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
	}

	@Override
	public List<GenericProductDto> getAllProducts() {
		List<GenericProductDto> genericProducts = new ArrayList<>();
		fakeStoreProductServiceClient.getAllProducts().forEach(
			fakeStoreProductDto -> genericProducts.add(getGenericProductFromFakeStoreProduct(fakeStoreProductDto))
		);

		return genericProducts;
	}

	@Override
	public GenericProductDto getProductById(Long id) throws NotFoundException {
		FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.getProductById(id);

		if (fakeStoreProductDto == null) {
			throw new NotFoundException("Product with id " + id + " does not exists.");
		}

		return getGenericProductFromFakeStoreProduct(fakeStoreProductDto);
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto genericProductDto) {
		FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.createProduct(genericProductDto);

		return getGenericProductFromFakeStoreProduct(fakeStoreProductDto);
	}
	
	@Override
	public GenericProductDto deleteProduct(Long id) throws NotFoundException {
		FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.deleteProduct(id);

		if (fakeStoreProductDto == null) {
			throw new NotFoundException("Product with id " + id + " does not exists.");
		}

		return getGenericProductFromFakeStoreProduct(fakeStoreProductDto);
	}

	@Override
	public GenericProductDto updateProduct(Long id, GenericProductDto genericProductDto) {
		FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.updateProduct(id, genericProductDto);

		return getGenericProductFromFakeStoreProduct(fakeStoreProductDto);
	}

	private GenericProductDto getGenericProductFromFakeStoreProduct(FakeStoreProductDto fakeStoreProductDto) {
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
