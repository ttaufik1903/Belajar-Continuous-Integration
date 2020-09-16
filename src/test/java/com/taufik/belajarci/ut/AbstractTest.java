package com.taufik.belajarci.ut;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.taufik.belajarci.BelajarCiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = BelajarCiApplication.class)
@WebAppConfiguration
public class AbstractTest {

	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	@Test
    public void testA(){
        System.out.println("Hellosss");
    }
	
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
