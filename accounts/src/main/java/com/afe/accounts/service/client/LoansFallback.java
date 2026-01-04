package com.afe.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.afe.accounts.dto.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient {

    @Override
    public ResponseEntity<LoansDto> fetchLoan(String correlationId, String mobileNumber) {
        return null;
    }
}
