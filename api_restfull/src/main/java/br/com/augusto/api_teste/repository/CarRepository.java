package br.com.augusto.api_teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.augusto.api_teste.model.Car;


@CrossOrigin(origins = "http://localhost:4200")
public interface CarRepository extends JpaRepository<Car, Long>{

}
