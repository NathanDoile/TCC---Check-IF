package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    boolean existsByEmailAndIsAtivo(String email, boolean ativo);

    Responsavel findByEmailAndIsAtivo(String email, boolean ativo);

    boolean existsByCelularAndIsAtivo(Long celular, boolean ativo);

    List<Responsavel> findAllByAlunosAndIsAtivo(Aluno aluno, boolean ativo);

    List<Responsavel> findAllByEmailNotInAndIsAtivo(List<String> responsaveisPlanilha, boolean ativo);
}
