package com.taufik.belajarci.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.taufik.belajarci.model.Product;

public class ProductApiTests extends AbstractTest {


	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getProductsList() throws Exception {
		String uri = "/products";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = super.mapFromJson(content, Product[].class);

		assertTrue(productlist.length > 0);
	}

	@Test
	public void createProduct() throws Exception {
		String uri = "/products";
		Product product = new Product();
		product.setCode("T-030");
		product.setName("Server X");
		product.setId("T-030");
		product.setPrice(new BigDecimal("101000.01"));
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		
		String content = mvcResult.getResponse().getContentAsString();
		//assertEquals(content, "Product is created successfully");
		assertEquals(content, "{\"id\":\"T-030\",\"code\":\"T-030\",\"name\":\"Server X\",\"price\":101000.01}");
		int status = mvcResult.getResponse().getStatus();
		//assertEquals(201, status);
		assertEquals(200, status);
	}

	@Test
	public void updateProduct() throws Exception {
		String uri = "/products/T-030";
		Product product = new Product();
		product.setCode("T-030");
		product.setName("Server X");
		product.setId("T-030");
		product.setPrice(new BigDecimal("101000.01"));
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		 assertEquals(content, "{\"id\":\"T-030\",\"code\":\"T-030\",\"name\":\"Server X\",\"price\":101000.01}");
		
		//assertEquals(content, "Product is updated successsfully");
	}

	@Test
	public void deleteProduct() throws Exception {
		String uri = "/products/T-030";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		//assertEquals(content, "{\"id\":\"T-008\",\"code\":\"T-009X\",\"name\":\"Server X\",\"price\":101000.01}");
		assertEquals(content, "Product is deleted successsfully");
	}

}
