package br.paulocalderan.projetocrud.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class ApiErrors {

    @Getter
    private final List<String> erros;

    public ApiErrors(String mensagemErro) {
        this.erros = Collections.singletonList(mensagemErro);
    }

}