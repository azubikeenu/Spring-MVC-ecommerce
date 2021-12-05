package com.eshop.project.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/admin/orders/update/redirect")
	public String redirectUpdate(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "ORDER HAS BEEN UPDATED SUCCESSFULLY");
		return "redirect:/admin/orders";
	}

	@GetMapping("/admin/orders/delete/redirect")
	public String redirectDelete(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "ORDER HAS BEEN DELETED SUCCESSFULLY");
		return "redirect:/admin/orders";
	}

	@GetMapping("/orders/{orderId}")
	public String getOrder(Model model, @PathVariable String orderId) {
		Order order = orderService.findByOrderId(orderId);
		model.addAttribute("order", order);
		return "order_summary.html";
	}

	@GetMapping("/orders/confirm_payment/{id}")
	public String showCard(@PathVariable("id") String orderId, Model model) {
		model.addAttribute("orderId", orderId);
		return "card.html";
	}

	@PostMapping("/orders/make_payment")
	public String makePayment(@Param("orderId") String orderId, RedirectAttributes redirectAttributes) {
		orderService.makePayment(orderId);
		redirectAttributes.addFlashAttribute("message", "PAYMENT MADE SUCCESSFULLY");
		return "redirect:/orders/" + orderId;
	}

	@GetMapping("/orders/cancel_order/{orderId}")
	public String cancelPlacedOrder(@PathVariable String orderId) {
		orderService.cancelPlacedOrder(orderId);
		return "redirect:/orders/" + orderId;
	}

}
