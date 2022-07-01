package br.projetoCrud.entity;

import br.projetoCrud.service.LivroService;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "Autor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Autor")
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAutor")
    private Long id;

    @Column(name = "autor_nome")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "autor_livro",
            joinColumns = @JoinColumn(name = "idAutor"),
            inverseJoinColumns = @JoinColumn(name="livro_id")
    )
    private List<Livro> livros;



}
