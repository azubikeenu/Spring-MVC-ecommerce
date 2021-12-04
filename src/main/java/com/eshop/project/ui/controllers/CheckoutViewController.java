package com.eshop.project.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.request.PAYMENT_METHOD;
import com.eshop.project.api.models.request.ShippingDetails;
import com.eshop.project.api.services.CartItemService;
import com.eshop.project.api.services.OrderService;
import com.eshop.project.api.services.UserService;
import com.eshop.project.security.UserSecurity;

@Controller
@RequestMapping("/checkout")
public class CheckoutViewController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private OrderService orderService;

	@GetMapping
	public String showCheckOut(Model model, @AuthenticationPrincipal UserSecurity loggedInUser) {
		String email = loggedInUser.getUsername();
		User user = userService.getUserByEmail(email);
		List<CartItem> cartItems = cartItemService.findByUser(user);
		double sumTotal = 0;
		for (CartItem item : cartItems) {
			sumTotal += item.getQuantity() * item.getProduct().getPrice();
		}
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subTotal", sumTotal);
		model.addAttribute("shippingDetails", new ShippingDetails());

		return "order_form.html";
	}

	@PostMapping("/make_order")
	public String makeOrder(@AuthenticationPrincipal UserSecurity loggedInUser,
			@Param("paymentMethod") String paymentMethod, ShippingDetails shippingDetails, Model model) {
		PAYMENT_METHOD payMethod = (paymentMethod.equals("cod")) ? PAYMENT_METHOD.COD : PAYMENT_METHOD.DEBIT_CARD;
		String email = loggedInUser.getUsername();
		User user = userService.getUserByEmail(email);
		List<CartItem> cartItems = cartItemService.findByUser(user);

		orderService.createOrder(user, shippingDetails, payMethod, cartItems);

		return "order_confirmation";
	}

	@GetMapping("/cancel_order")
	public String cancelOrder(@AuthenticationPrincipal UserSecurity loggedInUser) {
		String email = loggedInUser.getUsername();
		User user = userService.getUserByEmail(email);
		orderService.cancelOrder(user);
		return "redirect:/";
	}

}
