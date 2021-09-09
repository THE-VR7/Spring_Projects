package com.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.reactive.service.VaccineService;

import reactor.core.publisher.Mono;

@Controller
public class VaccineWebController {

	@Autowired
	VaccineService vaccineService;
	
	@GetMapping("/")
	public Mono<String> getVaccines(Model model){
		model.addAttribute("vaccines",vaccineService.getVaccines());
		return Mono.just("index");
	}
	
}
