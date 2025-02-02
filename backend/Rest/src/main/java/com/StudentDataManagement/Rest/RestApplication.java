package com.StudentDataManagement.Rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.StudentDataManagement.*"})
@EntityScan("com.StudentDataManagement.*")
@EnableJpaRepositories("com.StudentDataManagement.*")
public class RestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
