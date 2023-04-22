package br.paulocalderan.projetocrud.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Campo nome não pode estar vazio.")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private Set<Livro> livros;

    public Autor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Autor(String name) {
        this.name = name;
    }

    public Autor(Long id) {
        this.id = id;
    }

}