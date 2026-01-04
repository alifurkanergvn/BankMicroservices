package com.afe.accounts.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.afe.accounts.dto.AccountsDto;
import com.afe.accounts.dto.CardsDto;
import com.afe.accounts.dto.CustomerDetailsDto;
import com.afe.accounts.dto.LoansDto;
import com.afe.accounts.entity.Accounts;
import com.afe.accounts.entity.Customer;
import com.afe.accounts.exception.ResourceNotFoundException;
import com.afe.accounts.mapper.AccountsMapper;
import com.afe.accounts.mapper.CustomerMapper;
import com.afe.accounts.repository.AccountsRepository;
import com.afe.accounts.repository.CustomerRepository;
import com.afe.accounts.service.ICustomerService;
import com.afe.accounts.service.client.CardsFeignClient;
import com.afe.accounts.service.client.LoansFeignClient;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoan(correlationId, mobileNumber);
        if (null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
