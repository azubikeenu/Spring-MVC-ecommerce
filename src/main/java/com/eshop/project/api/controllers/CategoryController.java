package com.eshop.project.api.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.project.api.entities.Category;
import com.eshop.project.api.models.request.CategoryRequestDetails;
import com.eshop.project.api.models.response.CategoryResponse;
import com.eshop.project.api.models.response.OperationStatusModel;
import com.eshop.project.api.models.response.RequestOperationName;
import com.eshop.project.api.models.response.RequestOperationStatus;
import com.eshop.project.api.models.response.UserResponse;
import com.eshop.project.api.services.CategoryService;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CategoryResponse> findCategory(@PathVariable String id) {
		Category foundCategory = categoryService.findCategoryById(id);
		CategoryResponse returnedCategory = new ModelMapper().map(foundCategory, CategoryResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnedCategory);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequestDetails categoryRequestDetails) {
		Category savedCategory = categoryService.createCategory(categoryRequestDetails);
		CategoryResponse returnedCategory = new ModelMapper().map(savedCategory, CategoryResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnedCategory);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CategoryResponse>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		List<CategoryResponse> returnedValue = new ArrayList<>();
		if (categories != null && !categories.isEmpty()) {
			Type listType = new TypeToken<List<UserResponse>>() {
			}.getType();
			returnedValue = new ModelMapper().map(categories, listType);
		}
		return ResponseEntity.status(HttpStatus.OK).body(returnedValue);

	}

	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id,
			@RequestBody CategoryRequestDetails categoryRequestDetails) {
		Category updatedCategory = categoryService.updateCategory(categoryRequestDetails, id);
		CategoryResponse returnedValue = new ModelMapper().map(updatedCategory, CategoryResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnedValue);
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnedValue = new OperationStatusModel();
		categoryService.deleteCategory(id);
		returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		returnedValue.setOperationName(RequestOperationName.DELETE.name());
		return returnedValue;

	}

	@PostMapping("/check_category_name")
	public OperationStatusModel checkDuplicateEmail(@Param("name") String name) {
		Category uniqueCategory = categoryService.isNameUnique(name);
		if (uniqueCategory == null) {
			OperationStatusModel returnedValue = new OperationStatusModel();
			returnedValue.setOperationResult(RequestOperationStatus.CATEGORY_DOESNT_EXIST.name());
			returnedValue.setOperationName(RequestOperationName.IS_EMAIL_UNIQUE.name());
			return returnedValue;
		}
		return null;
	}

}
