package br.paulocalderan.projetocrud.integracao.dto;

import br.paulocalderan.projetocrud.integracao.IntegrationTestConfig;
import br.paulocalderan.projetocrud.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LivroRequestTest extends IntegrationTestConfig {

    private String livroCriar;

    @BeforeEach
    public void setUp() {
        super.setUp();
        RestAssured.basePath = "/api/livros";
        livroCriar = ResourceUtils.getContentFromResource("/json/criarLivro.json");
    }

    @Test
    public void criarErro_NomeNulo_Retornando400() {
        String criarLivro = livroCriar
                .replace("{{autorId}}", "")
                .replace("{{editoraId}}", "Calderan Books Edition")
                .replace("{{name}}", "Star Wars")
                .replace("{{genero}}", "Ficção Cientifica")
                .replace("{{qtdPaginas}}", "250");
        given()
                .body(criarLivro)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .body("message", is("Campo autor não pode estar vazio."))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void criarErro_NomeEmBranco_Retornando400() {
        String criarLivro = livroCriar
                .replace("{{name}}", " ");
        given()
                .body(criarLivro)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}