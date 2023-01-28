package com.product.api.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.model.Product;
import com.product.api.service.ProductServiceImpl;
import com.product.api.service.ProductService;





@RestController
@RequestMapping("/product")
public class ProductController  {
	@Autowired
	private ProductServiceImpl productServiceImpl;

	org.slf4j.Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	//Post all 
	
	@PostMapping("/allproduct")
	public Product addProduct(@RequestBody Product product) {
		logger.trace("add product method accessed");
	   return  productServiceImpl.addProduct(product);
	}
    
	//get all product
	
	@GetMapping("/allproduct")
	public List<Product> getAllProducts() {
		
		List<Product> productList = productServiceImpl.getAllProducts();
		logger.trace("get products method accessed");
		return productList;
	}
	
	
	//get Product By product Id
	
	@GetMapping("/allproduct/{productId}")
	public Product getProductById(@PathVariable("productId")String productId) {
		
		Product product = productServiceImpl.getProductById(productId);
		logger.trace("get products by productId method accessed");
		return product;
	}

	
	
	//get Product By Product type
	
	@GetMapping("/allproduct/type/{producttype}")
	public List<Product> getProductByType(@PathVariable ("producttype") String producttype) {
		// TODO Auto-generated method stub
		logger.trace("get products by producttype method accessed");
		return productServiceImpl.getProductByType(producttype);
	}
 
    //get product By Name
	
	@GetMapping("/allproduct/name/{productname}")
	public List<Product> getProductByName(@PathVariable("productname") String productname) {
		logger.trace("get products by productName method accessed");
		return productServiceImpl.getProductByName(productname);
	}

	//get Product By Category
	
	@GetMapping("/allproduct/category/{category}")
	public List<Product> getProductByCategory(@PathVariable("category") String category) {
		logger.trace("get products by category method accessed");
		return productServiceImpl.getProductByCategory(category);
	}

	
	//Update Profile by Id
	
	@PutMapping("/allproduct/{productId}")
	public Product updateProducts(@RequestBody Product product,
			                           @PathVariable("productId") String productId ) {
		
		// TODO Auto-generated method stub
		logger.trace("update products method accessed");
		return productServiceImpl.updateProducts(product, productId) ;
	}

	//Delete Profile By id
	
	@DeleteMapping("/allproduct/{productId}")
	public String deleteProductById(@PathVariable("productId") String productId) {
		// TODO Auto-generated method stub
		logger.trace("delete products method accessed");
		return productServiceImpl.deleteProductById(productId);
	}


}
