package com.eshop.project.api.services;

import java.util.List;

import com.eshop.project.api.entities.Category;
import com.eshop.project.api.models.request.CategoryRequestDetails;

public interface CategoryService {
	List<Category> getAllCategories();

	Category findCategoryByName(String name);

	Category createCategory(CategoryRequestDetails category);

	void deleteCategory(String categoryId);

	Category updateCategory(CategoryRequestDetails category, String categoryId);

	Category findCategoryById(String categoryId);

	Category isNameUnique(String name);

	List<Category> findAllActiveCategories();
}
