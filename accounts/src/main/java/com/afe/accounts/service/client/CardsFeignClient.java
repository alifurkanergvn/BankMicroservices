package com.afe.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.afe.accounts.dto.CardsDto;

@FeignClient("cards") // My feign client will connect with the eureka server at the runtime and it will try to get all instance details with the logical names "cards"
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("myBank-correlation-id") String correlationId,
                                                     @RequestParam String mobileNumber);

}
