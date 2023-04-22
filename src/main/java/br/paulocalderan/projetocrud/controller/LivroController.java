package br.paulocalderan.projetocrud.controller;


import br.paulocalderan.projetocrud.domain.request.LivroRequest;
import br.paulocalderan.projetocrud.domain.entity.Livro;
import br.paulocalderan.projetocrud.domain.service.LivroService;
import br.paulocalderan.projetocrud.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@Slf4j
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Livro getById(@PathVariable Long id) {
        return service
                .obterLivroCompleto(id)
                .orElseThrow(() ->
                        new ApiException("Informe um id válido."));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Livro> save(@Valid @RequestBody LivroRequest request) {
        Livro livroCriado = service.salvar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroCriado.getId())
                .toUri();
        log.info("Criado novo autor com id: {}", livroCriado.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid LivroRequest request) {
        service.update(id, request);
        log.info("Livro alterado com o id: {}", id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
        log.info("Livro deletado com o id: {}", id);
    }
}
