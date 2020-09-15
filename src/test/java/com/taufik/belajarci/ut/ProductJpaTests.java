package com.taufik.belajarci.ut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.taufik.belajarci.BelajarCiApplication;
import com.taufik.belajarci.dao.ProductDao;
import com.taufik.belajarci.model.Product;
import com.taufik.belajarci.repository.ProductRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BelajarCiApplication.class)
@DataJpaTest
public class ProductJpaTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository repository;

	@Test
	public void should_find_no_product_if_repository_is_empty() {
		Iterable<Product> products = repository.findAll();

		assertThat(products).isEmpty();
	}

	@Test
	public void should_store_a_product() {
		Product product = new Product();
		product.setCode("T-008XX");
		product.setName("Server X");
		product.setId("T-008");
		product.setPrice(new BigDecimal("101000.01"));

		Product products = repository.save(product);

		assertThat(products).hasFieldOrPropertyWithValue("code", "T-008XX");
		assertThat(products).hasFieldOrPropertyWithValue("name", "Server X");
		assertThat(products).hasFieldOrPropertyWithValue("id", "T-008");
	}

	@Test
	public void should_find_all_products() {
		Product product = new Product();
		product.setCode("T-009");
		product.setName("Server X");
		product.setId("T-008");
		product.setPrice(new BigDecimal("101000.01"));
		entityManager.persist(product);

		Product product2 = new Product();
		product2.setCode("T-010");
		product2.setName("Server Y");
		product2.setId("T-010");
		product2.setPrice(new BigDecimal("201000.01"));
		entityManager.persist(product2);

		Product product3 = new Product();
		product3.setCode("T-011X");
		product3.setName("Server Y");
		product3.setId("T-011");
		product3.setPrice(new BigDecimal("301000.01"));
		entityManager.persist(product3);

		Iterable<Product> products = repository.findAll();

		assertThat(products).hasSize(3).contains(product, product2, product3);
	}

	@Test
	public void should_find_product_tutorial_by_id() {
		Product product = new Product();
		product.setCode("T-009");
		product.setName("Server X");
		product.setId("T-008");
		product.setPrice(new BigDecimal("101000.01"));
		entityManager.persist(product);

		Product foundProduk = repository.findById(product.getId()).get();
		assertThat(foundProduk).isEqualTo(product);

		// Product foundProduk2 = repository.findById("T-011").orElse(null);
		// assertNull(foundProduk2);
	}

	@Test
	public void should_find_byname_products() {
		Product product = new Product();
		product.setId("T-007");
		product.setCode("T-009");
		product.setName("Server X");
		product.setPrice(new BigDecimal("101000.01"));
		entityManager.persist(product);

		Product product2 = new Product();
		product2.setId("T-008");
		product2.setCode("T-010");
		product2.setName("Server X");
		product2.setPrice(new BigDecimal("101000.01"));
		entityManager.persist(product2);

		Iterable<Product> products = repository.findByNames("Server XY");

		assertThat(products).hasSize(2).contains(product, product2);
	}

	@Test
	public void should_update_product_by_id() {

		Product product = new Product();
		product.setId("T-009");
		product.setCode("T-009");
		product.setName("Server X");
		product.setPrice(new BigDecimal("101000.01"));
		entityManager.persist(product);

		Product product2 = new Product();
		product2.setId("T-008");
		product2.setCode("T-010");
		product2.setName("Server X");
		product2.setPrice(new BigDecimal("101000.01"));
		entityManager.persist(product2);

		Product produpdateTo = new Product();
		produpdateTo.setId("T-011");

		produpdateTo.setCode("T-011");
		produpdateTo.setName("Server XYZ");
		produpdateTo.setPrice(new BigDecimal("901000.01"));

		Product prod = repository.findById(product2.getId()).get();

		prod.setCode(produpdateTo.getCode());
		prod.setName(produpdateTo.getName());
		prod.setPrice(new BigDecimal("1901000.01"));
		repository.save(prod);

		Product prodCek = repository.findById(product2.getId()).get();

		assertThat(prodCek.getCode()).isEqualTo(product2.getCode());
		assertThat(prodCek.getName()).isEqualTo(product2.getName());
		assertThat(prodCek.getPrice()).isEqualTo(product2.getPrice());
	}

	@Test
	public void should_delete_product_by_id() {

		Product product = new Product();
		product.setCode("T-009");
		product.setName("Server X");
		product.setId("T-008");
		product.setPrice(new BigDecimal("101000.01"));
		entityManager.persist(product);

		Product product2 = new Product();
		product2.setCode("T-010");
		product2.setName("Server Y");
		product2.setId("T-010");
		product2.setPrice(new BigDecimal("201000.01"));
		entityManager.persist(product2);

		Product product3 = new Product();
		product3.setCode("T-011X");
		product3.setName("Server Y");
		product3.setId("T-011");
		product3.setPrice(new BigDecimal("301000.01"));
		entityManager.persist(product3);

		repository.deleteById(product2.getId());

		Iterable<Product> products = repository.findAll();

		assertThat(products).hasSize(2).contains(product, product3);
	}

	@Test
	public void should_delete_all_products() {
		Product product2 = new Product();
		product2.setCode("T-010");
		product2.setName("Server Y");
		product2.setId("T-010");
		product2.setPrice(new BigDecimal("201000.01"));
		entityManager.persist(product2);

		Product product3 = new Product();
		product3.setCode("T-011X");
		product3.setName("Server Y");
		product3.setId("T-011");
		product3.setPrice(new BigDecimal("301000.01"));
		entityManager.persist(product3);

		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}

}
