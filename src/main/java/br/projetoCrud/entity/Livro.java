package br.projetoCrud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;


import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "livros")
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLivro")
    private Long id;

    @Column(name = "livro_nome")
    private String name;
    @Column(name = "livro_genero")
    private String genero;
    @Column(name = "livro_qtdPaginas")
    private int qtdPaginas;


    @ManyToMany(mappedBy = "livros")
    private List<Autor> autores;




}
