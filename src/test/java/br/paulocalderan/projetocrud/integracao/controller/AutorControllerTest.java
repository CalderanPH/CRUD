package br.paulocalderan.projetocrud.integracao.controller;

import br.paulocalderan.projetocrud.integracao.IntegrationTestConfig;
import br.paulocalderan.projetocrud.domain.entity.Autor;
import br.paulocalderan.projetocrud.domain.repository.AutorRepository;
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


public class AutorControllerTest extends IntegrationTestConfig {

    @Autowired
    private AutorRepository autorRepository;

    private String autorJson;
    private Autor autor, autor2, autor3;

    @BeforeEach
    public void setUp() {
        super.setUp();
        basePath = "/api/autores";
        autorJson = ResourceUtils.getContentFromResource("/json/criarAutor.json");
        prepararDados();
    }

    private void prepararDados() {
        Autor autor = new Autor("Paulo Henrique M Calderan");
        this.autor = autorRepository.save(autor);

        Autor autor2 = new Autor("Luke Skywalker");
        this.autor2 = autorRepository.save(autor2);

        Autor autor3 = new Autor("Leia Skywalker");
        this.autor3 = autorRepository.save(autor3);
    }

    @Test
    public void cadastrarAutor_Status201() {
        String loadAutor = autorJson
                .replace("{{name}}", "Anakin Skywalker");

        Response response = given()
                .body(loadAutor)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post();
        response.then()
                .statusCode(HttpStatus.CREATED.value());

        String id = getIdHeaderLocation(response);
        given()
                .pathParam("autorId", id)
                .when()
                .get("/{autorId}")
                .then()
                .body("size()", is(2))
                .body("name", is("Anakin Skywalker"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void alterarAutor_Status204() {
        String loadAutor = autorJson
                .replace("{{name}}", "Paulo Henrique Molinari Calderan");
        given()
                .pathParam("autorId", autor.getId().toString())
                .body(loadAutor)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{autorId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void deletarAutor_Status204() {
        given()
                .pathParam("autorId", autor.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete("/{autorId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    @Test
    public void findById_Status200() {
        given()
                .pathParam("autorId", autor.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{autorId}")
                .then()
                .body("size()", is(2))
                .body("name", is("Paulo Henrique M Calderan"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findById_NaoEncontrado() {
        given()
                .pathParam("autorId", -1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{autorId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}