package br.paulocalderan.projetocrud.integracao.dto;

import br.paulocalderan.projetocrud.integracao.IntegrationTestConfig;
import br.paulocalderan.projetocrud.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class AutorDTOTest extends IntegrationTestConfig {
    private String autorCriar;

    @BeforeEach
    public void setUp() {
        super.setUp();
        RestAssured.basePath = "/api/autores";
        autorCriar = ResourceUtils.getContentFromResource("/json/criarAutor.json");
    }


    @Test
    public void criarErro_NomeNulo_Retornando400() {
        String criarAutor = autorCriar
                .replace("{{name}}", "");
        given()
                .body(criarAutor)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void criarErro_NomeEmBranco_Retornando400() {
        String criarAutor = autorCriar
                .replace("{{name}}", " ");
        given()
                .body(criarAutor)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
