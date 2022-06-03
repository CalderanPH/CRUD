package br.projetoCrud.autor;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nome;


    public Autor() {
    }

    public Autor(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }


}
