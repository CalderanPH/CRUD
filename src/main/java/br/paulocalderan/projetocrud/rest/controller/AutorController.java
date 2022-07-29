package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.repository.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/autores")
public class AutorController {
    private AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("{id}")
    public Autor findById(@PathVariable Long id) {
        return autorRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Autor não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autor save(@RequestBody @Valid Autor autor) {
        return autorRepository.save(autor);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Autor update(@PathVariable Long id,
                        @RequestBody @Valid Autor autor) {
        return autorRepository
                .findById(id)
                .map(autorExist -> {
                    autor.setId(autorExist.getId());
                    autorRepository.save(autor);
                    return autor;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Autor não encontrado."));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        autorRepository
                .findById(id)
                .map(autorExist -> {
                    autorRepository.delete(autorExist);
                    return autorExist;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Autor não encontrado"));
    }
}
