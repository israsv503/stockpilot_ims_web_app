package com.stockpilot.ims;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.stockpilot.ims.model.Category;
import com.stockpilot.ims.service.CategoryService;

@SpringBootApplication
public class ImsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsApplication.class, args);
	}

  // NEW BEAN: Runs code once after startup
    @Bean
    public CommandLineRunner demoCategoryData(CategoryService categoryService) {
        return args -> {
            // Check if categories already exist to prevent duplicates on every restart
            if (categoryService.getAllCategories().isEmpty()) {
                
                categoryService.saveCategory(new Category("Apparel"));
                categoryService.saveCategory(new Category("Accessories"));
                categoryService.saveCategory(new Category("Electronics"));
                categoryService.saveCategory(new Category("Home Goods"));
                
                System.out.println("--- Initial Categories Loaded ---");
            }
        };
    }
}
