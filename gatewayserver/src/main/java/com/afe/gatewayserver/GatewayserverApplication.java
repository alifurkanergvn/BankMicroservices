package com.afe.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	// http://localhost:8072/actuator/gateway/routes ->
	/* Spring Cloud Gateway route configuration.
   Defines three routes for /mybank/accounts/**, /mybank/loans/**, /mybank/cards/**.
   Each route rewrites the incoming path by stripping the /mybank/<service>/ prefix using
   rewritePath with the regex (?<segment>.*) and forwards to the target service via
   load-balanced URIs lb://ACCOUNTS, lb://LOANS, lb://CARDS.
   This enables service discovery integration and clean internal endpoint paths. */
	@Bean
	public RouteLocator myBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/mybank/accounts/**")
						.filters(f -> f.rewritePath("/mybank/accounts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("accountsCircuitBreaker")
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://ACCOUNTS"))
				.route(p -> p.path("/mybank/loans/**")
						.filters(f -> f.rewritePath("/mybank/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.retry(retryConfig -> retryConfig.setRetries(3)
									.setMethods(HttpMethod.GET)		// Specifies that retries should only apply to GET requests
									.setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)))	// Configures exponential backoff: initial delay 100ms, max delay 1000ms, multiplier 2 (delay doubles each retry), jitter true (adds randomness to delay to prevent simultaneous retries)
						.uri("lb://LOANS"))
				.route(p -> p.path("/mybank/cards/**")
						.filters(f -> f.rewritePath("/mybank/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
										.setKeyResolver(userKeyResolver())))
						.uri("lb://CARDS"))
				.build();
	}

	// This bean customizes the default configuration for all circuit breakers created by ReactiveResilience4JCircuitBreakerFactory.
	// It sets the circuit breaker to use default settings and applies a time limiter with a 4-second timeout for each circuit breaker instance.
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}

	//For each key, a maximum of 1 request per second and 1 additional request for a burst are defined.
	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1);
	}

	//KeyResolver generates a key (key) from the incoming HTTP request and this key is used by the rate limiter.
	//Here the key is taken from the "user" field in the HTTP header of the request. If the "user" header is missing, the default value "anonymous" is used.
	//if the specified limit is exceeded, the relevant error message is returned (for example, HTTP 429 Too Many Requests).
	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}
}
