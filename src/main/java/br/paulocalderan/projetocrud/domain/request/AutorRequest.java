package br.paulocalderan.projetocrud.domain.request;

import br.paulocalderan.projetocrud.domain.entity.Autor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorRequest {
    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String name;

    public AutorRequest(Autor autor) {
        this.name = autor.getName();
    }
}
