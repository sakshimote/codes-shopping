package com.product.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.model.Product;
import com.product.api.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	//add product 
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	//get all products
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//get product by id
	public Product getProductById(String productid) {
		Optional<Product> productOptional=	productRepository.findById(productid);
		return productOptional.get();
	}
	
	
	//delete product by id
	public String deleteProductById(String productid) {
		productRepository.deleteById(productid);
		return "Deleted Succesfully";
	}
	
	//Update Product 
	public Product updateProducts(Product product, String productId) {
		
		Product productDb = productRepository.findById(productId).get();
		
		if(product.getProductType() !=null) {
			productDb.setProducttype(product.getProductType());
		}
		if(product.getProductName() !=null) {
			productDb.setProductName(product.getProductName());
		}
		if(product.getCategory() !=null) {
			productDb.setCategory(product.getCategory());
		}
		if(product.getRating() !=null) {
			productDb.setRating(product.getRating());
		}
		if(product.getReview() !=null) {
			productDb.setReview(product.getReview());
			
		}
		if(product.getImage() !=null) {
			productDb.setImage(product.getImage());
		}
		
		if(product.getPrice() != 0) {
			productDb.setPrice(product.getPrice());
		}
		
		if(product.getDescription() !=null) {
			productDb.setDescription(product.getDescription());
			
		}
		
		if(product.getSpecification() !=null) {
			productDb.setSpecification(product.getSpecification());
			
		}
		return productRepository.save(product);
		
		
	}
	
	//get Product By category
	public List<Product> getProductByCategory( String category){
		return productRepository.findByCategory(category);
	}
	
	//get Product By Name
	public List<Product> getProductByName( String productName){
		return productRepository.findByProductName(productName);
	}
	
	//get Product By Type 
	public List<Product> getProductByType(String producttype){
		return productRepository.findByProductType(producttype);
	}
	

		
	}
	
	
	


