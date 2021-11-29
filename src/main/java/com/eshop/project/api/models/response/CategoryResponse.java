package com.eshop.project.api.models.response;

public class CategoryResponse {
	private String name;
	private String description;
	private String categoryId;
	private boolean enabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "CategoryResponse [name=" + name + ", description=" + description + ", categoryId=" + categoryId
				+ ", enabled=" + enabled + "]";
	}

}
