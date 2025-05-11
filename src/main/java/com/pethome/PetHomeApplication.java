package com.pethome;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080", description = "开发环境")
        }
)
@SpringBootApplication
public class PetHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetHomeApplication.class, args);
    }

}