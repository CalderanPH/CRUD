package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.exception.ApiException;
import br.paulocalderan.projetocrud.rest.controller.dto.AutorDTO;
import br.paulocalderan.projetocrud.service.AutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@Slf4j
@RequestMapping("/api/autores")
public class AutorController {
    private AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Autor findById(@PathVariable Long id) {
        log.info("Autor autalizado com o id: {}", id);
        return service
                .obterAutor(id)
                .orElseThrow(() -> new ApiException("Autor não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Autor> save(@Valid @RequestBody AutorDTO dto) {
        Autor autorCriado = service.salvar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(autorCriado.getId())
                .toUri();
        log.info("Criado novo autor com id: {}", autorCriado.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid AutorDTO dto) {
        service.update(id, dto);
        log.info("Autor autalizado com o id: {}", id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
        log.info("Autor deletado com o id: {}", id);
    }
}
