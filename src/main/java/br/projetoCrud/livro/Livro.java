package br.projetoCrud.livro;

import br.projetoCrud.autor.Autor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String genero;

    @Getter @Setter
    private int qtdPaginas;

    @Getter @Setter
    private Autor autor;


    public Livro(String nome) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.qtdPaginas = qtdPaginas;
        Autor autor = new Autor();
            this.autor = autor;
    }

    public LivroEntidade clonar(){
        var livroEntidade = new LivroEntidade();

        livroEntidade.setNome(this.getNome());

        return livroEntidade;
    }


}
