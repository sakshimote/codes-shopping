package com.product.api.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Product {
	@Id
	private String productid;
	
	@Field
	private String productType;
	@Field
	private String productName;
	@Field
	private String category;
	@Field
	private Map<Integer, String> review;
	@Field
	private Map<Integer, String> rating;
	@Field
	private List<String> image;
	@Field
	private double price;
	@Field
	private String description;  
	@Field
	private Map<String, String> specification;  

	public Product() {
		
	}

	public String getProductid() {
		return productid;
	}


	public void setProductid(String productid) {
		this.productid = productid;
	}


	public String getProductType() {
		return productType;
	}


	public void setProducttype(String productType) {
		this.productType = productType;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Map<Integer, String> getReview() {
		return review;
	}


	public void setReview(Map<Integer, String> review) {
		this.review = review;
	}


	public Map<Integer, String> getRating() {
		return rating;
	}


	public void setRating(Map<Integer, String> rating) {
		this.rating = rating;
	}


	public List<String> getImage() {
		return image;
	}


	public void setImage(List<String> image) {
		this.image = image;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Map<String, String> getSpecification() {
		return specification;
	}


	public void setSpecification(Map<String, String> specification) {
		this.specification = specification;
	}


	public Product(String productid, String producttype, String productName, String category,
			Map<Integer, String> review, Map<Integer, String> rating, List<String> image, double price,
			String description, Map<String, String> specification, String marchantId) {
		super();
		this.productid = productid;
		this.productType = producttype;
		this.productName = productName;
		this.category = category;
		this.review = review;
		this.rating = rating;
		this.image = image;
		this.price = price;
		this.description = description;
		this.specification = specification;
		
	}





	@Override
	public String toString() {
		return "Product [productid=" + productid + ", producttype=" + productType + ", productName=" + productName
				+ ", category=" + category + ", review=" + review + ", rating=" + rating + ", image=" + image
				+ ", price=" + price + ", description=" + description + ", specification=" + specification + "]";
	}





}
