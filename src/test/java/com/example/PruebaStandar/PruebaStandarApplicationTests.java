package com.example.PruebaStandar;

import com.example.PruebaStandar.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PruebaStandarApplicationTests {

	@Mock
	private  ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

}
