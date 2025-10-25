package com.afe.gatewayserver.filters;

import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    /**
     * Filters incoming requests to check for the presence of a correlation ID in the headers.
     * If not present, generates a new correlation ID and sets it in the exchange.
     * Logs the correlation ID for tracing purposes.
     *
     * @param exchange the current server web exchange
     * @param chain the gateway filter chain
     * @return a Mono that indicates filter chain completion
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("myBank-correlation-id found in RequestTraceFilter : {}",
                    filterUtility.getCorrelationId(requestHeaders));
        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationID);
            logger.debug("myBank-correlation-id generated in RequestTraceFilter : {}", correlationID);
        }
        return chain.filter(exchange);
    }

    /**
     * Checks if the correlation ID is present in the request headers.
     *
     * @param requestHeaders the HTTP headers of the request
     * @return true if the correlation ID is present, false otherwise
     */
    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Generates a new unique correlation ID using UUID.
     *
     * @return a newly generated correlation ID as a String
     */
    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

}
