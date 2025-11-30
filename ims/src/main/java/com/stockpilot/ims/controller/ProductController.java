package com.stockpilot.ims.controller;

import com.stockpilot.ims.model.Product;
import com.stockpilot.ims.service.CategoryService;
import com.stockpilot.ims.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// NEW IMPORT
import jakarta.validation.Valid;

@Controller
@RequestMapping("/") // Base path for all methods in this controller
public class ProductController {

  private final ProductService productService;
  private final CategoryService categoryService;

  // Dependency Injection
  public ProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService; // INJECT CATEGORY SERVICE
  }

  // Handles GET requests to the root URL (e.g., http://localhost:8080/)
  @GetMapping
  public String viewHomePage(Model model) {
    // 1. Calls the Service layer to get a list of all products
    model.addAttribute("listProducts", productService.getAllProducts());

    // We also want to pass the categories list to the homepage to display the category name
    model.addAttribute("listCategories", categoryService.getAllCategories()); // NEW
    
    // 2. Returns the name of the Thymeleaf template (products.html)
    return "products";    
  }

  // Handles GET requests to show the "Add New Product" form
  @GetMapping("/showNewProductForm")
  public String showNewProductForm(Model model) {
    // Create model attribute to bind form data
    Product product = new Product();
    model.addAttribute("product", product);

    // CRITICAL: Pass the list of all categories to the form for the dropdown
    model.addAttribute("listCategories", categoryService.getAllCategories());

    // Return the name of the Thymeleaf template (new_product.html)
    return "new_product";
  }

  // Handles POST requests to save a new product
  @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model) {
        
        // 1. Check if there are any validation errors
        if (bindingResult.hasErrors()) {
            // CRITICAL: If errors exist, re-fetch categories to reload the dropdown
          // correctly
          model.addAttribute("listCategories", categoryService.getAllCategories());
            // If errors exist, return the user back to the form
            // NOTE: We reuse "new_product" template here
            return "new_product"; 
        }
        
        // 2. If no errors, save the product
        productService.saveProduct(product);
        
        // 3. Redirect to the homepage list
        return "redirect:/";
    }

  // Handles GET requests to show the "Update" form for an existing product
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        
        // Get product from the service
        Product product = productService.getProductById(id).orElseThrow(
            () -> new IllegalArgumentException("Invalid product Id:" + id));

        // Set product as a model attribute to pre-populate the form
        model.addAttribute("product", product);

        // CRITICAL: Pass the list of all categories to the form for the dropdown
        model.addAttribute("listCategories", categoryService.getAllCategories());
        
        // We reuse the new_product.html template for updates!
        return "new_product"; 
    }

    // Handles GET requests to delete a product
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {

      // Call delete method on the Service layer
      this.productService.deleteProduct(id);

      // Redirect back to the homepage list
      return "redirect:/";
    }
}