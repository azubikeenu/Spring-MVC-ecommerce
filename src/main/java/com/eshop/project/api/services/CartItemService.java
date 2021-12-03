package com.eshop.project.api.services;

import java.util.List;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.User;

public interface CartItemService {
	List<CartItem> saveAllItems(List<CartItem> cartitems);

	List<CartItem> findByUser(User user);

}
