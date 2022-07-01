package br.projetoCrud.repository;

import br.projetoCrud.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByName(String name);
}
