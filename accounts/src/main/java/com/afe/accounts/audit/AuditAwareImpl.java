package com.afe.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    //This code enables JPA Auditing and sets a fixed auditor ("ACCOUNTS_MS") for audit fields like @CreatedBy or @LastModifiedBy.
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
