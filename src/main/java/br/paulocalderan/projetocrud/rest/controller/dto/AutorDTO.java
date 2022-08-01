package br.paulocalderan.projetocrud.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorDTO {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String name;

}
