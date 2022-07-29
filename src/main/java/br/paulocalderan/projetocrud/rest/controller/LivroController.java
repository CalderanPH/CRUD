package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Livro;
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

    @GetMapping("{id}")
    public Livro getById(@PathVariable Long id) {
        return service
                .obterLivroCompleto(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Livro não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody @Valid LivroDTO dto) {
        Livro livro = service.salvar(dto);
        return livro.getId();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid LivroDTO dto) {
        service.update(id, dto);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
