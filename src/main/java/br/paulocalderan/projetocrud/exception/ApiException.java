package br.paulocalderan.projetocrud.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

}