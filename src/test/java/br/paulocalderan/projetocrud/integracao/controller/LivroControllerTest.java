package br.paulocalderan.projetocrud.integracao.controller;

import br.paulocalderan.projetocrud.domain.entity.Autor;
import br.paulocalderan.projetocrud.domain.entity.Editora;
import br.paulocalderan.projetocrud.domain.entity.Livro;
import br.paulocalderan.projetocrud.domain.repository.AutorRepository;
import br.paulocalderan.projetocrud.domain.repository.EditoraRepository;
import br.paulocalderan.projetocrud.domain.repository.LivroRepository;
import br.paulocalderan.projetocrud.integracao.IntegrationTestConfig;
import br.paulocalderan.projetocrud.util.ResourceUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class LivroControllerTest extends IntegrationTestConfig {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    private String livrojson;
    private Livro livro, livro2, livro3;
    private Autor autor;
    private Editora editora;

    @BeforeEach
    public void setUp() {
        super.setUp();
        basePath = "/api/livros";
        livrojson = ResourceUtils.getContentFromResource("/json/criarLivro.json");
        prepararDados();
    }

    private void prepararDados() {
        Autor autor = new Autor("Paulo");
        this.autor = autorRepository.save(autor);

        Editora editora = new Editora("Books");
        this.editora = editoraRepository.save(editora);

        Livro livro = new Livro("Harry Potter", "Fantasia", 200);
        livro.setAutor(autor);
        livro.setEditora(editora);
        this.livro = livroRepository.save(livro);

        Livro livro2 = new Livro(autor, editora, "Senhor dos Anéis", "Fantasia", 500);
        this.livro2 = livroRepository.save(livro2);

        Livro livro3 = new Livro(autor, editora, "Nos dois", "Romance", 150);
        this.livro3 = livroRepository.save(livro3);
    }

    @Test
    public void cadastrarLivro_Status201() {
        String loadLivro = livrojson
                .replace("{{autorId}}", "Paulo Henrique M. Calderan")
                .replace("{{editoraId}}", "Calderan Books Edition")
                .replace("{{name}}", "Star Wars")
                .replace("{{genero}}", "Ficção Cientifica")
                .replace("{{qtdPaginas}}", "250");

        Response response = given()
                .body(loadLivro)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post();
        response.then()
                .statusCode(HttpStatus.CREATED.value());

        String id = getIdHeaderLocation(response);
        given()
                .pathParam("livroId", id)
                .when()
                .get("/{livroId}")
                .then()
                .body("size()", is(6))
                .body("name", is("Star Wars"))
                .body("genero", is("Ficção Cientifica"))
                .body("qtdPaginas", is(250))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void alterarLivro_Status204() {
        String loadLivro = livrojson
                .replace("{{autorId}}", "Novo Calderan")
                .replace("{{editoraId}}", "Novo Books")
                .replace("{{name}}", "Paulo Henrique Molinari Calderan")
                .replace("{{genero}}", "Ação")
                .replace("{{qtdPaginas}}", "450");
        given()
                .pathParam("livroId", livro.getId().toString())
                .body(loadLivro)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{livroId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void deletarLivro_Status204() {
        given()
                .pathParam("livroId", livro.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete("/{livroId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void findById_Status200() {
        given()
                .pathParam("livroId", livro.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{livroId}")
                .then()
                .body("size()", is(6))
                .body("name", is("Harry Potter"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findById_NaoEncontrado() {
        given()
                .pathParam("livroId", -1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{livroId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}