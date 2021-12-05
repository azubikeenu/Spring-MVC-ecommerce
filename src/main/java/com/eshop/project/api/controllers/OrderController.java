package com.eshop.project.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.project.api.entities.Order;
import com.eshop.project.api.models.request.OrderRequestDetails;
import com.eshop.project.api.models.response.OperationStatusModel;
import com.eshop.project.api.models.response.OrderResponse;
import com.eshop.project.api.models.response.RequestOperationName;
import com.eshop.project.api.models.response.RequestOperationStatus;
import com.eshop.project.api.services.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<OrderResponse> getOrder(@PathVariable String id) {
		Order foundOrder = orderService.findByOrderId(id);
		OrderResponse returnedValue = new ModelMapper().map(foundOrder, OrderResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnedValue);
	}

	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable String id,
			@RequestBody OrderRequestDetails orderRequestDetails) {
		Order updateOrder = orderService.updateOrder(id, orderRequestDetails);
		OrderResponse returnedValue = new ModelMapper().map(updateOrder, OrderResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnedValue);
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnedValue = new OperationStatusModel();
		orderService.deleteOrder(id);
		returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		returnedValue.setOperationName(RequestOperationName.DELETE.name());
		return returnedValue;

	}

}
