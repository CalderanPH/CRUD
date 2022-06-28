package br.projetoCrud.repository;

import br.projetoCrud.entity.LivroEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntidade, Long> {
    @Override
    LivroEntidade getReferenceById(Long aLong);
}
