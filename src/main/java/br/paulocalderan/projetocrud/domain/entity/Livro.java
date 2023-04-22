package br.paulocalderan.projetocrud.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @NotEmpty(message = "Campo nome não pode estar vazio.")
    private String name;

    @NotEmpty(message = "Campo genero não pode estar vazio.")
    private String genero;

    private Integer qtdPaginas;

    public Livro(String name, String genero, Integer qtdPaginas) {
        this.name = name;
        this.genero = genero;
        this.qtdPaginas = qtdPaginas;
    }

    public Livro(Autor autor, Editora editora, String name, String genero, Integer qtdPaginas) {
        this.autor = autor;
        this.editora = editora;
        this.name = name;
        this.genero = genero;
        this.qtdPaginas = qtdPaginas;
    }

}