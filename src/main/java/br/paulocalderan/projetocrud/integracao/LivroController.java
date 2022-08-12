package br.paulocalderan.projetocrud.integracao;


import br.paulocalderan.projetocrud.entity.Livro;
import br.paulocalderan.projetocrud.exception.ApiException;
import br.paulocalderan.projetocrud.integracao.dto.LivroDTO;
import br.paulocalderan.projetocrud.service.LivroService;
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

    private LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Livro getById(@PathVariable Long id) {
        return service
                .obterLivroCompleto(id)
                .orElseThrow(() ->
                        new ApiException("Informe um id válido."));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Livro> save(@Valid @RequestBody LivroDTO dto) {
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
    public void update(@PathVariable Long id,
                       @RequestBody @Valid LivroDTO dto) {
        service.update(id, dto);
        log.info("Livro alterado com o id: {}", id);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
        log.info("Livro deletado com o id: {}", id);
    }
}
