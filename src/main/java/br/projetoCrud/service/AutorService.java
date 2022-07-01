package br.projetoCrud.service;

import br.projetoCrud.entity.Autor;
import br.projetoCrud.entity.Livro;
import br.projetoCrud.repository.AutorRepository;
import br.projetoCrud.repository.LivroRepository;
import br.projetoCrud.request.AutorPostRequestBody;
import br.projetoCrud.request.AutorPutRequestBody;
import br.projetoCrud.request.LivroPostRequestBody;
import br.projetoCrud.request.LivroPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public List<Autor> listAll() {
        return autorRepository.findAll();
    }

    public List<Autor> findByName(String name) {
        return autorRepository.findByName(name);
    }

    public Autor findByIdOrThorwBadRequestException(long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro not Found"));
    }

    public Autor create(AutorPostRequestBody autorPostRequestBody) {
        return autorRepository.save(Autor.builder().name(autorPostRequestBody.getName()).build());
    }

    public void delete(long id) {
        autorRepository.delete(findByIdOrThorwBadRequestException(id));
    }

    public void replace(AutorPutRequestBody autorPutRequestBody) {
        Autor saveAutor = findByIdOrThorwBadRequestException(autorPutRequestBody.getId());
        Autor autor = Autor.builder()
                .id(saveAutor.getId())
                .name(autorPutRequestBody.getNome())
                .build();

    }
}
