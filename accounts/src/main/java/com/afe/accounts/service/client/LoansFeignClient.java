package com.afe.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.afe.accounts.dto.LoansDto;

@FeignClient("loans") // My feign client will connect with the eureka server at the runtime and it will try to get all instance details with the logical names "cards"
public interface LoansFeignClient {

    @GetMapping(value="/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam String mobileNumber);

}
