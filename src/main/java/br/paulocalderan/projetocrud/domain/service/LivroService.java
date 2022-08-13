package br.paulocalderan.projetocrud.domain.service;

import br.paulocalderan.projetocrud.domain.dto.LivroDTO;
import br.paulocalderan.projetocrud.domain.entity.Livro;

import java.util.Optional;

public interface LivroService {
    Livro salvar(LivroDTO dto);

    void update(Long id, LivroDTO livroDTO);

    void delete(Long id);

    Optional<Livro> obterLivroCompleto(Long id);

}
