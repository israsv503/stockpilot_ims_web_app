package com.stockpilot.ims.repository;

import com.stockpilot.ims.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository is technically optional here, but good practice
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // Spring Data JPA automatically provides:
  // save(Product) - Create and Update
  // findById(Long) - Read one
  // findAll() - Read all
  // deleteById(Long) - Delete
}