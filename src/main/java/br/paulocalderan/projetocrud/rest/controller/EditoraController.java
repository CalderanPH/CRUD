package br.paulocalderan.projetocrud.rest.controller;


import br.paulocalderan.projetocrud.entity.Editora;
import br.paulocalderan.projetocrud.repository.EditoraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
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
    @ResponseStatus(HttpStatus.CREATED)
    public Editora save(@RequestBody @Valid Editora editora) {
        return editoraRepository.save(editora);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Editora update(@PathVariable Long id,
                          @RequestBody @Valid Editora editora) {
        return editoraRepository
                .findById(id)
                .map(editoraExist -> {
                    editora.setId(editoraExist.getId());
                    editoraRepository.save(editora);
                    return editora;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Editora não encontrado."));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        editoraRepository
                .findById(id)
                .map(editoraExist -> {
                    editoraRepository.delete(editoraExist);
                    return editoraExist;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Editora não encontrado"));
    }
}
