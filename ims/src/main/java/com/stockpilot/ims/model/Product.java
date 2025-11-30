package com.stockpilot.ims.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// NEW IMPORTS FOR VALIDATION
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Must not be null and must contain at least one non-whitespace character.
  @NotBlank(message = "Product Name is required")
  private String name;

  @NotBlank(message = "SKU is required")
  private String sku; // Stock Keeping Unit

  @NotNull(message = "Quantity is required")
  @Min(value = 0, message = "Quantity cannot be negative")
  private Integer quantity;

  // Must not be null, and must be at least 0.01 (ensuring a price exists).
  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than zero")
  private Double price;

  // --- Constructors ---

  // Default constructor (required by JPA/Hibernate)
  public Product() {
  }

  // Constructor for creating new products (without ID)
  public Product(String name, String sku, Integer quantity, Double price) {
    this.name = name;
    this.sku = sku;
    this.quantity = quantity;
    this.price = price;
  }

  // --- Getters and Setters ---

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}