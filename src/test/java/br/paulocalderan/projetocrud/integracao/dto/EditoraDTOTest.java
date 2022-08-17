package br.paulocalderan.projetocrud.integracao.dto;

import br.paulocalderan.projetocrud.integracao.IntegrationTestConfig;
import br.paulocalderan.projetocrud.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class EditoraDTOTest extends IntegrationTestConfig {
    private String editoraCriar;

    @BeforeEach
    public void setUp() {
        super.setUp();
        RestAssured.basePath = "/api/editora";
        editoraCriar = ResourceUtils.getContentFromResource("/json/criarEditora.json");
    }


    @Test
    public void criarErro_NomeNulo_Retornando400() {
        String criarEditora = editoraCriar
                .replace("{{name}}", "");
        given()
                .body(criarEditora)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
