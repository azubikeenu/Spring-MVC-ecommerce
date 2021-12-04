package com.eshop.project.api.models.request;

public class ShippingDetails {
	private String address;
	private String city;
	private String postalCode;
	private String country;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "ShippingDetails [address=" + address + ", city=" + city + ", postalCode=" + postalCode + ", country="
				+ country + "]";
	}

}
