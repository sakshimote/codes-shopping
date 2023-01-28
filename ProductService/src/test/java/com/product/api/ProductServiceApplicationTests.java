package com.product.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.api.controller.ProductController;
import com.product.api.model.Product;
import com.product.api.service.ProductServiceImpl;

@SpringBootTest
class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductServiceImpl productServiceImpl;
	
	Product product;
	
	List<Product> productlist;
	
	private final String productId = "786";
	
	
	
	private final String producttype= "premium jewellery";
	
	
	private final String productName="Tanishq Gold";
	
	private final String category="Women Fashion Jewellery";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		product = new Product();
		
		Map<Integer,String> map = new HashMap<>();
		map.put(1, "good");
		
		Map<Integer, String> man = new HashMap<>();
		man.put(1,"4.5");
		
		Map<String, String> mam = new HashMap<>();
		mam.put("price", "Very expensive");
		
		List<String> image = new ArrayList<>();
		image.add("url1");
		
		product.setProducttype("premium jewellery");
		product.setProductName("Tanishq Gold");
		product.setCategory("Women Fashion Jewellery");
		product.setDescription("Made by 24 carat gold");
		product.setPrice(50000);
		product.setReview(map);
		product.setRating(man);
		product.setSpecification(mam);
		product.setImage(image);
		
		
		productlist = new ArrayList<Product>();
		productlist.add(product);
		
		
	}
	
	@Test
	@Order(1)
	void addProduct() {
		// first check that all the required parameters are in the product class
		
		assertNotNull(product, "product is null");
		assertNotNull(product.getProductType(), "Product type is required");
		assertNotNull(product.getProductName(), "Product Name is required");
		assertNotNull(product.getCategory(), "product category is required");
		assertNotNull(product.getDescription(), "product description is required");
		assertNotNull(product.getPrice(), "product price is required");
		assertNotNull(product.getReview(), "product review is required");
		assertNotNull(product.getRating(), "product rating is required");
		assertNotNull(product.getSpecification(), "Product specification is required");
		assertNotNull(product.getImage(), "Product image is required");
		
		
		//get the Product call when meathod is call
		when(productServiceImpl.addProduct(product)).thenReturn(product);
		
		Product productRest = productController.addProduct(product);
		
		assertNotNull(productRest);
		
		assertEquals(product.getProductType(), productRest.getProductType());
		assertEquals(product.getProductName(), productRest.getProductName());
		assertEquals(product.getCategory(), productRest.getCategory());
		assertEquals(product.getDescription(), productRest.getDescription());
		assertEquals(product.getPrice(), productRest.getPrice());
		assertEquals(product.getReview(), productRest.getReview());
		assertEquals(product.getRating(), productRest.getRating());
		assertEquals(product.getSpecification(), productRest.getSpecification());
		assertEquals(product.getImage(), productRest.getImage());
		
	}
	
	@Test
	@Order(2)
	void getAllProducts() {
		
		when(productServiceImpl.getAllProducts()).thenReturn(productlist);
		assertEquals(1,productServiceImpl.getAllProducts().size() );
	}
	
	@Test
	@Order(3)
	void getProductById() {
		when(productServiceImpl.getProductById(productId)).thenReturn(product);
		
		Product productRest=productController.getProductById(productId);
		
		assertNotNull(productRest, "Product not avalable in db");
		
		assertEquals(product.getProductid(), productRest.getProductid());
		
		
		
	}
	
	@Test
	@Order(4)
	void getProductByType() {
		when(productServiceImpl.getProductByType(producttype)).thenReturn(productlist);
		
		productlist = productController.getProductByType(producttype);
		
		assertNotNull(product);
		
		assertEquals(product.getProductType(), "premium jewellery");
		
		
	}
	
	@Test
	@Order(5)
	void getProductByName() {
		when(productServiceImpl.getProductByName(productName)).thenReturn(productlist);
		
		productlist= productController.getProductByName(productName);
		
		assertNotNull(product);
		
		assertEquals(product.getProductName(),"Tanishq Gold");
	}
	
	
	@Test
	@Order(6)
	void getProductByCategory() {
		
		when(productServiceImpl.getProductByCategory(category)).thenReturn(productlist);
		
		productlist= productController.getProductByCategory(category);
		
		assertNotNull(product);
		
		assertEquals(product.getCategory(),"Women Fashion Jewellery");
	}
	
	@Test
	@Order(7)
	void updateProducts() {
		when(productServiceImpl.updateProducts(product, productId)).thenReturn(product);
		
		Product productRest=productController.updateProducts(product, productId);
		
		assertNotNull(productRest, "Product not avalable in DB/ id  incorrect");
		
		assertEquals(product.getProductid(), productRest.getProductid());
		
		
	}
	
	@Test
	@Order(8)
	//@Disabled
	void deleteProductById() {
		
		when(productServiceImpl.deleteProductById(productId)).thenReturn("Deleted Succesfully");
		
		when(productServiceImpl.getProductById(productId)).thenReturn(product);
		
		String delete = productController.deleteProductById(productId);
		
		assertEquals("Deleted Succesfully", delete ,"Product not avalable in db");
		
	}
	
	
	
}
