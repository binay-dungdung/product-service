package dev.binaydungdung.productservice.services;

import dev.binaydungdung.productservice.dtos.PriceDto;
import dev.binaydungdung.productservice.dtos.ProductDto;
import dev.binaydungdung.productservice.dtos.ProductTitleRequestDto;
import dev.binaydungdung.productservice.exceptions.ClientException;
import dev.binaydungdung.productservice.exceptions.NotFoundException;
import dev.binaydungdung.productservice.models.Category;
import dev.binaydungdung.productservice.models.Product;
import dev.binaydungdung.productservice.repositories.CategoryRepository;
import dev.binaydungdung.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<String> getAllCategories() throws NotFoundException {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("No categories found.");
        }

        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findByName(category);
        if (optionalCategory.isEmpty()) {
            throw new ClientException("Invalid category provided.");
        }

        List<Product> productList = optionalCategory.get().getProducts();
        if (productList.isEmpty()) {
            throw new NotFoundException("No products found for this category.");
        }

        return productList.stream()
                .map(entity -> {
                    ProductDto dto = new ProductDto();
                    dto.setTitle(entity.getTitle());
                    dto.setDescription(entity.getDescription());
                    dto.setImage(entity.getImage());
                    dto.setPrice(new PriceDto(entity.getPrice().getCurrency(), entity.getPrice().getPrice()));
                    return dto;
                })
                .toList();
    }

    @Override
    public List<String> getProductsByCategories(ProductTitleRequestDto requestDto) throws NotFoundException {
        List<Category> categories = categoryRepository.findAllByNameIn(requestDto.getCategories());
        if (categories.isEmpty()) {
            throw new ClientException("Invalid categories provided");
        }

        List<Product> products = productRepository.findAllByCategoryIn(categories);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found for this category.");
        }

        return products.stream()
                .map(Product::getTitle)
                .toList();
    }
}
