package br.paulocalderan.projetocrud.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    @NotBlank(message = "Campo autor não pode estar vazio.")
    private Integer autor;

    @NotBlank(message = "Campo editora não pode estar vazio.")
    private Integer editora;

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String name;

    @NotBlank(message = "Campo genero não pode estar vazio.")
    private String genero;

    @NotBlank(message = "Campo paginas não pode estar vazio.")
    private Integer qtdPaginas;

}
