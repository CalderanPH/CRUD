package br.projetoCrud.request;

import lombok.Data;

@Data
public class AutorPutRequestBody {
    private Long id;
    private String nome;
}
