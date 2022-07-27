package br.paulocalderan.projetocrud.repository;

import br.paulocalderan.projetocrud.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
    List<Editora> findByNameLike(String name);
}
