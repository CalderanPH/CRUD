package br.paulocalderan.projetocrud.domain.repository;

import br.paulocalderan.projetocrud.domain.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNameLike(String name);

    Autor save (Autor autor);

}