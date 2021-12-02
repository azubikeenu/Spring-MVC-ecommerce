package com.eshop.project.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eshop.project.api.entities.Product;
import com.eshop.project.api.services.ProductService;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;

	@GetMapping("")
	String showIndex(Model model) {
		List<Product> products = productService.getAllActiveProducts();
		model.addAttribute("products", products);
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
}
