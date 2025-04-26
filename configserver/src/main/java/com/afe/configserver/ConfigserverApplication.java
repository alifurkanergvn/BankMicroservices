package com.afe.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // the application is configured to read configuration properties from a central location
// (such as a Git repository or a file system) and serve them to client applications.
// http://localhost:8071/accounts/prod (http://localhost:8071/{application}/{profile})
// http://localhost:8071/accounts/default
public class ConfigserverApplication {

	//We choose on start.spring.io with Config Client and Spring Boot Actuator
	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
	}

}
