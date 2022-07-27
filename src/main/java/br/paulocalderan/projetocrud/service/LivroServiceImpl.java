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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LivroServiceImpl implements LivroService{
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

}
