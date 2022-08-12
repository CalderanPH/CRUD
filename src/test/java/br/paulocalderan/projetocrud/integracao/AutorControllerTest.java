package br.paulocalderan.projetocrud.integracao;

import br.paulocalderan.projetocrud.ProjetoCrudApplication;
import br.paulocalderan.projetocrud.entity.Autor;
import br.paulocalderan.projetocrud.repository.AutorRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.basePath;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProjetoCrudApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AutorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private DataBaseCleaner dataBaseCleaner;

    @Autowired
    private AutorRepository autorRepository;

    private Autor autor1, autor2, autor3;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        basePath = "/api/autores";

        dataBaseCleaner.clearTables();
        prepararDados();
    }

    private void prepararDados() {
        long id = 0;
        Autor autor1 = new Autor(id, "Paulo Henrique M Calderan");
        this.autor1 = autorRepository.save(autor1);

        Autor autor2 = new Autor(id, "Luke Skywalker");
        this.autor2 = autorRepository.save(autor2);

        Autor autor3 = new Autor(id, "Leia Skywalker");
        this.autor3 = autorRepository.save(autor3);
    }

    @Test
    public void deveRetornarRespostaEStatusCorreto_QuandoConsultaAutor() {
        RestAssured.given()
                .pathParam("id", 5)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("Paulo Henrique M. Calderan"));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultaAutorInexistente() {
        RestAssured.given()
                .pathParam("id", 150)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarAutor() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    @Test
    public void deveRetornarStatus201_QuandoCadastrarAutor() {
        RestAssured.given()
                .body("{ \"name\": \"Paulo H Calderan Teste\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }


}