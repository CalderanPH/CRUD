package br.paulocalderan.projetocrud.integracao.controller;

import br.paulocalderan.projetocrud.integracao.IntegrationTestConfig;
import br.paulocalderan.projetocrud.domain.entity.Editora;
import br.paulocalderan.projetocrud.domain.repository.EditoraRepository;
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


public class EditoraControllerTest extends IntegrationTestConfig {
    @Autowired
    private EditoraRepository editoraRepository;

    private String editoraJson;
    private Editora editora, editora2, editora3;

    @BeforeEach
    public void setUp() {
        super.setUp();
        basePath = "/api/editora";
        editoraJson = ResourceUtils.getContentFromResource("/json/criarEditora.json");
        prepararDados();
    }

    private void prepararDados() {
        Editora editora = new Editora("Calderan Books Edition");
        this.editora = editoraRepository.save(editora);

        Editora editora2 = new Editora("Editora Molinari ltda");
        this.editora2 = editoraRepository.save(editora2);

        Editora editora3 = new Editora("Books Edition Molinari Calderan");
        this.editora3 = editoraRepository.save(editora3);
    }

    @Test
    public void cadastrarEditora_Status201() {
        String loadEditora = editoraJson
                .replace("{{name}}", "Nova Editora Books");

        Response response = given()
                .body(loadEditora)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post();
        response.then()
                .statusCode(HttpStatus.CREATED.value());

        String id = getIdHeaderLocation(response);
        given()
                .pathParam("editoraId", id)
                .when()
                .get("/{editoraId}")
                .then()
                .body("size()", is(2))
                .body("name", is("Nova Editora Books"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void alterarEditora_Status204() {
        String loadEditora = editoraJson
                .replace("{{name}}", "Novo Calderan Books Edition");
        given()
                .pathParam("editoraId", editora.getId().toString())
                .body(loadEditora)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{editoraId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void deletarEditora_Status204() {
        given()
                .pathParam("editoraId", editora.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete("/{editoraId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    @Test
    public void findById_Status200() {
        given()
                .pathParam("editoraId", editora.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{editoraId}")
                .then()
                .body("size()", is(2))
                .body("name", is("Calderan Books Edition"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findById_NaoEncontrado() {
        given()
                .pathParam("editoraId", -1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{editoraId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}