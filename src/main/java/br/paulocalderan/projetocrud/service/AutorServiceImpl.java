package br.paulocalderan.projetocrud.service;

import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.exception.ApiException;
import br.paulocalderan.projetocrud.repository.AutorRepository;
import br.paulocalderan.projetocrud.rest.controller.dto.AutorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;

    @Override
    public Autor salvar(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setName(dto.getName());
        autorRepository.save(autor);
        return autor;
    }

    @Override
    public void update(Long id, AutorDTO autorDTO) {
        autorRepository
                .findById(id)
                .map(autor -> {
                    autor.setName(autorDTO.getName());
                    return autorRepository.save(autor);
                }).orElseThrow(() -> new ApiException(
                        "Autor não encontrado."));
    }


    @Override
    public void delete(Long id) {
        autorRepository
                .findById(id)
                .map(autor -> {
                    autorRepository.delete(autor);
                    return autor;
                }).orElseThrow(() -> new ApiException(
                        "Autor não encontrado."));
    }

    @Override
    public Optional<Autor> obterAutor(Long id) {
        return autorRepository.findById(id);
    }
}
