package com.eshop.project.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eshop.project.api.entities.Order;
import com.eshop.project.api.services.OrderService;

@Controller

public class OrderViewController {
	@Autowired
	private OrderService orderService;

	@GetMapping("/admin/orders")
	public String getAllOrders(Model model) {
		List<Order> orders = orderService.findAllOrders();
		model.addAttribute("orders", orders);
		return "orders.html";

	}

	@GetMapping("/orders/{orderId}")
	public String getOrder(Model model, @PathVariable String orderId) {
		Order order = orderService.findByOrderId(orderId);
		model.addAttribute("order", order);
		return "order_summary.html";
	}

	@GetMapping("/orders/show_card")
	public String showCard() {
		return "card.html";
	}

}
