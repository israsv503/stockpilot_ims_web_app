package com.stockpilot.ims.repository;

import com.stockpilot.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository is technically optional here, but good practice
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // Spring Data JPA automatically provides:
  // save(Product) - Create and Update
  // findById(Long) - Read one
  // findAll() - Read all
  // deleteById(Long) - Delete


  // NEW METHOD: Spring Data JPA will automatically generate the query:
  // SELECT * FROM product WHERE name LIKE %keyword% OR sku LIKE %keyword%
  List<Product> findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(String nameKeyword, String skuKeyword);
}

