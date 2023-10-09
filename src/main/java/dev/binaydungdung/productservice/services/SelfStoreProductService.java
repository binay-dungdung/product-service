package dev.binaydungdung.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.binaydungdung.productservice.exceptions.NotFoundException;
import dev.binaydungdung.productservice.exceptions.ServerException;
import dev.binaydungdung.productservice.models.Category;
import dev.binaydungdung.productservice.models.Price;
import dev.binaydungdung.productservice.models.Product;
import dev.binaydungdung.productservice.repositories.CategoryRepository;
import dev.binaydungdung.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import dev.binaydungdung.productservice.dtos.GenericProductDto;

@Service("selfStoreProductService")
public class SelfStoreProductService implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public SelfStoreProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<GenericProductDto> getAllProducts() throws NotFoundException {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new NotFoundException("No products found.");
		}
		List<GenericProductDto> genericProductDtos = new ArrayList<>();
		products.forEach(product -> genericProductDtos.add(getProductDtoFromEntity(product)));

		return genericProductDtos;
	}

	@Override
	public GenericProductDto getProductById(Long id) throws NotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new NotFoundException("Product with id " + id + " does not exists.");
		}

        return getProductDtoFromEntity(product.get());
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto genericProductDto) {
		Product product = initProductEntityFromDto(genericProductDto, null);

		product = productRepository.save(product);
		genericProductDto.setId(product.getId());

		return genericProductDto;
	}

	@Override
	public GenericProductDto deleteProduct(Long id) throws NotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isEmpty()) {
			throw new NotFoundException("Product with id " + id + " does not exists.");
		}

		boolean deleted = productRepository.deleteProductById(id) > 0;
		if (!deleted) {
			throw new ServerException("Could not delete the product with id " + id);
		}

		return getProductDtoFromEntity(optionalProduct.get());
	}
	
	@Override
	public GenericProductDto updateProduct(Long id, GenericProductDto genericProductDto) throws NotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isEmpty()) {
			throw new NotFoundException("Product with id " + id + " does not exists.");
		}

		Product product = initProductEntityFromDto(genericProductDto, optionalProduct.get());
		productRepository.save(product);

		return getProductDtoFromEntity(product);
	}

	private GenericProductDto getProductDtoFromEntity(Product product) {
		GenericProductDto genericProductDto = new GenericProductDto();
		genericProductDto.setId(product.getId());
		genericProductDto.setTitle(product.getTitle());
		genericProductDto.setDescription(product.getDescription());
		genericProductDto.setImage(product.getImage());
		genericProductDto.setPrice(product.getPrice().getPrice());
		genericProductDto.setCategory(product.getCategory().getName());
		return genericProductDto;
	}

	private Product initProductEntityFromDto(GenericProductDto genericProductDto, Product product) {
		Optional<Category> optionalCategory = categoryRepository.findByName(genericProductDto.getCategory());
		Category category = optionalCategory.orElseGet(() -> new Category(genericProductDto.getCategory()));

		Price price;
		if (product == null) {
			product = new Product();
			price = new Price();
		} else {
			price = product.getPrice();
		}

		price.setCurrency("INR");
		price.setPrice(genericProductDto.getPrice());

		product.setTitle(genericProductDto.getTitle());
		product.setDescription(genericProductDto.getDescription());
		product.setImage(genericProductDto.getImage());
		product.setPrice(price);
		product.setCategory(category);
		return product;
	}

}
