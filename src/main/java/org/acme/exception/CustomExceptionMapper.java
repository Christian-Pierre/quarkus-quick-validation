package org.acme.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception){
        Map<String, String> errors = new HashMap<>();
        // Obtém as violações do ConstraintViolationException
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        
        for (ConstraintViolation<?> violation : violations) {
            // Extrai o nome do campo do caminho de propriedade
            String field = violation.getPropertyPath().toString();
            // Obtém a mensagem de erro associada à violação
            String message = violation.getMessage();
            errors.put(field, message);
        }
        
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}
