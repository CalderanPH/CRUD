package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.repository.AutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.PrintStream;
import java.net.URI;

import static org.springframework.http.HttpStatus.*;


@RestController
@Slf4j
@RequestMapping("/api/autores")
public class AutorController {
    private AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping("{id}")
    public Autor findById(@PathVariable Long id) {
        log.info("Autor autalizado com o id: {}", id);
        return autorRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Autor não encontrado."));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Autor> save(@Valid @RequestBody Autor autor) {
        Autor autorCriado = autorRepository.save(autor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(autor.getId())
                .toUri();
        log.info("Criado novo autor com id: {}", autorCriado.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id,
                        @RequestBody @Valid Autor autor) {
         autorRepository
                .findById(id)
                .map(autorExist -> {
                    autor.setId(autorExist.getId());
                    autorRepository.save(autor);
                    return autor;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Autor não encontrado."));
        log.info("Autor autalizado com o id: {}", id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        autorRepository
                .findById(id)
                .map(autorExist -> {
                    autorRepository.delete(autorExist);
                    return autorExist;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Autor não encontrado"));
        log.info("Autor deletado com o id: {}", id);
    }
}
