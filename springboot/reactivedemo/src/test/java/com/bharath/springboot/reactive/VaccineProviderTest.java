package com.bharath.springboot.reactive;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bharath.springboot.reactive.vaccine.Vaccine;
import com.bharath.springboot.reactive.vaccine.VaccineProvider;
import com.bharath.springboot.reactive.vaccine.VaccineService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class VaccineProviderTest {
	@Autowired
	VaccineProvider provider;
	
	@MockBean
	VaccineService service;
	
	@Test
	void testVaccineProvider_reactive() {
		when(service.getVaccines())
					.thenReturn(Flux.just(new Vaccine("Bharath"), new Vaccine("J&J"), new Vaccine("Covaxin")));
		StepVerifier.create(provider.provideVaccines())
					.expectSubscription()
					.expectNext(new Vaccine("Bharath"))
					.expectNext(new Vaccine("J&J"))
					.expectNext(new Vaccine("Covaxin"))
					.expectComplete()
					.verify();
		verify(service).getVaccines();
	}
	

}
