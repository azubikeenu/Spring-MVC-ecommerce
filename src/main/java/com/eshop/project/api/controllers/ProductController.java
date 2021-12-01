package com.eshop.project.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.project.api.entities.Product;
import com.eshop.project.api.models.response.OperationStatusModel;
import com.eshop.project.api.models.response.RequestOperationName;
import com.eshop.project.api.models.response.RequestOperationStatus;
import com.eshop.project.api.services.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/check_product_name")
	public OperationStatusModel checkDuplicateEmail(@Param("name") String name) {
		Product uniqueCategory = productService.isNameUnique(name);
		if (uniqueCategory == null) {
			OperationStatusModel returnedValue = new OperationStatusModel();
			returnedValue.setOperationResult(RequestOperationStatus.PRODUCT_DOES_NOT_EXIST.name());
			returnedValue.setOperationName(RequestOperationName.IS_PRODUCT_UINQUE.name());
			return returnedValue;
		}
		return null;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnedValue = new OperationStatusModel();
		productService.deleteProduct(id);
		returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		returnedValue.setOperationName(RequestOperationName.DELETE.name());
		return returnedValue;

	}

}
