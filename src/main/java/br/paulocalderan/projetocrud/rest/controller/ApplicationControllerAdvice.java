package br.paulocalderan.projetocrud.rest.controller;

import br.paulocalderan.projetocrud.exception.RegraNegocioException;
import br.paulocalderan.projetocrud.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraDeNegocio(RegraNegocioException ex) {
        String message = ex.getMessage();
        return new ApiErrors(message);

    }
}
