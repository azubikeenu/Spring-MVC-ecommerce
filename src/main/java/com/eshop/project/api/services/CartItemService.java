package com.eshop.project.api.services;

import java.util.List;

import com.eshop.project.api.entities.CartItem;

public interface CartItemService {
	List<CartItem> saveAllItems(List<CartItem> cartitems);

}
