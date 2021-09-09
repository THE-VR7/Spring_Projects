package com.reactive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.reactive.providers.VaccineProvider;
import com.reactive.service.VaccineService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class VaccineProviderTest {

	@Autowired
	VaccineProvider provider;
	
	@MockBean
	VaccineService vaccineService;
	
	@Test
	void testVaccineProvider_reactive() {
		when(vaccineService.getVaccines())
		.thenReturn(Flux.just(new Vaccine("Pzier"),new Vaccine("covaxine"),new Vaccine("J&J")));
		StepVerifier.create(provider.provideVaccines())
					.expectSubscription()
					.assertNext(vaccine->{
						assertThat(vaccine.getName()).isNotNull();
						assertTrue(vaccine.isDelivered());
						assertEquals("Pzier", vaccine.getName());
					})
					.expectNext(new Vaccine("covaxine"))
					.expectNext(new Vaccine("J&J"))
					.expectComplete()
					.verify();
					
	}
	
	}

