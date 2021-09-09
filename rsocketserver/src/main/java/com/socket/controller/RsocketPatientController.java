package com.socket.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.socket.model.Claim;
import com.socket.model.ClinicalData;
import com.socket.model.Patient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RsocketPatientController {
	
	Logger logger = LoggerFactory.getLogger(RsocketPatientController.class);

	@MessageMapping("get-patient-data")
	public Mono<ClinicalData> requestResponse(@RequestBody Patient patient){
		logger.info("Received Patient -> "+patient);
		return Mono.just(new ClinicalData(90, "80/120"));
	}
	

	@MessageMapping("patient-checking-out")
	public Mono<Void> fireAndForgot(Patient patient){
		logger.info("PAtient Checking out " + patient);
		logger.info("Biilling initiated");
		return Mono.empty().then();
	}
	
	@MessageMapping("claim-stream")
	public Flux<Claim> requestStream(){
		return Flux.just(new Claim(1000f, "MRI SCAN"),
				new Claim(101200f, "MRI SCAN 2"),
				new Claim(100f, "Ultrasount SCAN"),
				new Claim(5000f, "Food")
				).delayElements(Duration.ofSeconds(2));
	}
	
}
