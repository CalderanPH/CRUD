package br.paulocalderan.projetocrud.integracao;


import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.integracao.dto.AutorDTO;
import br.paulocalderan.projetocrud.service.AutorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CadastroAutorIT {

    @Autowired
    private AutorServiceImpl autorService;

    @Test
    public void deveCadastrarAutorComSucesso() {
        Autor novoAutor = new Autor();
        novoAutor.setName("Paulo Calderan");

        novoAutor = autorService.salvar(new AutorDTO(novoAutor));

        Assertions.assertThat(novoAutor).isNotNull();
        Assertions.assertThat(novoAutor.getId()).isNotNull();
    }

    @Test
    public void deveFalharAoCadastrarAutorSemNome() {
        Autor novoAutor = new Autor();
        novoAutor.setName(null);

        Exception erroEsperado =
                assertThrows(Exception.class, () -> {
                    autorService.salvar(new AutorDTO(novoAutor));
                });

        Assertions.assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalharQuandoExcluirAutorInexistente() {
        autorService.obterAutor(69L);

        Exception erroEsperado =
                assertThrows(Exception.class, () -> {
                    autorService.delete(69L);
                });
        Assertions.assertThat(erroEsperado);
    }


}