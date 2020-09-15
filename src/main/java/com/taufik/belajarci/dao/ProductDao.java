package com.taufik.belajarci.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.taufik.belajarci.model.Product;

public interface ProductDao extends JpaRepository<Product, String> {

 
}
