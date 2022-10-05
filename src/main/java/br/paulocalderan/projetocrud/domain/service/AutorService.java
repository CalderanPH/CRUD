package br.paulocalderan.projetocrud.domain.service;

import br.paulocalderan.projetocrud.domain.request.AutorRequest;
import br.paulocalderan.projetocrud.domain.entity.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    Autor salvar(AutorRequest dto);

    void update(Long id, AutorRequest autorRequest);

    void delete(Long id);

    Optional<Autor> obterAutor(Long id);

    List<Autor> findAll();
}
