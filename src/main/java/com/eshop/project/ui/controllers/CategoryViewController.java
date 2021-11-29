package com.eshop.project.ui.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.project.api.entities.Category;
import com.eshop.project.api.models.request.CategoryRequestDetails;
import com.eshop.project.api.models.response.CategoryResponse;
import com.eshop.project.api.services.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryViewController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String showCategories(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		List<CategoryResponse> returnedValue = new ArrayList<>();
		if (categories != null && !categories.isEmpty()) {
			Type listType = new TypeToken<List<CategoryResponse>>() {
			}.getType();
			returnedValue = new ModelMapper().map(categories, listType);
		}
		model.addAttribute("categories", returnedValue);

		return "categories.html";

	}

	@GetMapping("/create")
	private String showCreateCategoryForm(Model model) {
		CategoryRequestDetails category = new CategoryRequestDetails();
		model.addAttribute("category", category);
		return "create_category.html";
	}

	@PostMapping("/save")
	private String saveCategory(CategoryRequestDetails categoryRequestDetails, RedirectAttributes redirectAttributes) {
		categoryService.createCategory(categoryRequestDetails);
		redirectAttributes.addFlashAttribute("message", "CATEGORY HAS BEEN SAVED SUCCESSULLY");
		return "redirect:/admin/categories";

	}

	@GetMapping("/update/redirect")
	public String redirectUpdate(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "CATEGORY HAS BEEN UPDATED SUCCESSFULLY");
		return "redirect:/admin/categories";
	}

	@GetMapping("/delete/redirect")
	public String redirectDelete(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "CATEGORY HAS BEEN DELETED SUCCESSFULLY");
		return "redirect:/admin/categories";
	}

}
