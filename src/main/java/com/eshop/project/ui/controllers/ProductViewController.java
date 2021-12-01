package com.eshop.project.ui.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.eshop.project.api.shared.utils.Utils;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {

	private static final int PAGE_SIZE = 10;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private Utils utils;

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
	public String showAllFirstPage(Model model) {
		return listByPage(1, model);
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
			@RequestParam("imageFile") MultipartFile mulipartFile, @Param("image") String image,
			RedirectAttributes redirectAttributes) throws IOException {
		if (!mulipartFile.isEmpty()) {
			String fileName = utils.generateRandomId(7);
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
		redirectAttributes.addFlashAttribute("message", "PROUCT HAS BEEN UPDATED SUCCESSULLY");
		return "redirect:/admin/products";
	}

	@PostMapping("/save")
	public String createProduct(Product product, RedirectAttributes redirectAttributes,
			@RequestParam("imageFile") MultipartFile image) throws IOException {
		if (!image.isEmpty()) {
			String ext = StringUtils.cleanPath(image.getOriginalFilename()).split("\\.")[1];
			String fileName = utils.generateRandomId(10) + "." + ext;
			product.setImage(fileName);
			Product savedProduct = productService.createProduct(product);
			String uploadDir = "product-images/" + savedProduct.getId();
			if (Files.exists(Paths.get(uploadDir)))
				FileUploadUtil.cleanDirectory(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, image);

		} else {
			productService.createProduct(product);
		}

		redirectAttributes.addFlashAttribute("message", "PROUCT HAS BEEN SAVED SUCCESSULLY");
		return "redirect:/admin/products";
	}

	@GetMapping("/delete/redirect")
	public String redirectDelete(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "PRODUCT HAS BEEN DELETED SUCCESSFULLY");
		return "redirect:/admin/products";
	}

	@GetMapping("/page/{pageNumb}")
	public String listByPage(@PathVariable int pageNumb, Model model) {

		Page<Product> page = productService.listByPage(pageNumb);
		List<Product> products = page.getContent();
		long startCount = (pageNumb - 1) * PAGE_SIZE + 1;
		long endCount = startCount + PAGE_SIZE - 1;
		if (endCount > page.getTotalElements())
			endCount = page.getTotalElements();
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("startCount", startCount);
		model.addAttribute("currentPage", pageNumb);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("endCount", endCount);

		model.addAttribute("products", products);
		return "products.html";
	}

}
