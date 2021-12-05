package com.eshop.project.ui.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.project.api.entities.Category;
import com.eshop.project.api.entities.Product;
import com.eshop.project.api.entities.Role;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.services.CategoryService;
import com.eshop.project.api.services.ProductService;
import com.eshop.project.api.services.RoleService;
import com.eshop.project.api.services.UserService;
import com.eshop.project.api.shared.utils.Utils;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private Utils utils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleService roleService;

	@GetMapping("")
	String showIndex(Model model) {
		List<Product> products = productService.getAllActiveProducts();
		List<Category> categories = categoryService.findAllActiveCategories();
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		return "index.html";
	}

	@GetMapping("/login")
	String showLogin() {
		return "login.html";
	}

	@GetMapping("products/{alias}")
	String showProductDetails(@PathVariable String alias, Model model) {
		Product product = productService.findProductByAlias(alias);
		model.addAttribute("totalQuantity", product.getQuantity());
		model.addAttribute("product", product);
		return "product_detail.html";
	}

	@GetMapping("/cart")
	String showCart() {
		return "cart_view.html";
	}

	@GetMapping("/category/{alias}")
	String getAllProductsInCategory(@PathVariable String alias, Model model) {
		Category category = categoryService.findCategoryByAlias(alias);
		Set<Product> products = category.getProducts();
		model.addAttribute("categoryName", category.getName());
		model.addAttribute("products", products);
		return "category_products.html";
	}

	@PostMapping("/search")
	String filterProducts(@Param("keyword") String keyword, Model model) {
		List<Product> products = productService.filterProductByName(keyword);
		model.addAttribute("keyword", keyword);
		model.addAttribute("products", products);
		return "filter.html";

	}

	@GetMapping("/search")
	String filterProductWithGet(@Param("keyword") String keyword, Model model) {
		List<Product> products = (keyword != null) ? productService.filterProductByName(keyword)
				: productService.getAllActiveProducts();
		model.addAttribute("keyword", keyword);
		model.addAttribute("products", products);
		return "filter.html";

	}

	@GetMapping("/signup")
	String showRegisterationForm(Model model) {
		model.addAttribute("user", new User());
		return "sign_up.html";
	}

	@PostMapping("/signup")
	String signUp(User user, RedirectAttributes redirectAttributes) {
		Role roleUser = roleService.findByName("USER");
		user.addRole(roleUser);
		user.setActive(true);
		user.setUserId(utils.generateRandomUserId(30));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "ACCOUNT CREATED SUCCESSFULLY");
		return "redirect:/login";

	}

}
