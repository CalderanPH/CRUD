package br.paulocalderan.projetocrud.domain.service;

import br.paulocalderan.projetocrud.domain.dto.LivroDTO;
import br.paulocalderan.projetocrud.domain.entity.Livro;
import br.paulocalderan.projetocrud.domain.repository.AutorRepository;
import br.paulocalderan.projetocrud.domain.repository.EditoraRepository;
import br.paulocalderan.projetocrud.domain.repository.LivroRepository;
import br.paulocalderan.projetocrud.domain.entity.Autor;
import br.paulocalderan.projetocrud.domain.entity.Editora;
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
    public Livro salvar(LivroDTO dto) {
        Autor idAutor = dto.getAutor();
        Editora idEditora = dto.getEditora();
        Autor autor = autorRepository.save(idAutor);
//                .orElseThrow(() -> new ApiException("Código de autor inválido."));
        Editora editora = editoraRepository.save(idEditora);
//                .orElseThrow(() -> new ApiException("Código de editora inválido."));

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
