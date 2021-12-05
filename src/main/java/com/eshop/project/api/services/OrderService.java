package com.eshop.project.api.services;

import java.util.List;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.Order;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.request.OrderRequestDetails;
import com.eshop.project.api.models.request.PAYMENT_METHOD;
import com.eshop.project.api.models.request.ShippingDetails;

public interface OrderService {
	Order createOrder(User user, ShippingDetails shippingDetails, PAYMENT_METHOD payment_METHOD,
			List<CartItem> cartItems);

	Order findByOrderId(String orderId);

	void cancelOrder(User user);

	List<Order> findAllOrders();

	void makePayment(String orderId);

	void deleteOrder(String orderId);

	Order cancelPlacedOrder(String orderId);

	Order updateOrder(String orderId, OrderRequestDetails orderRequestDetails);

}
