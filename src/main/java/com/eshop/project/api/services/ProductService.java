package com.eshop.project.api.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.eshop.project.api.entities.Product;

public interface ProductService {
	Product createProduct(Product product);

	List<Product> getAllProducts();

	List<Product> getAllActiveProducts();

	Product findProductByAlias(String alias);

	Product findByProductId(String productId);

	Product updateProduct(String productId, Product product);

	void deleteProduct(String productId);

	Product isNameUnique(String name);

	Page<Product> listByPage(int pageNumb);

	List<Product> filterProductByName(String keyword);

}
