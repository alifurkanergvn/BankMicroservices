package com.afe.accounts.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties is used to bind configuration properties to a Java class.
//The prefix attribute is used to specify the prefix of the configuration properties.
//However, for this annotation to work, the class must be registered with the Spring Boot application.
//For this registration, you can use the @EnableConfigurationProperties annotation in AccountsApplication.java.


@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {

}
