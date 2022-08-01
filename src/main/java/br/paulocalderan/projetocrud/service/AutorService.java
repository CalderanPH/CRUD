package br.paulocalderan.projetocrud.service;

import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.rest.controller.dto.AutorDTO;

import java.util.Optional;

public interface AutorService {
    Autor salvar(AutorDTO dto);

    void update(Long id, AutorDTO autorDTO);

    void delete(Long id);

    Optional<Autor> obterAutor(Long id);
}
