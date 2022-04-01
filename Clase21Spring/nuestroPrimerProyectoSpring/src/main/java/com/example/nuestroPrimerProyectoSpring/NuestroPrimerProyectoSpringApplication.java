package com.example.nuestroPrimerProyectoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NuestroPrimerProyectoSpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(NuestroPrimerProyectoSpringApplication.class, args);
	}
	@GetMapping
	public String holaMundo(){
		return "Hola mundo";
	}

}
