package com.eshop.project.api.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.project.api.entities.Category;
import com.eshop.project.api.exceptions.CategoryServiceException;
import com.eshop.project.api.models.request.CategoryRequestDetails;
import com.eshop.project.api.models.response.ErrorMessages;
import com.eshop.project.api.repository.CategoryRepository;
import com.eshop.project.api.services.CategoryService;
import com.eshop.project.api.shared.utils.Utils;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private Utils utils;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findCategoryByName(String name) {
		Category foundCategory = categoryRepository.findByName(name);
		if (foundCategory == null)
			throw new CategoryServiceException(ErrorMessages.COULD_NOT_FIND_CATEGORY.getErrorMessages());
		return foundCategory;
	}

	@Override
	public Category createCategory(CategoryRequestDetails category) {
		Category foundCategory = categoryRepository.findByName(category.getName());
		if (foundCategory != null)
			throw new CategoryServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessages());
		Category categoryEntity = new ModelMapper().map(category, Category.class);
		categoryEntity.setCategoryId(utils.generateRandomId(30));
		return categoryRepository.save(categoryEntity);

	}

	@Override
	public void deleteCategory(String categoryId) {
		Category foundCategory = categoryRepository.findByCategoryId(categoryId);
		if (foundCategory == null)
			throw new CategoryServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		categoryRepository.delete(foundCategory);

	}

	@Override
	public Category updateCategory(CategoryRequestDetails category, String categoryId) {
		Category foundCategory = categoryRepository.findByCategoryId(categoryId);
		if (foundCategory == null)
			throw new CategoryServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		foundCategory.setDescription(category.getDescription());
		foundCategory.setName(category.getName());
		foundCategory.setEnabled(category.isEnabled());
		Category updatedCategory = categoryRepository.save(foundCategory);
		return updatedCategory;

	}

	@Override
	public Category findCategoryById(String categoryId) {
		Category foundCategory = categoryRepository.findByCategoryId(categoryId);
		if (foundCategory == null)
			throw new CategoryServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		return foundCategory;
	}

	@Override
	public Category isNameUnique(String name) {
		return categoryRepository.findByName(name);
	}

}
