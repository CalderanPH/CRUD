package br.paulocalderan.projetocrud.domain.service;

import br.paulocalderan.projetocrud.domain.dto.AutorDTO;
import br.paulocalderan.projetocrud.domain.entity.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    Autor salvar(AutorDTO dto);

    void update(Long id, AutorDTO autorDTO);

    void delete(Long id);

    Optional<Autor> obterAutor(Long id);

    List<Autor> findAll();
}
