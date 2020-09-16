package com.taufik.belajarci.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taufik.belajarci.model.Product;
import com.taufik.belajarci.repository.ProductRepository;

@RestController
public class ProductRestController {

	@Autowired
	ProductRepository repository;

	@GetMapping("/products")
	List<Product> all() {

		Product product = new Product();
		product.setCode("T-020");
		product.setName("Server X");
		product.setId("T-020");
		product.setPrice(new BigDecimal("101000.01"));
		repository.save(product);

		return repository.findAll();
	}

	@PostMapping("/products")
	Product saveData() {

		repository.deleteAll();

		Product product = new Product();
		product.setCode("T-030");
		product.setName("Server X");
		product.setId("T-030");
		product.setPrice(new BigDecimal("101000.01"));

		return repository.save(product);

	}

	@PutMapping("/products/{id}")
	Product updateData(@PathVariable String id) {

		Product product = repository.findById(id).orElse(null);
		product.setCode("T-030");
		product.setName("Server X");
		product.setPrice(new BigDecimal("101000.01"));

		return repository.save(product);

	}

	@DeleteMapping("/products/{id}")
	String Delete(@PathVariable String id) {

		Product product = new Product();
		product.setCode("T-030");
		product.setName("Server X");
		product.setId("T-030");
		product.setPrice(new BigDecimal("101000.01"));
		repository.save(product);

		repository.deleteById(product.getId());
		String returns = "Product is deleted successsfully";

		return returns;

	}

}
