package br.paulocalderan.projetocrud.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
        String errorDes = e.getLocalizedMessage();
        e.printStackTrace();
        if (errorDes == null){
            errorDes = e.toString();
        }
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorDes);
        return new ResponseEntity(errorMessage, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handleRegraDeNegocio(ApiException ex) {
        String message = ex.getMessage();
        return new ApiErrors(message);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(EntityExistsException.class)
    public ApiErrors handle(EntityExistsException ex) {
        ex.printStackTrace();
        return new ApiErrors(ex.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiErrors handle(EntityNotFoundException ex) {
        ex.printStackTrace();
        return new ApiErrors(ex.getMessage());
    }

}