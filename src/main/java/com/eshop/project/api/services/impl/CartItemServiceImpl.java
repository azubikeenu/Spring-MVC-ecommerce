package com.eshop.project.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.repository.CartItemRepository;
import com.eshop.project.api.services.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public List<CartItem> saveAllItems(List<CartItem> cartitems) {
		List<CartItem> savedItems = cartItemRepository.saveAll(cartitems);
		return savedItems;
	}

}
