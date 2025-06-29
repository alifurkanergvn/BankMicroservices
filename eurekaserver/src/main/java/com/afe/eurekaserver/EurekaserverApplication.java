package com.afe.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // This annotation enables the Eureka server functionality in the application
public class EurekaserverApplication {

	// We choose on start.spring.io with Eureka Server, Config and Spring Boot Actuator
	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}

}
