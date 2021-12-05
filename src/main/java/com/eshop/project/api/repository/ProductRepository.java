package com.eshop.project.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eshop.project.api.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.enabled = true ")
	List<Product> getAllActiveProducts();

	Product findByAlias(String alias);

	Product findByProductId(String productId);

	Product findByName(String name);

	@Query("SELECT p from Product p WHERE p.name LIKE %:keyword%")
	List<Product> filterProductByName(String keyword);

//	@Query("SELECT p from Product p WHERE p.name LIKE %:keyword% OR p.category.name LIKE %:keyword%")
//	Page<Product> findAll(String keyword, Pageable pageable);
}
