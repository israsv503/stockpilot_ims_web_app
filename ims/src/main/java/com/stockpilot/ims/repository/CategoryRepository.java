package com.stockpilot.ims.repository;

import com.stockpilot.ims.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  // Inherits all C.R.U.D. operations for the Category entity.
}