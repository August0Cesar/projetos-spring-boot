package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.augusto.api_teste.ApiTesteApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ApiTesteApplication.class)
@TestPropertySource(locations="classpath:application_test.properties")
public class ApiTesteApplicationTests {

	@Test
	public void contextLoads() {
	}

}
