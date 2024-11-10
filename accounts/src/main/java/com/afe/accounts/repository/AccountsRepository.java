package com.afe.accounts.repository;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.afe.accounts.entity.Accounts;


@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional // Rolls back the transaction if a runtime error occurs
    @Modifying //JPA marks this as a modifying query within the @Transactional scope
    void deleteByCustomerId(Long customerId);

}
