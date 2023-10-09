package dev.binaydungdung.productservice.repositories;

import dev.binaydungdung.productservice.models.Category;
import dev.binaydungdung.productservice.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    int deleteProductById(Long id);

    List<Product> findAllByCategoryIn(List<Category> categories);
}
