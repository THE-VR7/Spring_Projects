package com.reactive.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reactive.Vaccine;
import com.reactive.service.VaccineService;

import reactor.core.publisher.Flux;

@Component
public class VaccineProvider {

	@Autowired
	private VaccineService vaccineService;

	public Flux<Vaccine> provideVaccines() {
		return vaccineService.getVaccines().map(this::deliver);

	}

	private Vaccine deliver(Vaccine vaccine) {
		vaccine.setDelivered(true);
		return vaccine;
	}

}
