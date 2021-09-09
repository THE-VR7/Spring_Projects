package com.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.Vaccine;
import com.reactive.service.VaccineService;

import reactor.core.publisher.Flux;

@RestController
public class VaccineController {

	@Autowired
	VaccineService vaccineService;
	
	@GetMapping("/vaccines")
	public Flux<Vaccine> getVaccines(){
		return vaccineService.getVaccines();
	}
	
}
