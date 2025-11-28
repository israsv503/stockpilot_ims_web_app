package com.stockpilot.ims.service;

import com.stockpilot.ims.model.Product;
import com.stockpilot.ims.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  // Dependency Injection via Constructor
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  // CREATE / UPDATE
  public void saveProduct(Product product) {
    // Here, you could add business logic, like:
    // if (product.getQuantity() < 0) { throw new IllegalArgumentException("Quantity
    // cannot be negative"); }
    productRepository.save(product);
  }

  // READ ALL
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  // READ ONE
  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  // DELETE
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}