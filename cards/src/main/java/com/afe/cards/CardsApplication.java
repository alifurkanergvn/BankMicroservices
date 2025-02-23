package com.afe.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.afe.cards.dto.CardsContactInfoDto;

@OpenAPIDefinition(
        info = @Info(
                title = "Microservice REST API Documentation",
                description = "MyBank Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Ali Furkan Erguven",
                        email = "alifurkanerguven@gmail.com",
                        url = "https://alifurkanergvn.github.io/"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "MyBank Accounts microservice REST API Documentation",
                url = "https://alifurkanergvn.github.io/"
        )
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardsContactInfoDto.class}) //It allows you to inject configuration properties (e.g., application.yml) into the fields of the AccountsContactInfoDto class.
@SpringBootApplication
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}
