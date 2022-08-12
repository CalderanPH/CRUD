package br.paulocalderan.projetocrud.integracao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    @NotNull(message = "Campo autor não pode estar vazio.")
    private Integer autor;

    @NotNull(message = "Campo editora não pode estar vazio.")
    private Integer editora;

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String name;

    @NotBlank(message = "Campo genero não pode estar vazio.")
    private String genero;

    private Integer qtdPaginas;

}
