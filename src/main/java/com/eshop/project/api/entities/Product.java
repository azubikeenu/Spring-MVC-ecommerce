package com.eshop.project.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String productId;
	@Column
	private String description;
	@Column(columnDefinition = "varchar(255) default 'default.png'")
	private String image;
	@Column(columnDefinition = "boolean default true")
	private boolean inStock;
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;
	@Column(columnDefinition = "int default 1")
	private int quantity;
	@Column
	private double price;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column
	private String alias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quanitity) {
		this.quantity = quanitity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Transient
	public String getImagePath() {
		return (this.id == null || this.image == null) ? "/img/default.png"
				: "/product-images/" + this.id + "/" + this.image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productId=" + productId + ", description=" + description
				+ ", image=" + image + ", inStock=" + inStock + ", enabled=" + enabled + ", quantity=" + quantity
				+ ", price=" + price + ", category=" + category + ", alias=" + alias + "]";
	}

}
