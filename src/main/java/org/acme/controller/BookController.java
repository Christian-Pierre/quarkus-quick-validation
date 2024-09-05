package org.acme.controller;

import org.acme.dto.BookDTO;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.Set;
import java.util.stream.Collectors;

@Path("/books")
public class BookController {
    @Inject
    Validator validator; 
    
    @POST
    public Result tryMeManualValidation(BookDTO book) {
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(book);
        if (violations.isEmpty()) {
            return new Result("Book is valid! It was validated by manual validation.");
        } else {
            return new Result(violations);
        }
    }
    public static class Result {

        Result(String message) {
            this.success = true;
            this.message = message;
        }

        Result(Set<? extends ConstraintViolation<?>> violations) {
            this.success = false;
            this.message = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
        }

        private String message;
        private boolean success;

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
