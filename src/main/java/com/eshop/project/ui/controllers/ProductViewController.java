package com.eshop.project.ui.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.project.api.entities.Category;
import com.eshop.project.api.entities.Product;
import com.eshop.project.api.services.CategoryService;
import com.eshop.project.api.services.ProductService;
import com.eshop.project.api.shared.utils.FileUploadUtil;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@GetMapping("/create")
	public String showCreateProuct(Model model) {
		List<Category> categories = categoryService.findAllActiveCategories();
		model.addAttribute("categories", categories);
		Product product = new Product();
		product.setEnabled(true);
		product.setInStock(true);
		model.addAttribute("product", product);
		return "create_product.html";
	}

	@GetMapping
	public String showAllProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "products.html";

	}

	@GetMapping("/edit/{productId}")
	public String showEditForm(Model model, @PathVariable String productId) {
		Product product = productService.findByProductId(productId);
		List<Category> categories = categoryService.findAllActiveCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "edit_product.html";
	}

	@PostMapping("/update")
	public String updateProduct(Model model, @Param("productId") String productId, Product product,
			@RequestParam("imageFile") MultipartFile mulipartFile, @Param("image") String image) throws IOException {
		if (!mulipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(mulipartFile.getOriginalFilename());
			product.setImage(fileName);
			Product updatedProduct = productService.updateProduct(productId, product);
			String uploadDir = "product-images/" + updatedProduct.getId();
			if (Files.exists(Paths.get(uploadDir)))
				FileUploadUtil.cleanDirectory(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, mulipartFile);

		} else {
			product.setImage(image);
			productService.updateProduct(productId, product);
		}

		return "redirect:/admin/products";
	}

	@PostMapping("/save")
	public String createProduct(Product product, RedirectAttributes redirectAttributes,
			@RequestParam("imageFile") MultipartFile image) throws IOException {
		if (!image.isEmpty()) {
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			product.setImage(fileName);
			Product savedProduct = productService.createProduct(product);
			String uploadDir = "product-images/" + savedProduct.getId();
			if (Files.exists(Paths.get(uploadDir)))
				FileUploadUtil.cleanDirectory(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, image);

		} else {
			productService.createProduct(product);
		}

		return "redirect:/admin/products";
	}

}
