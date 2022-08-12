package br.paulocalderan.projetocrud.service;

import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.integracao.dto.AutorDTO;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    Autor salvar(AutorDTO dto);

    void update(Long id, AutorDTO autorDTO);

    void delete(Long id);

    Optional<Autor> obterAutor(Long id);

    List<Autor> findAll();
}
