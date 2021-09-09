package com.socket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socket.model.Claim;
import com.socket.model.ClinicalData;
import com.socket.model.Patient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RSocketPatientClientController {

	private final RSocketRequester requester;
	
	Logger logger = LoggerFactory.getLogger(RSocketPatientClientController.class);


	public RSocketPatientClientController(@Autowired RSocketRequester.Builder builder) {
		super();
		this.requester = builder.tcp("localhost",7000);
	}
	
	@GetMapping("request-response")
	public Mono<ClinicalData> requestResponse(Patient patient){
		logger.info("Sending the request for patient -> "+patient);
		return requester.route("get-patient-data")
			.data(patient)
			.retrieveMono(ClinicalData.class);
	}
	
	
	
	@PostMapping("/fire-and-forget")
	public Mono<Void> fireandforget(@RequestBody Patient patient){
		logger.info("Patient being checked out"+patient);
		return requester.route("patient-checking-out")
		.data(patient)
		.retrieveMono(Void.class);
	}
	
	
	@GetMapping("/request-stream")
	public ResponseEntity<Flux<Claim>> requestStream(){
		Flux<Claim> data = requester.route("claim-stream")
				.retrieveFlux(Claim.class);
		
		return ResponseEntity.ok().contentType(MediaType.TEXT_EVENT_STREAM)
				.body(data);
	}
	
	
	
	
	
	
}





