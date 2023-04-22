package br.paulocalderan.projetocrud.domain.repository;

import br.paulocalderan.projetocrud.domain.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    List<Editora> findByNameLike(String name);

    Editora save (Editora editora);

}