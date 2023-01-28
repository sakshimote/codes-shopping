package com.product.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.product.api.model.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findByCategory(String category);

	List<Product> findByProductName(String productName);

	List<Product> findByProductType(String producttype);


	
	
	

}
