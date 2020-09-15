package com.taufik.belajarci.dao;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taufik.belajarci.BelajarCiApplication;
import com.taufik.belajarci.dao.ProductDao;
import com.taufik.belajarci.model.Product;

@SpringBootTest(classes = { BelajarCiApplication.class })

@RunWith(SpringJUnit4ClassRunner.class)
/* otomatis rollback tambahkan anotasi @@Transactional */
// @Transactional
// @Sql(scripts = { "/mysql/delete-data.sql", "/mysql/sample-product.sql" })
public class ProductControllerTests {

	@Autowired
	private ProductDao pd;
	private static final String BASE_URL = "/api/product";

	
	@Test
	public void testSave() {
		Product p = new Product();
		p.setId("T-006");
		p.setCode("T-006");
		p.setName("Test Product 001");
		p.setPrice(new BigDecimal("100000.01"));

		// Assert.assertNull(p.getId());
		pd.save(p);
		// Assert.assertNotNull(p.getId());
	}

	@Test
	public void testFindById() {
		Optional<Product> p = pd.findById("abc123");
		Assert.assertNotNull(p);
		Assert.assertEquals("P-002", p.get().getCode());
		Assert.assertEquals("Product 001", p.get().getName());
		Assert.assertEquals(BigDecimal.valueOf(101000.01), p.get().getPrice());

		Assert.assertNull(pd.findById("notexist"));
	}

}