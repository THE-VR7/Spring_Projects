package com.rest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	private String upload_dir = "C:\\Users\\HP\\Documents\\Spring\\Uploads\\";
	
	@PostMapping("/upload")
	public boolean upload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		file.transferTo(new File(upload_dir + file.getOriginalFilename()));
		return true;
	}
	
	@GetMapping("/download/{file}")
	public ResponseEntity<byte[]> download(@PathVariable("file") String file) throws IOException{
		byte[] readAllBytes = Files.readAllBytes(new File(upload_dir+file).toPath());
	
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(readAllBytes, headers, HttpStatus.OK);
	}
}
