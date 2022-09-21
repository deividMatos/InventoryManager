package com.project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Inventory Manager API", version = "2.0", description = "Inventory Manager"))
@SpringBootApplication
public class DefaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultApplication.class, args);
	}

}
