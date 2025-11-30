package com.stockpilot.ims.service;

import com.stockpilot.ims.model.Product;
import com.stockpilot.ims.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

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

  // READ ALL / SEARCH
  public List<Product> getAllProducts(String keyword) {
    if (keyword != null && !keyword.isEmpty()) {
      // Use the custom search query if a keyword is provided
      return productRepository.findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(keyword, keyword);
    } else {
      // Fall back to reading all products if no keyword is provided
      return productRepository.findAll();
    }    
  }

  // Original method (we'll keep it for simplicity in the controller, but adjust
  // usage)
  // Note: We can simplify the Controller by using the above method directly.
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  // NEW TRANSACTIONAL METHOD
  @Transactional
  public void receiveNewStock(String sku, Integer quantityReceived) {

    // 1. Find the product by SKU
    Product product = productRepository.findBySku(sku)
        .orElseThrow(() -> new IllegalArgumentException("Error: Product with SKU " + sku + " not found."));

    // 2. Validate quantity received
    if (quantityReceived == null || quantityReceived <= 0) {
      throw new IllegalArgumentException("Error: Quantity received must be a positive number.");
    }

    // 3. Update the stock
    Integer currentQuantity = product.getQuantity();
    product.setQuantity(currentQuantity + quantityReceived);

    // 4. Save the updated product (within the transaction)
    productRepository.save(product);
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