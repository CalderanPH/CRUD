package br.paulocalderan.projetocrud.domain.request;

import br.paulocalderan.projetocrud.domain.entity.Autor;
import br.paulocalderan.projetocrud.domain.entity.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequest {
    @NotNull(message = "Campo autor não pode estar vazio.")
    private Autor autor;

    @NotNull(message = "Campo editora não pode estar vazio.")
    private Editora editora;

    @NotBlank(message = "Campo nome não pode estar vazio.")
    private String name;

    @NotBlank(message = "Campo genero não pode estar vazio.")
    private String genero;

    private Integer qtdPaginas;

}
