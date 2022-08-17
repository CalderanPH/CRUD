package br.paulocalderan.projetocrud.integracao;


import br.paulocalderan.projetocrud.ProjetoCrudApplication;
import br.paulocalderan.projetocrud.util.DataBaseCleaner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProjetoCrudApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class IntegrationTestConfig {

    protected static final Pattern UUID_PATTERN = Pattern.compile("[^/]+$");

    @Autowired
    private DataBaseCleaner dataBaseCleaner;

    @LocalServerPort
    private int port;

    public void setUp() {
        dataBaseCleaner.clearTables();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
    }

    protected String getIdHeaderLocation(Response response) {
        String location = response.getHeader("Location");
        Matcher matcher = UUID_PATTERN.matcher(location);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
