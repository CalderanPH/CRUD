package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Livro;
import br.paulocalderan.projetocrud.rest.controller.dto.LivroDTO;
import br.paulocalderan.projetocrud.service.LivroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.*;


@RestController
@Slf4j
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
    @ResponseStatus(CREATED)
    public ResponseEntity<Livro> save(@RequestBody @Valid LivroDTO dto) {
        Livro livroCriado = service.salvar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(livroCriado.getId())
                .toUri();
        log.info("Criado novo autor com id: {}", livroCriado.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid LivroDTO dto) {
        service.update(id, dto);
        log.info("Livro alterado com o id: {}", id);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void delete(@PathVariable Long id) {
        service.delete(id);
        log.info("Livro deletado com o id: {}", id);
    }
}
