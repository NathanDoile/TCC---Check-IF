package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByChegadasAtrasadas(ChegadaAtrasada chegadaAtrasada);

    boolean existsByEmail(String email);

    boolean existsBySiape(String siape);

    boolean existsByCelular(Long celular);

    List<Professor> findAllByOrderByNome();

    Page<Professor> findAllByOrderByNome(Pageable pageable);
}
