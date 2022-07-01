package br.projetoCrud.service;

import br.projetoCrud.entity.Livro;
import br.projetoCrud.repository.LivroRepository;
import br.projetoCrud.request.LivroPostRequestBody;
import br.projetoCrud.request.LivroPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public List<Livro> listAll() {
        return livroRepository.findAll();
    }

    public List<Livro> findByName(String name) {
        return livroRepository.findByName(name);
    }

    public Livro findByIdOrThorwBadRequestException(long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro not Found"));
    }

    public Livro create(LivroPostRequestBody livroPostRequestBody) {
        return livroRepository.save(Livro.builder().name(livroPostRequestBody.getName()).build());
    }

    public void delete(long id) {
        livroRepository.delete(findByIdOrThorwBadRequestException(id));
    }

    public void replace(LivroPutRequestBody livroPutRequestBody) {
        Livro saveLivro = findByIdOrThorwBadRequestException(livroPutRequestBody.getId());
        Livro livro = Livro.builder()
                .id(saveLivro.getId())
                .name(livroPutRequestBody.getNome())
                .build();

    }
}
