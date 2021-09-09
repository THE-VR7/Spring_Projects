package com.reactive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class ReactiveDemoApplicationTests {

	@Autowired
	VaccineProvider provider;
	
	@Test
	void testVaccineProvider() {
		provider.provideVaccines().subscribe(new VaccineConsumer());
	}
	
	
	@Test
	void testVaccineProvider_reactive() {
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
	
	@Test
	void testVaccineProvider_reactive_expectcount() {
		StepVerifier.create(provider.provideVaccines())
					.expectSubscription()
					.expectNextCount(3)
					.expectComplete()
					.verify();
					
	}
	
	@Test
	void testMono() {
		Mono<String> mono = Mono.just("Macbook pro");
		mono.log().map(data->data.toUpperCase())
		.subscribe(System.out::println);
	}
	
	@Test
	void testFlux() throws InterruptedException {
//		Flux.just("Macbook pro","dell ","samsing")
		Flux.fromIterable(Arrays.asList("Macbook pro","dell ","samsing","sdasdasd","dwadwa2","asdasd1","Dasdw22"))
//		.delayElements(Duration.ofSeconds(2))
		.log().map(data->data.toUpperCase())
		.subscribe(new Subscriber<String>() {

			private long count = 0;
			private Subscription subscription;
			
			@Override
			public void onSubscribe(Subscription subscription) {
				this.subscription = subscription;
				subscription.request(2);
			}

			@Override
			public void onNext(String order) {
				count++;
				if(count>=2) {
					count=0;
					subscription.request(2);
				}
				System.out.println(order);
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("Completely done");
			}
		});
		
		
//		.subscribe(new OrderConsumer());
		
		
//		Thread.sleep(6000);
		
	}

}
