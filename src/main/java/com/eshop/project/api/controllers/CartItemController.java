package com.eshop.project.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.Product;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.request.CartItemRequestDetails;
import com.eshop.project.api.models.response.OperationStatusModel;
import com.eshop.project.api.models.response.RequestOperationName;
import com.eshop.project.api.models.response.RequestOperationStatus;
import com.eshop.project.api.services.CartItemService;
import com.eshop.project.api.services.ProductService;
import com.eshop.project.api.services.UserService;
import com.eshop.project.security.UserSecurity;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartItemService cartItemService;

	@PostMapping(path = "/save_items", produces = { MediaType.APPLICATION_JSON_VALUE })
	OperationStatusModel savedCartItems(@RequestBody List<CartItemRequestDetails> cartItemRequestDetails,
			@AuthenticationPrincipal UserSecurity currentUser) {
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		String email = currentUser.getUsername();
		User loggedInUser = userService.getUserByEmail(email);
		List<CartItem> cartItems = new ArrayList<>();
		for (CartItemRequestDetails item : cartItemRequestDetails) {
			Product product = productService.findByProductId(item.getId());
			int quantity = Integer.valueOf(item.getQuantity());
			cartItems.add(new CartItem(loggedInUser, product, quantity));
		}

		List<CartItem> savedItems = cartItemService.saveAllItems(cartItems);
		operationStatusModel.setOperationName(RequestOperationName.SAVE_ITEMS_TO_CART.name());
		if (savedItems.size() > 0) {
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} else {
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
		}

		return operationStatusModel;
	}

}
