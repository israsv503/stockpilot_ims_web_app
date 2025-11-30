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

// NEW IMPORTS for the relationship annotation
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;





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

  // NEW FIELD AND ANNOTATIONS FOR THE RELATIONSHIP
  @ManyToOne
  @JoinColumn(name = "category_id") // This creates the category_id foreign key column in the product table
  private Category category;

  // --- Constructors ---

  // Default constructor (required by JPA/Hibernate)
  public Product() {
  }

  // Constructor for creating new products (without ID)
  public Product(String name, String sku, Integer quantity, Double price, Category category) {
    this.name = name;
    this.sku = sku;
    this.quantity = quantity;
    this.price = price;
    this.category = category;
  }

  // --- Getters and Setters ---

  // NEW Getter for Category
  public Category getCategory() {
    return category;
  }

  // NEW Setter for Category
  public void setCategory(Category category) {
    this.category = category;
  }

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