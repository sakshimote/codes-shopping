package com.product.api.service;

import java.util.List;

import com.product.api.model.Product;



public interface ProductService {

	public Product addProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(String productid);
	
	public List<Product> getProductByType(String producttype);
	
	public List<Product> getProductByName( String productName);
	
	public List<Product> getProductByCategory( String category);
	
	public Product updateProducts(Product product, String productid);
	
	public String deleteProductById(String productid);

}
