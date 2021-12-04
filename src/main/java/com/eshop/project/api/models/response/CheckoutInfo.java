package com.eshop.project.api.models.response;

import org.springframework.stereotype.Component;

import com.eshop.project.api.models.request.ShippingDetails;

@Component
public class CheckoutInfo {

	private double subtotal;
	private double shipping;
	private float tax;
	private double total;
	private ShippingDetails shippingDetails;

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getShipping() {
		return shipping;
	}

	public void setShipping(double shipping) {
		this.shipping = shipping;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ShippingDetails getShippingDetails() {
		return shippingDetails;
	}

	public void setShippingDetails(ShippingDetails shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	@Override
	public String toString() {
		return "CheckoutInfo [subtotal=" + subtotal + ", shipping=" + shipping + ", tax=" + tax + ", total=" + total
				+ ", shippingDetails=" + shippingDetails + "]";
	}

}
