package br.paulocalderan.projetocrud.integracao.dto;

import br.paulocalderan.projetocrud.entity.Autor;
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

    public AutorDTO(Autor autor) {
        this.name = autor.getName();
    }
}
