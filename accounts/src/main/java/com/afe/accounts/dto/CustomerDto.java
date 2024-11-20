package com.afe.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @Schema(description = "Name of the customer", example = "Ali Furkan Erguven")
    @NotEmpty(message = "Name should not be empty or null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Schema(description = "Email address of the customer", example = "alifurkanerguven@gmail.com")
    @NotEmpty(message = "Email should not be empty or null")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Mobile Number of the customer", example = "5391234567")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the Customer")
    private AccountsDto accountsDto;
}
