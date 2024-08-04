package com.StudentManagementSystem.Entity;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.StudentDataManagement.entity.Student;

@SpringBootApplication
public class UserActions {

	static RestTemplate restTemplate = new RestTemplate();
	static String baseUrl = "http://localhost:8080/student";
	
	/*
	
	private static void useExchangeMethodsOfRestTemplate() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		
		ResponseEntity<Student> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, requestEntity, Student.class);
		HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
		System.out.println("status code -"+ statusCode);
		Student user = responseEntity.getBody();
		System.out.println("response body - "+user);
		HttpHeaders responseHeaders = responseEntity.getHeaders();
		System.out.println("response Headwers - "+responseHeaders);
		
		
	}

*/
	
	public void addUser(Student student) throws Exception {
		if(student != null) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl+"/save", student, String.class);
			HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
			System.out.println("status code -"+ statusCode);
			String user = responseEntity.getBody();
			System.out.println("response body - "+user);
			HttpHeaders responseHeaders = responseEntity.getHeaders();
			System.out.println("response Headers - "+responseHeaders);
			
		}else {
			throw new Exception("student data not there.");
		}
	}
	
	
 public void modifyData(Student student) throws Exception {
		if(student != null) {
			restTemplate.put(baseUrl+"/update", student);
		}else {
			
			throw new Exception("student data not there.");
		}
	} 

	
public List<Student> getAllStudents() throws Exception {

	ResponseEntity<List<Student>> responseEntity = restTemplate.exchange(
	        baseUrl,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<List<Student>>() {});
	    return responseEntity.getBody();
	}


public void deleteUser(String id) throws Exception{
	if(id != null) {
		restTemplate.delete(baseUrl+"/delete/"+id);
	}else {
		throw new Exception("student data not there.");
	}
}


public List<Student> search(String studentId) throws Exception {
	if(studentId != null) {
		ResponseEntity<List<Student>> responseEntity = restTemplate.exchange(
		        baseUrl+"/"+studentId,
		        HttpMethod.GET,
		        null,
		        new ParameterizedTypeReference<List<Student>>() {});
		    return responseEntity.getBody();
	}else {
		throw new Exception("No student found with this data.");
	}
}

}
