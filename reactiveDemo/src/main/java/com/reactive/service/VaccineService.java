package com.reactive.service;

import org.springframework.stereotype.Service;

import com.reactive.Vaccine;

import reactor.core.publisher.Flux;

@Service
public class VaccineService {

	public Flux<Vaccine> getVaccines(){
		return Flux.just(new Vaccine("Pzier"),new Vaccine("covaxine"),new Vaccine("J&J"));
	}
	
}
