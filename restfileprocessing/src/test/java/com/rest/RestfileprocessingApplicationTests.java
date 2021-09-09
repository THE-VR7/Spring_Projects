package com.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RestfileprocessingApplicationTests {

	private static final String C_USERS_HP_DOCUMENTS_SPRING_DOWNLOADS = "C:\\Users\\HP\\Documents\\Spring\\downloads\\";
	private static final String HTTP_LOCALHOST_8080_DOWNLOAD = "http://localhost:8080/download/";
	private static final String HTTP_LOCALHOST_8080_UPLOAD = "http://localhost:8080/upload";
	@Autowired
	RestTemplate template;
	
	@Test
	void testUpload() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		body.add("file", new ClassPathResource("Screenshot (343).png"));
		
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
		ResponseEntity<Boolean> response = template.postForEntity(HTTP_LOCALHOST_8080_UPLOAD, httpEntity, Boolean.class);
		
		System.out.println(response.getBody());
		
	}
	
	@Test
	void testDownload() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
		
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		String file = "Screenshot (343).png";

		ResponseEntity<byte[]> response = template.exchange(HTTP_LOCALHOST_8080_DOWNLOAD+file,HttpMethod.GET,httpEntity,byte[].class);
		
		
		Files.write(Paths.get(C_USERS_HP_DOCUMENTS_SPRING_DOWNLOADS+file), response.getBody());
		
		System.out.println(response.getBody());
		
	}

}
