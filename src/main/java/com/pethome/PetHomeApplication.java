package com.pethome;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class PetHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetHomeApplication.class, args);
    }

}