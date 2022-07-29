package br.paulocalderan.projetocrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("br.paulocalderan.projetocrud.repository")
@EnableTransactionManagement
public class SpringDataConfig {

}
