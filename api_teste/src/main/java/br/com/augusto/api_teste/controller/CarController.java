package br.com.augusto.api_teste.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.augusto.api_teste.model.Car;
import br.com.augusto.api_teste.repository.CarRepository;

@RestController
@RequestMapping("/api")
public class CarController {
	@Autowired
	CarRepository carRepository;

	@GetMapping("/carros")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Car> getAllCar() {
		return carRepository.findAll();
				//.stream().filter(this::isCool).collect(Collectors.toList());
	}

	private boolean isCool(Car car) {
		return !car.getNome().equals("AMC Gremlin") && !car.getNome().equals("Triumph Stag")
				&& !car.getNome().equals("Ford Pinto") && !car.getNome().equals("Yugo GV");
	}

	@PostMapping("/carros")
	@CrossOrigin(origins = "http://localhost:4200")
	public Car createCar(@RequestBody Car car) {
		//if(StringUtils.isEmpty(car.getEstado()))
			
			
		try {
			return carRepository.save(car);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PutMapping("/carros/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long carId, @Valid @RequestBody Car cardetalhes) {
		Car car = carRepository.findOne(carId);
		if (car == null) {
			return ResponseEntity.notFound().build();
		}
		car.setNome(cardetalhes.getNome());

		Car updatedcar = carRepository.save(car);
		return ResponseEntity.ok(updatedcar);
	}
	
	@DeleteMapping("/carros/{id}")
	public ResponseEntity<Car> deleteCar(@PathVariable(value = "id") Long carId) {
	    Car car = carRepository.findOne(carId);
	    if(car == null) {
	        return ResponseEntity.notFound().build();
	    }

	    carRepository.delete(car);
	    return ResponseEntity.ok().build();
	}

}
