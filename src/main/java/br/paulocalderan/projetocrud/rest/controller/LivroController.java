package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Livro;
import br.paulocalderan.projetocrud.repository.LivroRepository;
import br.paulocalderan.projetocrud.rest.controller.dto.LivroDTO;
import br.paulocalderan.projetocrud.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

//    @GetMapping("{id}")
//    public Livro getById(@PathVariable Long id) {
//        return livroRepository
//                .findById(id)
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                "Livro não encontrado."));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody @Valid LivroDTO dto) {
        Livro livro = service.salvar(dto);
        return livro.getId();
    }

//    @PutMapping("{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Livro update(@PathVariable Long id,
//                        @RequestBody @Valid Livro livro) {
//        return livroRepository
//                .findById(id)
//                .map(livroExist -> {
//                    livro.setId(livroExist.getId());
//                    livroRepository.save(livro);
//                    return livro;
//                }).orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                "Livro não encontrado."));
//    }
//
//
//    @DeleteMapping
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public void delete(@PathVariable Long id) {
//        livroRepository.findById(id)
//                .map(livroExist -> {
//                    livroRepository.delete(livroExist);
//                    return livroExist;
//                }).orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                "Livro não encontrado."));
//    }
}
