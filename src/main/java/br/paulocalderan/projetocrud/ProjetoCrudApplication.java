package br.paulocalderan.projetocrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"br.paulocalderan"})
@EnableJpaRepositories(basePackages = "br.paulocalderan")
public class ProjetoCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoCrudApplication.class, args);

    }

}
