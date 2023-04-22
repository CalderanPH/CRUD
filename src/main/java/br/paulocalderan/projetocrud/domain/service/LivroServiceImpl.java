package br.paulocalderan.projetocrud.domain.service;

import br.paulocalderan.projetocrud.domain.entity.Autor;
import br.paulocalderan.projetocrud.domain.entity.Editora;
import br.paulocalderan.projetocrud.domain.entity.Livro;
import br.paulocalderan.projetocrud.domain.repository.AutorRepository;
import br.paulocalderan.projetocrud.domain.repository.EditoraRepository;
import br.paulocalderan.projetocrud.domain.repository.LivroRepository;
import br.paulocalderan.projetocrud.domain.request.LivroRequest;
import br.paulocalderan.projetocrud.exception.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class LivroServiceImpl implements LivroService {
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;
    private final LivroRepository livroRepository;

    @Override
    @Transactional
    public Livro salvar(LivroRequest request) {
        Autor idAutor = request.getAutor();
        Editora idEditora = request.getEditora();
        Autor autor = autorRepository.save(idAutor);
        Editora editora = editoraRepository.save(idEditora);

        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setName(request.getName());
        livro.setGenero(request.getGenero());
        livro.setQtdPaginas(request.getQtdPaginas());

        livroRepository.save(livro);
        return livro;
    }


    @Override
    public void update(Long id, LivroRequest livroRequest) {
        livroRepository
                .findById(id)
                .map(livro -> {
                    livro.setName(livroRequest.getName());
                    livro.setGenero(livroRequest.getGenero());
                    livro.setQtdPaginas(livroRequest.getQtdPaginas());
                    return livroRepository.save(livro);
                }).orElseThrow(() -> new ApiException(
                        "Livro não encontrado."));
    }


    @Override
    public void delete(Long id) {
        livroRepository
                .findById(id)
                .map(livro -> {
                    livroRepository.delete(livro);
                    return livro;
                }).orElseThrow(() -> new ApiException(
                        "Livro não encontrado."));
    }

    @Override
    public Optional<Livro> obterLivroCompleto(Long id) {
        return livroRepository.findById(id);
    }

}