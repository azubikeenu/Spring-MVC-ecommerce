package com.eshop.project.api.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.Order;
import com.eshop.project.api.entities.OrderDetails;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.request.ORDER_STATUS;
import com.eshop.project.api.models.request.PAYMENT_METHOD;
import com.eshop.project.api.models.request.ShippingDetails;
import com.eshop.project.api.repository.CartItemRepository;
import com.eshop.project.api.repository.OrderRepository;
import com.eshop.project.api.services.OrderService;
import com.eshop.project.api.shared.utils.Utils;

@Service
public class OrderServiceImpl implements OrderService {
	private static double SHIPPING_COST = 10;
	private static double TAX_COST = 12;
	private static int DELIVERY_DAYS = 2;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private Utils utils;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order createOrder(User user, ShippingDetails shippingDetails, PAYMENT_METHOD payment_METHOD,
			List<CartItem> cartItems) {
		Order order = new Order();
		order.setUser(user);
		order.setFirstName(user.getFirstName());
		order.setLastName(user.getLastName());
		order.setEmail(user.getEmail());
		order.setAddress(shippingDetails.getAddress());
		order.setPaymentMethod(payment_METHOD);
		order.setOrderId(utils.generateRandomId(30));
		double subTotal = 0;
		for (CartItem item : cartItems) {
			subTotal += item.getProduct().getPrice() * item.getQuantity();
		}
		double total = subTotal + (TAX_COST + SHIPPING_COST);
		Set<OrderDetails> orderItems = new HashSet<>();
		for (CartItem item : cartItems) {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setProduct(item.getProduct());
			orderDetails.setQuantity(item.getQuantity());
			orderDetails.setUnitPrice(item.getProduct().getPrice());
			orderDetails.setSubTotal(item.getProduct().getPrice() * item.getQuantity());
			orderDetails.setOrder(order);
			orderItems.add(orderDetails);
		}
		order.setOrderDetails(orderItems);
		order.setPaymentMethod(payment_METHOD);
		if (payment_METHOD.name().equals(payment_METHOD.COD.name())) {
			order.setPaymentStatus(false);
		} else {
			order.setPaymentStatus(true);
		}
		order.setOrderStatus(ORDER_STATUS.PROCESSING);
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, DELIVERY_DAYS);
		order.setDeliveryDate(calender.getTime());
		order.setOrderTime(new Date());
		order.setTax(TAX_COST);
		order.setTotal(total);

		cartItemRepository.deleteByCustomer(user);

		Order savedOrder = orderRepository.save(order);
		return savedOrder;

	}

	@Override
	public Order findByOrderId(String orderId) {
		return orderRepository.findByOrderId(orderId);
	}

	@Override
	public void cancelOrder(User user) {
		cartItemRepository.deleteByCustomer(user);

	}

	@Override
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

}
