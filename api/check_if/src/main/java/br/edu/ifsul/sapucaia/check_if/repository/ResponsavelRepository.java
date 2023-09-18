package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    boolean existsByEmail(String email);

    Responsavel findByEmail(String email);

    boolean existsByCelular(Long celular);

    List<Responsavel> findAllByAlunos(Aluno aluno);
}
