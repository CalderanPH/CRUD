package br.paulocalderan.projetocrud.service;

import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.entity.Editora;
import br.paulocalderan.projetocrud.entity.Livro;
import br.paulocalderan.projetocrud.exception.RegraNegocioException;
import br.paulocalderan.projetocrud.repository.AutorRepository;
import br.paulocalderan.projetocrud.repository.EditoraRepository;
import br.paulocalderan.projetocrud.repository.LivroRepository;
import br.paulocalderan.projetocrud.rest.controller.dto.LivroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LivroServiceImpl implements LivroService {
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;
    private final LivroRepository livroRepository;

    @Override
    public Livro salvar(LivroDTO dto) {
        Integer idAutor = dto.getAutor();
        Integer idEditora = dto.getEditora();
        Autor autor = autorRepository.findById(Long.valueOf(idAutor))
                .orElseThrow(() -> new RegraNegocioException("Código de autor inválido."));
        Editora editora = editoraRepository.findById(Long.valueOf(idEditora))
                .orElseThrow(() -> new RegraNegocioException("Código de editora inválido."));

        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setName(dto.getName());
        livro.setGenero(dto.getGenero());
        livro.setQtdPaginas(dto.getQtdPaginas());

        livroRepository.save(livro);
        return livro;
    }


    @Override
    public void update(Long id, LivroDTO livroDTO) {
        livroRepository
                .findById(id)
                .map(livro -> {
                    livro.setName(livroDTO.getName());
                    livro.setGenero(livroDTO.getGenero());
                    livro.setQtdPaginas(livroDTO.getQtdPaginas());
                    return livroRepository.save(livro);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Livro não encontrado."));
    }


    @Override
    public void delete(Long id) {
        livroRepository
                .findById(id)
                .map(livro -> {
                    livroRepository.delete(livro);
                    return livro;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Livro não encontrado."));
    }

    @Override
    public Optional<Livro> obterLivroCompleto(Long id) {
        return livroRepository.findById(id);
    }
}
