package dev.binaydungdung.productservice.controllers;

import dev.binaydungdung.productservice.dtos.CategoryDto;
import dev.binaydungdung.productservice.dtos.ProductDto;
import dev.binaydungdung.productservice.dtos.ProductTitleRequestDto;
import dev.binaydungdung.productservice.exceptions.NotFoundException;
import dev.binaydungdung.productservice.models.Category;
import dev.binaydungdung.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<String> getAllCategories() throws NotFoundException {
        return categoryService.getAllCategories();
    }

    @GetMapping("/products/{category}")
    public List<ProductDto> getProductsByCategory(@PathVariable("category") String category) throws NotFoundException {
        return categoryService.getProductsByCategory(category);
    }

//    @GetMapping("/products/titles")
//    public List<String> getProductTitlesByCategories(@RequestBody ProductTitleRequestDto requestDto) throws NotFoundException {
//        return categoryService.getProductsByCategories(requestDto);
//    }
}
