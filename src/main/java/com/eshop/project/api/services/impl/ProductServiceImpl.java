package com.eshop.project.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshop.project.api.entities.Product;
import com.eshop.project.api.exceptions.ProductServiceException;
import com.eshop.project.api.models.response.ErrorMessages;
import com.eshop.project.api.repository.ProductRepository;
import com.eshop.project.api.services.ProductService;
import com.eshop.project.api.shared.utils.Utils;

@Service
public class ProductServiceImpl implements ProductService {

	private static final int PAGE_SIZE = 6;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Utils utils;

	@Override
	public Product createProduct(Product product) {
		santizeInput(product);
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	private void santizeInput(Product product) {
		product.setName(utils.normalizeString(product.getName()));
		product.setProductId(utils.generateRandomId(30));
		product.setAlias(utils.getStub(product.getName()));
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getAllActiveProducts() {
		return productRepository.getAllActiveProducts();
	}

	@Override
	public Product findProductByAlias(String alias) {
		Product product = productRepository.findByAlias(alias);
		if (product == null)
			throw new ProductServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		return product;
	}

	@Override
	public Product findByProductId(String productId) {
		Product product = productRepository.findByProductId(productId);
		if (product == null)
			throw new ProductServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		return product;
	}

	@Override
	public Product updateProduct(String productId, Product product) {
		Product foundProduct = productRepository.findByProductId(productId);

		if (foundProduct == null)
			throw new ProductServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		product.setId(foundProduct.getId());
		santizeInput(product);
		Product updatedProduct = productRepository.save(product);
		return updatedProduct;

	}

	@Override
	public void deleteProduct(String productId) {
		Product foundProduct = productRepository.findByProductId(productId);
		if (foundProduct == null)
			throw new ProductServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		productRepository.delete(foundProduct);

	}

	@Override
	public Product isNameUnique(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public Page<Product> listByPage(int pageNumb) {
		Pageable pageable = PageRequest.of(pageNumb - 1, PAGE_SIZE);

		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> filterProductByName(String keyword) {
		return productRepository.filterProductByName(keyword);
	}

}
