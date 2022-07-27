package br.paulocalderan.projetocrud.service;

import br.paulocalderan.projetocrud.entity.Livro;
import br.paulocalderan.projetocrud.rest.controller.dto.LivroDTO;

import java.util.Optional;

public interface LivroService {
    Livro salvar(LivroDTO dto);

    void update(Long id, LivroDTO livroDTO);

    void delete(Long id);

    Optional<Livro> obterLivroCompleto(Long id);
}
