package com.afe.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The @ResponseStatus annotation is used in Spring to define the HTTP status code
 * that should be returned to the client when an exception is thrown or a method is executed.
 *
 * - Applied to custom exception classes or directly to controller methods.
 * - Automatically sets the HTTP status code in the response without manual configuration.
 * - Parameters:
 *   - value: Specifies the HTTP status code (e.g., HttpStatus.BAD_REQUEST).
 *   - reason (optional): Provides an additional message for the status.
 *
 * Example:
 * Throwing an exception annotated with @ResponseStatus(HttpStatus.NOT_FOUND)
 * results in a 404 status code returned to the client.
 *
 * Enhances clean and maintainable error handling in Spring applications.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
