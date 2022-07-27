package br.paulocalderan.projetocrud.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    @NotNull(message = "Campo autor não pode estar vazio.")
    private Integer autor;

    @NotNull(message = "Campo editora não pode estar vazio.")
    private Integer editora;

    @NotNull(message = "Campo nome não pode estar vazio.")
    private String name;

    @NotNull(message = "Campo genero não pode estar vazio.")
    private String genero;

    @NotNull(message = "Campo paginas não pode estar vazio.")
    private Integer qtdPaginas;

}
