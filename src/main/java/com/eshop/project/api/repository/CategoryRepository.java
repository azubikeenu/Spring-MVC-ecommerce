package com.eshop.project.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eshop.project.api.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);

	Category findByCategoryId(String categoryId);

	Category findByAlias(String alias);

	@Query("SELECT c FROM Category c WHERE c.enabled = true ORDER BY c.name ASC")
	List<Category> findAllActiveCategories();
}
