package dev.binaydungdung.productservice.services;

import dev.binaydungdung.productservice.dtos.ProductDto;
import dev.binaydungdung.productservice.dtos.ProductTitleRequestDto;
import dev.binaydungdung.productservice.exceptions.NotFoundException;

import java.util.List;

public interface CategoryService {

    List<String> getAllCategories() throws NotFoundException;

    List<ProductDto> getProductsByCategory(String category) throws NotFoundException;

    List<String> getProductsByCategories(ProductTitleRequestDto requestDto) throws NotFoundException;
}
