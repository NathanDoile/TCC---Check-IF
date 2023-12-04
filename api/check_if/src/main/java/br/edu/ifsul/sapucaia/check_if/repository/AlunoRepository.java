package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Page<Aluno> findByNomeContainingOrMatriculaContainingAndIsAtivo(String nome, String matricula, boolean ativo, Pageable pageable);

    Aluno findBySaidasAntecipadasAndIsAtivo(SaidaAntecipada saidaAntecipada, boolean ativo);

    boolean existsByMatriculaAndIsAtivo(String matricula, boolean ativo);

    Aluno findByMatriculaAndIsAtivo(String matriculaAluno, boolean ativo);

    List<Aluno> findAllByResponsaveisAndIsAtivo(Responsavel responsavel, boolean ativo);

    Aluno findByMatriculaContainingAndIsAtivo(String matricula, boolean ativo);

    boolean existsByMatriculaContainingAndIsAtivo(String matricula, boolean ativo);

    List<Aluno> findAllByMatriculaNotInAndIsAtivo(List<String> alunosPlanilha, boolean ativo);
}
