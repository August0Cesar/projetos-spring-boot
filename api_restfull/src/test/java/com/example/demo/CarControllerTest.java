package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.utils.DateUtils;

import br.com.augusto.api_teste.controller.CarController;
import br.com.augusto.api_teste.model.Car;
import br.com.augusto.api_teste.repository.CarRepository;

public class CarControllerTest extends ApiTesteApplicationTests {
	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	CarController carController;

	@Autowired
	CarRepository carRepository;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
		this.carRepository.deleteAll();
		List<Car> listaCarros = Arrays.asList(
				new Car(new Long("1"), "Chevett", "MG", "BRANCO", new Date()),
				new Car(new Long("2"), "Chevett", "MG", "BRANCO", new Date()));

		this.carRepository.save(listaCarros);
	}

	protected String json(Car car) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		MappingJackson2HttpMessageConverter m = new MappingJackson2HttpMessageConverter();
		m.write(car, contentType, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	@Test
	public void createCarTest() throws Exception {
		System.out.println("Teste createCarTest()");
		Car c = new Car(new Long("1"), "Chevett","MG" , "BRANCO", new Date());
		String carJson = json(c);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/carros").
				contentType(this.contentType).content(carJson))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}
	//testa o serviço que retorna lista de carros
	@Test
	public void getAllCarsTes() throws Exception {
		System.out.println("Teste getAllCarsTest()");
		List<Car> listaCarros = this.carRepository.findAll();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/carros")
				.contentType(this.contentType))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(jsonPath("$[0].nome", is(listaCarros.get(0).getNome())))
				.andExpect(jsonPath("$[0].estado", is(listaCarros.get(0).getEstado())))
				.andExpect(jsonPath("$[0].dataFabricacao",is(DateUtils.formataDate(listaCarros.get(0).getDataFabricacao()))))
				.andExpect(jsonPath("$[0].cor", is(listaCarros.get(0).getCor())));
				
	}

}
