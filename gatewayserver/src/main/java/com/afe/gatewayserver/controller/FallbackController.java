package com.afe.gatewayserver.controller;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FallbackController handles fallback requests when the main services are unavailable.
 *
 * The method uses Mono because the application is built on a Reactive Stack (Spring WebFlux).
 *
 * Reactive Contract:
 * In a WebFlux application, the entire stack (from the controller to the database/client) must remain non-blocking.
 * Mono is a container that promises to produce a result (or an error) in the future. It doesn't return the string immediately; it returns the publisher of that string.
 *
 * Compatibility with spring-cloud-starter-circuitbreaker-reactor-resilience4j:
 * The reactor-resilience4j starter specifically expects reactive types (Mono or Flux) to function correctly.
 * The circuit breaker logic (timing out, counting failures) wraps around the Mono signal. If you returned a plain String, the reactive circuit breaker operators would not be able to subscribe to the execution flow.
 * Performance (Non-Blocking):
 *
 * Even though Mono.just(...) creates the data immediately in memory, returning it as a Mono ensures the framework handles it via the Reactor event loop, maintaining consistency with the rest of the non-blocking architecture.
 */

@RestController
public class FallbackController {

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }

}
