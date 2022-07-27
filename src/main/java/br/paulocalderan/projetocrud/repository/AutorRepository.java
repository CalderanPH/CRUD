package br.paulocalderan.projetocrud.repository;

import br.paulocalderan.projetocrud.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNameLike(String name);
}
