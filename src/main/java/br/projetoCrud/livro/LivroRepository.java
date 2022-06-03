package br.projetoCrud.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class LivroRepository implements JpaRepository<LivroEntidade, Long> {
    public abstract Optional<LivroEntidade> findByNome(String nome);
}
