package com.taufik.belajarci.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taufik.belajarci.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	@Query(value = "SELECT * FROM product u WHERE u.name = ?1", nativeQuery = true)
	List<Product> findByNames(String name);

}