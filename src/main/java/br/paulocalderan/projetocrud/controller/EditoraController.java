package br.paulocalderan.projetocrud.controller;

import br.paulocalderan.projetocrud.domain.entity.Editora;
import br.paulocalderan.projetocrud.domain.repository.EditoraRepository;
import br.paulocalderan.projetocrud.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@Slf4j
@RequestMapping("/api/editora")
public class EditoraController {
    private final EditoraRepository editoraRepository;

    public EditoraController(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @GetMapping("/{id}")
    public Editora findById(@PathVariable Long id) {
        return editoraRepository
                .findById(id)
                .orElseThrow(() ->
                        new ApiException("ID inválido, tente novamente."));
    }

    @PostMapping
    public ResponseEntity<Editora> save(@RequestBody @Valid Editora editora) {
        Editora editoraCriado = editoraRepository.save(editora);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(editora.getId())
                .toUri();
        log.info("Criado novo autor com id: {}", editoraCriado.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
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
                        new ApiException("Editora não alterado."));
        log.info("Editora autalizado com o id: {}", id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) throws ApiException {
        editoraRepository
                .findById(id)
                .map(editoraExist -> {
                    editoraRepository.delete(editoraExist);
                    return editoraExist;
                }).orElseThrow(() ->
                        new ApiException("Editora não encontrada."));
        log.info("Editora deletado com o id: {}", id);
    }

}