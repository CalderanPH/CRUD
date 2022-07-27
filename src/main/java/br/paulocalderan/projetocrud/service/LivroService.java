package br.paulocalderan.projetocrud.service;

import br.paulocalderan.projetocrud.entity.Livro;
import br.paulocalderan.projetocrud.rest.controller.dto.LivroDTO;

public interface LivroService {
    Livro salvar(LivroDTO dto);


}
