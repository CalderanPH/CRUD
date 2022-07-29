package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.entity.Editora;
import br.paulocalderan.projetocrud.repository.EditoraRepository;
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
@RequestMapping("/api/editora")
public class EditoraController {
    private EditoraRepository editoraRepository;

    public EditoraController(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @GetMapping("{id}")
    public Editora findById(@PathVariable Long id) {
        return editoraRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Editora não encontrado."));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Editora> save(@RequestBody @Valid Editora editora) {
        Editora editoraCriado = editoraRepository.save(editora);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(editora.getId())
                .toUri();
        log.info("Criado novo autor com id: {}", editoraCriado.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id,
                          @RequestBody @Valid Editora editora) {
        editoraRepository
                .findById(id)
                .map(editoraExist -> {
                    editora.setId(editoraExist.getId());
                    editoraRepository.save(editora);
                    return editora;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Editora não encontrado."));
        log.info("Editora autalizado com o id: {}", id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        editoraRepository
                .findById(id)
                .map(editoraExist -> {
                    editoraRepository.delete(editoraExist);
                    return editoraExist;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Editora não encontrado"));
        log.info("Editora deletado com o id: {}", id);
    }
}
