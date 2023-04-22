package br.paulocalderan.projetocrud.domain.service;

import br.paulocalderan.projetocrud.domain.request.LivroRequest;
import br.paulocalderan.projetocrud.domain.entity.Livro;

import java.util.Optional;

public interface LivroService {

    Livro salvar(LivroRequest dto);

    void update(Long id, LivroRequest livroRequest);

    void delete(Long id);

    Optional<Livro> obterLivroCompleto(Long id);

}