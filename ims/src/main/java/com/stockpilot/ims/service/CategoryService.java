package com.stockpilot.ims.service;

import com.stockpilot.ims.model.Category;
import com.stockpilot.ims.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  // Dependency Injection
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  // Read All Categories (for dropdown menus)
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  // Create/Update Category (we will add a controller for this later)
  public void saveCategory(Category category) {
    categoryRepository.save(category);
  }
}