package br.projetoCrud.request;

import lombok.Data;

@Data
public class LivroPutRequestBody {
    private Long id;
    private String nome;
}
