package com.eshop.project.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.request.ShippingDetails;
import com.eshop.project.api.models.response.CheckoutInfo;
import com.eshop.project.api.services.CartItemService;
import com.eshop.project.api.services.ProductService;
import com.eshop.project.api.services.UserService;
import com.eshop.project.security.UserSecurity;

@Controller

public class ShippingViewController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private ProductService productService;

	@GetMapping("/shipping")
	public String showShippingForm(Model model) {
		ShippingDetails shippingDetails = new ShippingDetails();
		model.addAttribute("shippingDetails", shippingDetails);
		return "shipping_form.html";
	}

	@GetMapping("/submit_shipping")
	public String submitShippingDetails(ShippingDetails shippingDetails, Model model,
			@AuthenticationPrincipal UserSecurity loggedInUser) {
		String email = loggedInUser.getUsername();
		User user = userService.getUserByEmail(email);
		CheckoutInfo checkoutInfo = new CheckoutInfo();
		List<CartItem> cartItems = cartItemService.findByUser(user);
		double sumTotal = 0;
		for (CartItem item : cartItems) {
			sumTotal += item.getQuantity() * item.getProduct().getPrice();
		}
		checkoutInfo.setShipping(12);
		checkoutInfo.setShippingDetails(shippingDetails);
		checkoutInfo.setSubtotal(sumTotal);
		checkoutInfo.setTax(10);
		double total = checkoutInfo.getSubtotal() - (checkoutInfo.getShipping() + checkoutInfo.getTax());
		checkoutInfo.setTotal(total);

		model.addAttribute("checkoutInfo", checkoutInfo);
		model.addAttribute("cartItems", cartItems);

		return "check_out_summary.html";
	}

}
